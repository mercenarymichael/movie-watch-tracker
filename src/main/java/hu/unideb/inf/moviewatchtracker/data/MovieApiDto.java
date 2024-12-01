package hu.unideb.inf.moviewatchtracker.data;

import lombok.Value;

import java.time.LocalDate;

@Value
public class MovieApiDto {
    Long tmdbMovieId;
    String posterUrl;
    Long budget;
    String status;
    Integer runtime;
    String backdropPath;
    String title;
    String overview;
    LocalDate releaseDate;
    String tagline;
    Double voteAverage;
}
