package hu.unideb.inf.moviewatchtracker.mapper;

import hu.unideb.inf.moviewatchtracker.data.MovieApiDto;
import hu.unideb.inf.moviewatchtracker.data.MovieDto;
import hu.unideb.inf.moviewatchtracker.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto movieToMovieDto(Movie movie);
    Movie movieDtoToMovie(MovieDto movieDto);
    MovieApiDto movieToPopularMovieDto(Movie movie);

    List<MovieApiDto> movieListToPopularMovieDtoList(List<Movie> movies);
    List<MovieDto> movieListToMovieDtoList(List<Movie> movies);
    List<Movie> movieDtoListToMovieList(List<MovieDto> movieDtos);
}
