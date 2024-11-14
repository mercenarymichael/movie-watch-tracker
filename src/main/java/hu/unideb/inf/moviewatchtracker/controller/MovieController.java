package hu.unideb.inf.moviewatchtracker.controller;

import hu.unideb.inf.moviewatchtracker.data.MovieDto;
import hu.unideb.inf.moviewatchtracker.data.PopularMovieDto;
import hu.unideb.inf.moviewatchtracker.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie")
    public ResponseEntity<MovieDto> getMovie(@RequestParam Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping("/movie/watch_list")
    public void addMovie(@RequestParam Long id, @RequestParam Integer accountId) {
        movieService.addMovie(id, accountId);
    }

    @GetMapping("/movie/popular")
    public List<PopularMovieDto> getPopularMovies() {
        return movieService.getMovies("/popular");
    }

    @GetMapping("/movie/now_playing")
    public List<PopularMovieDto> getNowPlayingMovies() {
        return movieService.getMovies("/now_playing");
    }
}
