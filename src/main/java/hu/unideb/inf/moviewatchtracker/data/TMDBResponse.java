package hu.unideb.inf.moviewatchtracker.data;

import hu.unideb.inf.moviewatchtracker.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class TMDBResponse {
    private List<Movie> results;
}
