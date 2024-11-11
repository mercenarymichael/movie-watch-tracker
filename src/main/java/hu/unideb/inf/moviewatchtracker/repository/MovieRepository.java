package hu.unideb.inf.moviewatchtracker.repository;

import hu.unideb.inf.moviewatchtracker.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
  Movie getMovieById(int id);
}