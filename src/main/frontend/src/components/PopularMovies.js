import React, { useState, useEffect } from 'react';
import './PopularMovies.css';

const PopularMovies = () => {
    const [movies, setMovies] = useState([]);
    const placeholderImage = "https://via.placeholder.com/154x231?text=Poster";
    const baseUrl = "https://image.tmdb.org/t/p/w153/";

    useEffect(() => {
        // Fetch data from the backend API using fetch
        fetch('/api/popular-movies') // Az endpointod nevét itt állítsd be
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
    }, []);

    return (
        <div className="movies-container">
            <h1>Popular Movies</h1>
            
            <div className="grid-base">
                {movies.map((movie, index) => (
                    <div className="movie-card" key={movie.tmdbId || index}>
                        <img
                            src={movie.posterUrl ? `${baseUrl}${movie.posterUrl}` : placeholderImage}
                            alt={movie.title || `Movie ${index + 1}`}
                            className="movie-poster"
                        />
                        <h3>{movie.title}</h3>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default PopularMovies;
