package hu.unideb.inf.moviewatchtracker.controller;

import hu.unideb.inf.moviewatchtracker.data.MovieApiDto;
import hu.unideb.inf.moviewatchtracker.data.MovieListDto;
import hu.unideb.inf.moviewatchtracker.entity.Movie;
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
    public ResponseEntity<MovieApiDto> getMovie(@RequestParam Long id) {
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

    @GetMapping("/movies")
    public ResponseEntity<List<MovieListDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
}
