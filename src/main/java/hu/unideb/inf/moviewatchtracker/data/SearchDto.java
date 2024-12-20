package hu.unideb.inf.moviewatchtracker.data;

import hu.unideb.inf.moviewatchtracker.entity.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchDto {
    private Long id;
    private List<Movie> results;
}
