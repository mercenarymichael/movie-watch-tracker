package hu.unideb.inf.moviewatchtracker.data;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class PopularMovieDto {
    Long movieId;
    Long tmdbMovieId;
    String posterUrl;
    String title;
    String overview;
    LocalDate releaseDate;
    String voteAverage;
    List<String> genres;
}