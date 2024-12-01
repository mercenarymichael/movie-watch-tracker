import React, {useState, useEffect} from 'react';
import { useParams } from "react-router-dom";
import axios from "axios";
import NavigationBar from './NavigationBar';
import '../style/MovieDetails.css'
const MovieDetails = () => {
    const [movie, setMovie] = useState(null);
    const baseUrl = "https://image.tmdb.org/t/p/w500/";
    const [isClicked, setIsClicked] = useState(false);
    const {id} = useParams();
    useEffect(() => {
        const token = localStorage.getItem('jwtToken');
        
        const fetchMovie = async () => {
            try {
                /*
                console.log('Request URL:', `http://localhost:8080/api/v1/movie?id=${id}`);
                const response = await axios.get(`http://localhost:8080/api/v1/movie?id=${id}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
                console.log("API Response:", response.data);
                setMovie(response.data);
                */
                console.log('Fetching movie with ID:', id);
                const response = await fetch(`http://localhost:8080/api/v1/movie?id=${id}`, {
                    method: 'GET',
                    headers: {
                        'Authorization': `Bearer ${token}`,
                        'Content-Type': 'application/json'
                    }
                });

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                const data = await response.json();
                console.log("API Response:", data);
                setMovie(data);
            } catch (error) {
                console.error("Error fetching movie", error);
            }
        }

        fetchMovie();
    }, [id]);

    if (!movie) {
        return <p>Loading...</p>;
    }

    const handleClick = () => {
        if(isClicked === false) {
            //TODO
        } else {
            //TODO
        }
    }

    return (
        <div>
            <NavigationBar />
            <div className='container'>
                <div className='left-side'>
                    <img 
                        src={`${baseUrl}${movie.posterUrl}`}
                        alt={movie.title}
                        className="movie-poster"
                    />
                    <button className="watchlist" onClick={handleClick}>Add to Watchlist</button>
                </div>
                <div className='right-side'>
                    <h1>{movie.title}</h1>
                    <p>{movie.tagline}</p>
                    <p>Status: {movie.status}</p>
                    <p>Runtime: {movie.runtime} min</p>
                    <p>Overview: {movie.overview}</p>
                    <p>Release date: {movie.releaseDate}</p>
                    <p>Average vote: {movie.voteAverage}</p>
                    <p>Budget: {movie.budget}</p>
                </div>
            </div>
        </div>
    )
}

export default MovieDetails;