import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import NavigationBar from "./NavigationBar";
import "../style/MovieDetails.css";
const MovieDetails = () => {
  const [movie, setMovie] = useState(null);
  const posterUrl = "https://image.tmdb.org/t/p/w500/";
  const backgroundUrl = "https://image.tmdb.org/t/p/original/";
  const { id } = useParams();
  const [isMovieInWatchlist, setIsMovieInWatchlist] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("jwtToken");

    const fetchMovie = async () => {
      try {
        const movieResponse = await axios.get(
          `http://localhost:8080/api/v1/movie?id=${id}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setMovie(movieResponse.data);

        const response = await axios.get(
          "http://localhost:8080/api/v1/account/watch_list",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        setIsMovieInWatchlist(
          response.data.some(
            (item) => item.tmdbMovieId === movieResponse.data.tmdbMovieId
          )
        );
      } catch (error) {
        console.error("Error fetching movie", error);
      }
    };

    fetchMovie();
  }, [id]);

  if (!movie) {
    return <p>Loading...</p>;
  }

  const handleClick = async () => {
    const token = localStorage.getItem("jwtToken");

    if (!isMovieInWatchlist) {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/v1/movie/watch_list",
          null,
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
            params: {
              id: movie.tmdbMovieId,
              username: localStorage.getItem("username"),
            },
          }
        );

        console.log("Success: added movie " + movie.title + " to watchlist");
        setIsMovieInWatchlist(true);
      } catch (error) {
        console.error("Error:", error);
      }
    } else {
      try {
        const response = await axios.delete(
          "http://localhost:8080/api/v1/movie/watch_list",
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
            params: {
              id: movie.tmdbMovieId,
              username: localStorage.getItem("username"),
            },
          }
        );

        console.log(
          "Success: deleted movie " + movie.title + " from watchlist"
        );
        setIsMovieInWatchlist(false);
      } catch (error) {
        console.error("Error:", error);
      }
    }
  };

  return (
    <div>
      <NavigationBar />
      <div
        className="box"
        style={{
          backgroundImage: `url(${backgroundUrl + movie.backdropPath})`,
        }}
      >
        <div className="left-side">
          <img
            src={`${posterUrl}${movie.posterUrl}`}
            alt={movie.title}
            className="poster"
          />
        </div>
        <div className="right-side">
          <h1>{movie.title}</h1>
          <p className="tagline">{movie.tagline}</p>
          <button className="watchlist" onClick={handleClick}>
            {isMovieInWatchlist ? (
              <i class="bi bi-check2-square"> Movie Added</i>
            ) : (
              "Add to Watchlist"
            )}
          </button>
          <ul className="movie-details">
            <li>
              <strong>Status:</strong> {movie.status}
            </li>
            <li>
              <strong>Runtime:</strong> {movie.runtime} min
            </li>
            <li>
              <strong>Release Date:</strong> {movie.releaseDate}
            </li>
            <li>
              <strong>Average Vote:</strong> {movie.voteAverage}
            </li>
            <li>
              <strong>Budget:</strong> {movie.budget}
            </li>
          </ul>
          <p className="overview">
            <strong>Overview:</strong> {movie.overview}
          </p>
        </div>
      </div>
    </div>
  );
};

export default MovieDetails;
