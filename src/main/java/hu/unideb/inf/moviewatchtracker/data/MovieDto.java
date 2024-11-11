package hu.unideb.inf.moviewatchtracker.data;

import lombok.Value;
import org.mapstruct.Mapper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link hu.unideb.inf.moviewatchtracker.entity.Movie}
 */
@Value
public class MovieDto{
    Long tmdbId;
    String posterUrl;
    String title;
    String overview;
    LocalDate releaseDate;
    String runtime;
    String voteAverage;
    List<String> genres;
}