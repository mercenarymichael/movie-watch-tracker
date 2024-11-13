package hu.unideb.inf.moviewatchtracker.service;

import hu.unideb.inf.moviewatchtracker.configuration.ApiKeyConfig;
import hu.unideb.inf.moviewatchtracker.data.MovieDto;
import hu.unideb.inf.moviewatchtracker.data.PopularMovieDto;
import hu.unideb.inf.moviewatchtracker.data.TMDBResponse;
import hu.unideb.inf.moviewatchtracker.entity.Movie;
import hu.unideb.inf.moviewatchtracker.mapper.MovieMapper;
import hu.unideb.inf.moviewatchtracker.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    String apiKey = ApiKeyConfig.getTmdbApiKey();

    @Autowired
    private final WebClient webClient;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    public MovieDto getMovieById(int id) {
        return movieMapper.movieToMovieDto(movieRepository.getMovieById(id));
    }

    public void addMovie(MovieDto movieDto) {
        Movie movie = movieMapper.movieDtoToMovie(movieDto);
        movieRepository.save(movie);
    }

    public List<PopularMovieDto> getPopularMovies() {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/movie/popular")
                .queryParam("api_key", apiKey)
                .queryParam("language", "en-US")
                .queryParam("page", 1)
                .toUriString();
        System.out.println(url);
        TMDBResponse response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(TMDBResponse.class)
                .block();

        List<Movie> movies = response.getResults();
        return movieMapper.movieListToPopularMovieDtoList(movies);
    }
}
