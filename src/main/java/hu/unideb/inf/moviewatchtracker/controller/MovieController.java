package hu.unideb.inf.moviewatchtracker.controller;

import hu.unideb.inf.moviewatchtracker.data.BasicMovieDetails;
import hu.unideb.inf.moviewatchtracker.data.ExtendedMovieDetails;
import hu.unideb.inf.moviewatchtracker.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/movie")
    public ResponseEntity<ExtendedMovieDetails> getMovie(@RequestParam Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }


    @DeleteMapping("/movie")
    public void deleteMovie(@RequestParam Long id) {
        movieService.deleteMovie(id);
    }


    @PostMapping("/movie/watch_list")
    public void addMovie(@RequestParam Long id, @RequestParam String username) {
        movieService.addMovie(id, username);
    }


    @DeleteMapping("/movie/watch_list")
    public void deleteMovieFromWatchList(@RequestParam Long id, @RequestParam String username) {
        movieService.deleteMovieFromWatchList(id, username);
    }


    @GetMapping("/movie/popular")
    public List<ExtendedMovieDetails> getPopularMovies() {
        return movieService.getMovies("/popular");
    }


    @GetMapping("/movie/now_playing")
    public List<ExtendedMovieDetails> getNowPlayingMovies() {
        return movieService.getMovies("/now_playing");
    }


    @GetMapping("/movie/search")
    public Long getTmdbId(@RequestParam String name) {
        return movieService.getTmdbId(name);
    }


    @GetMapping("/movies")
    public ResponseEntity<List<BasicMovieDetails>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
}
