import React, { useState, useEffect } from "react";
import Carousel from "react-bootstrap/Carousel";
import "../style/Carousel.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function NowPlaying() {
  const [movies, setMovies] = useState([]);
  const baseUrl = "https://image.tmdb.org/t/p/original/";
  const navigate = useNavigate();
  useEffect(() => {
    const token = localStorage.getItem("jwtToken");
    const fetchMovies = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/movie/now_playing",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        setMovies(response.data);
        console.log(movies);
      } catch (error) {
        console.error("Error fetching 'now playing' movies:", error);
      }
    };

    fetchMovies();
  }, []);

  const handleClick = (id) => {
    navigate(`/movie/${id}`);
  };

  return (
    <div className="carousel">
      <Carousel>
        {movies.map((movie, index) => (
          <Carousel.Item
            key={index}
            onClick={() => handleClick(movie.tmdbMovieId)}
          >
            <img src={baseUrl + movie.backdropPath} alt={movie.title} />
            <div className="image-overlay" />
            <Carousel.Caption>
              <h3>{movie.title}</h3>
            </Carousel.Caption>
          </Carousel.Item>
        ))}
      </Carousel>
    </div>
  );
}

export default NowPlaying;
