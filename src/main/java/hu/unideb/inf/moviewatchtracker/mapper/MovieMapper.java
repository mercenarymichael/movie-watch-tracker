package hu.unideb.inf.moviewatchtracker.mapper;

import hu.unideb.inf.moviewatchtracker.data.MovieApiDto;
import hu.unideb.inf.moviewatchtracker.data.MovieListDto;
import hu.unideb.inf.moviewatchtracker.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieListDto movieToMovieDto(Movie movie);
    Movie movieDtoToMovie(MovieListDto movieDto);
    MovieApiDto movieToMovieApiDto(Movie movie);

    List<MovieApiDto> movieListToMovieApiDtoList(List<Movie> movies);
    List<MovieListDto> movieListToMovieDtoList(List<Movie> movies);
    List<Movie> movieDtoListToMovieList(List<MovieListDto> movieDtos);
}
