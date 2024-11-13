package hu.unideb.inf.moviewatchtracker.controller;

import hu.unideb.inf.moviewatchtracker.data.MovieDto;
import hu.unideb.inf.moviewatchtracker.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie")
    public ResponseEntity<MovieDto> getMovie(@RequestParam int id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/movie/popular")
    public List<MovieDto> getPopularMovies() {
        return movieService.getPopularMovies();
    }

    @PostMapping("/movie")
    public void addMovie(@RequestBody MovieDto movieDto) {
        movieService.addMovie(movieDto);
    }
}
