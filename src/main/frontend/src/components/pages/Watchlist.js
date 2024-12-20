import React, { useState, useEffect } from 'react';
import '../style/Watchlist.css';
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import NavigationBar from './NavigationBar';

const Watchlist = () => {
    const [movies, setMovies] = useState([]);
    const placeholderImage = "https://via.placeholder.com/154x231?text=Poster";
    const baseUrl = "https://image.tmdb.org/t/p/w154/";
    const navigate = useNavigate();
    useEffect(() => {
        
        const token = localStorage.getItem('jwtToken');

        
        const fetchMovies = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/v1/account/watch_list', {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });

                setMovies(response.data);
            } catch (error) {
                console.error("Error fetching movies:", error);
            }
        };

        fetchMovies();
    }, []);

    const handleClick = (id) => {
        navigate(`/movie/${id}`)
    }

    return (
        <div>
            <NavigationBar />
            <div className="movies-container">
                <h1>Watchlist</h1>
                
                <div className="grid-base">
                    {movies.map((movie, index) => (
                        
                            <div className="movie-card" key={movie.tmdbMovieId || index}
                            onClick={() => handleClick(movie.tmdbMovieId)}>
                                <img
                                    src={movie.posterUrl ? `${baseUrl}${movie.posterUrl}` : placeholderImage}
                                    alt={movie.title || `Movie ${index + 1}`}
                                    className="movie-poster"
                                />
                                {/* <h3>{movie.title}</h3> */}
                            </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default Watchlist;
