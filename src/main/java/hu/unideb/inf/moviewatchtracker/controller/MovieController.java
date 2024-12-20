package hu.unideb.inf.moviewatchtracker.controller;

import hu.unideb.inf.moviewatchtracker.data.MovieApiDto;
import hu.unideb.inf.moviewatchtracker.data.MovieDto;
import hu.unideb.inf.moviewatchtracker.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie")
    public ResponseEntity<MovieApiDto> getMovie(@RequestParam Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/movie/watch_list")
    public ResponseEntity<List<MovieApiDto>> getMovieWatchList() {
        return ResponseEntity.ok(movieService.getMovieWatchList());
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
    public List<MovieApiDto> getPopularMovies() {
        return movieService.getMovies("/popular");
    }

    @GetMapping("/movie/now_playing")
    public List<MovieApiDto> getNowPlayingMovies() {
        return movieService.getMovies("/now_playing");
    }

    @GetMapping("/movie/search")
    public Long getMovieTmdbIdByName(@RequestParam String name) {
        return movieService.getMovieTmdbIdByName(name);
    }
}
