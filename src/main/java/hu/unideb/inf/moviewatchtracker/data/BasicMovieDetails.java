package hu.unideb.inf.moviewatchtracker.data;

import lombok.Value;

import java.time.LocalDate;

/**
 * DTO for {@link hu.unideb.inf.moviewatchtracker.entity.Movie}
 */
@Value
public class BasicMovieDetails {
    Long movieId;
    Long tmdbMovieId;
    String posterUrl;
    String title;
    String overview;
    LocalDate releaseDate;
    String runtime;
    String voteAverage;
    //List<String> genres;
}