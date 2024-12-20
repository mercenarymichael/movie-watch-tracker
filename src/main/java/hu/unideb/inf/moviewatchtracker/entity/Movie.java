package hu.unideb.inf.moviewatchtracker.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @Column(name = "movie_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @JsonProperty(value="id")
    @Column(nullable = false, unique = true)
    private Long tmdbMovieId;

    @JsonProperty(value="poster_path")
    private String posterUrl;

    @JsonProperty(value="backdrop_path")
    private String backdropPath;

    @Column(nullable = false)
    private String title;

    @Lob
    private String overview;

    private Long budget;

    private String status;

    @JsonProperty(value="release_date")
    private LocalDate releaseDate;

    private Integer runtime;

    @JsonProperty(value="vote_average")
    private String voteAverage;

    private String tagline;

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    private List<Account> accounts;

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", tmdbMovieId=" + tmdbMovieId +
                ", posterUrl='" + posterUrl + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", budget=" + budget +
                ", status='" + status + '\'' +
                ", releaseDate=" + releaseDate +
                ", runtime=" + runtime +
                ", voteAverage='" + voteAverage + '\'' +
                '}';
    }
}