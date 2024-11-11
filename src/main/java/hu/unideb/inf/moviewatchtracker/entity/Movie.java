package hu.unideb.inf.moviewatchtracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long tmdbId;

    private String posterUrl;

    @Column(nullable = false)
    private String title;
    private String overview;
    private LocalDate releaseDate;
    private String runtime;
    private String voteAverage;

    @ElementCollection
    private List<String> genres;

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    private List<Account> accounts;
}