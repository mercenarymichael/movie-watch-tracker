package hu.unideb.inf.moviewatchtracker.service;

import hu.unideb.inf.moviewatchtracker.configuration.ApiKeyConfig;
import hu.unideb.inf.moviewatchtracker.data.ExtendedMovieDetails;
import hu.unideb.inf.moviewatchtracker.data.BasicMovieDetails;
import hu.unideb.inf.moviewatchtracker.data.SearchDto;
import hu.unideb.inf.moviewatchtracker.data.TMDBResponse;
import hu.unideb.inf.moviewatchtracker.entity.Account;
import hu.unideb.inf.moviewatchtracker.entity.Movie;
import hu.unideb.inf.moviewatchtracker.mapper.MovieMapper;
import hu.unideb.inf.moviewatchtracker.repository.AccountRepository;
import hu.unideb.inf.moviewatchtracker.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    String apiKey = ApiKeyConfig.getTmdbApiKey();

    private final String baseURL = "https://api.themoviedb.org/3/movie/";

    private final WebClient webClient;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final AccountRepository accountRepository;

    //TODO: generikus method webClientre

    @Transactional
    public ExtendedMovieDetails getMovieById(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(baseURL + id)
                .queryParam("api_key", apiKey)
                .queryParam("language", "en-US")
                .toUriString();
        System.out.println(url);
        Movie response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Movie.class)
                .block();

        System.out.println(response);
        return movieMapper.movieToMovieApiDto(response);
    }


    @Transactional
    public void addMovie(Long id, String username) {
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

            Account account = accountRepository.findAccountByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            account.getMovies().add(movie);
            accountRepository.save(account);
        } else {
            throw new RuntimeException("Movie not found");
        }
    }

    @Transactional
    public void deleteMovieFromWatchList(Long id, String username) {
        Movie movie = movieRepository.getMovieByTmdbMovieId(id);
        Account account = accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        System.out.println(account.getMovies());
        account.getMovies().remove(movie);
        accountRepository.save(account);
    }

    @Transactional
    public List<ExtendedMovieDetails> getMovies(String path) {
        String url = UriComponentsBuilder.fromHttpUrl(baseURL + path)
                .queryParam("api_key", apiKey)
                .queryParam("language", "en-US")
                .queryParam("page", 1)
                .toUriString();

        TMDBResponse response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(TMDBResponse.class)
                .block();

        List<Movie> movies = response.getResults().stream()
                .limit(10)
                .collect(Collectors.toList());
        return movieMapper.movieListToMovieApiDtoList(movies);
    }

    @Transactional
    public Long getTmdbId(String name) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/search/movie")
                .queryParam("api_key", apiKey)
                .queryParam("query", name)
                .queryParam("language", "en-US")
                .queryParam("page", 1)
                .toUriString();
        System.out.println(url);
        SearchDto response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(SearchDto.class)
                .block();
        return response.getResults().get(0).getTmdbMovieId();
    }

    public List<BasicMovieDetails> getAllMovies() {
        return movieMapper.movieListToMovieDtoList(movieRepository.findAll());
    }

    @Transactional
    public void deleteMovie(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            movie.get().getAccounts().forEach(account -> account.getMovies().remove(movie.get()));
            movieRepository.delete(movie.get());
        }
    }
}
