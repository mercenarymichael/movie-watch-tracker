import React, { useState, useEffect } from 'react';
import './PopularMovies.css';

const PopularMovies = () => {
    const [movies, setMovies] = useState([]);
    const placeholderImage = "https://via.placeholder.com/154x231?text=Poster";
    const baseUrl = "https://image.tmdb.org/t/p/w154/";

    useEffect(() => {
        fetch('http://localhost:8080/api/movie/popular')
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json();
            })
            .then(data => setMovies(data))
            .catch(error => {
                console.error("Error fetching popular movies:", error);
            });
        console.log(movies);
    }, []);

    return (
        <div className="movies-container">
            <h1>Popular Movies</h1>
            
            <div className="grid-base">
                {movies.map((movie, index) => (
                    <a
                        key={index}
                        href="https://streamable.com/lf027o"
                        target="_blank"
                        rel="noopener noreferrer"
                        className="movie-link"
                    >
                        <div className="movie-card" key={movie.tmdbId || index}>
                            <img
                                src={movie.posterUrl ? `${baseUrl}${movie.posterUrl}` : placeholderImage}
                                alt={movie.title || `Movie ${index + 1}`}
                                className="movie-poster"
                            />
                            {/* <h3>{movie.title}</h3> */}
                        </div>
                    </a>
                ))}
            </div>
        </div>
    );
}

export default PopularMovies;
