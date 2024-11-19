package hu.unideb.inf.moviewatchtracker.service;

import hu.unideb.inf.moviewatchtracker.configuration.ApiKeyConfig;
import hu.unideb.inf.moviewatchtracker.data.MovieDto;
import hu.unideb.inf.moviewatchtracker.data.MovieApiDto;
import hu.unideb.inf.moviewatchtracker.data.TMDBResponse;
import hu.unideb.inf.moviewatchtracker.entity.Account;
import hu.unideb.inf.moviewatchtracker.entity.Movie;
import hu.unideb.inf.moviewatchtracker.mapper.MovieMapper;
import hu.unideb.inf.moviewatchtracker.repository.AccountRepository;
import hu.unideb.inf.moviewatchtracker.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    String apiKey = ApiKeyConfig.getTmdbApiKey();

    private final String baseURL = "https://api.themoviedb.org/3/movie/";

    @Autowired
    private final WebClient webClient;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private AccountRepository accountRepository;

    public MovieDto getMovieById(Long id) {
        return movieMapper.movieToMovieDto(movieRepository.getMovieByTmdbMovieId(id));
    }

    public void addMovie(Long id, Integer accountId) {
        if(movieRepository.existsByTmdbMovieId(id)) {
            System.out.println("Movie already exists");
            return;
        }
        String url = UriComponentsBuilder.fromHttpUrl(baseURL + id)
                .queryParam("api_key", apiKey)
                .queryParam("language", "en-US")
                .queryParam("page", 1)
                .toUriString();
        Movie movie = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
        if (movie != null) {
            movieRepository.save(movie);

            Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            account.getMovies().add(movie);
            accountRepository.save(account);
        } else {
            throw new RuntimeException("Movie not found");
        }
    }


    public List<MovieApiDto> getMovies(String path) {
        String url = UriComponentsBuilder.fromHttpUrl(baseURL + path)
                .queryParam("api_key", apiKey)
                .queryParam("language", "en-US")
                .queryParam("page", 1)
                .toUriString();
        System.out.println(url);

        //TODO: Check performance issue
        TMDBResponse response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(TMDBResponse.class)
                .block();

        List<Movie> movies = response.getResults().stream()
                .limit(10)
                .collect(Collectors.toList());
        return movieMapper.movieListToPopularMovieDtoList(movies);
    }

    public List<MovieDto> getPopularMovies() {
        return null;
    }
}
