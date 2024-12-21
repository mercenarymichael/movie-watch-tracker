package hu.unideb.inf.moviewatchtracker.mapper;

import hu.unideb.inf.moviewatchtracker.data.BasicMovieDetails;
import hu.unideb.inf.moviewatchtracker.data.ExtendedMovieDetails;
import hu.unideb.inf.moviewatchtracker.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    BasicMovieDetails movieToMovieDto(Movie movie);
    Movie movieDtoToMovie(BasicMovieDetails movieDto);
    ExtendedMovieDetails movieToMovieApiDto(Movie movie);

    List<ExtendedMovieDetails> movieListToMovieApiDtoList(List<Movie> movies);
    List<BasicMovieDetails> movieListToMovieDtoList(List<Movie> movies);
    List<Movie> movieDtoListToMovieList(List<BasicMovieDetails> movieDtos);
}
