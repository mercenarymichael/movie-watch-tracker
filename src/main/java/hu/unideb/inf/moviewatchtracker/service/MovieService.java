package hu.unideb.inf.moviewatchtracker.service;

import hu.unideb.inf.moviewatchtracker.data.MovieDto;
import hu.unideb.inf.moviewatchtracker.entity.Movie;
import hu.unideb.inf.moviewatchtracker.mapper.MovieMapper;
import hu.unideb.inf.moviewatchtracker.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
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
}
