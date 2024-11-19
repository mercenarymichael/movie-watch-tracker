import React, {useState, useEffect} from 'react';
import Carousel from 'react-bootstrap/Carousel';
import './CarouselCss.css';
import axios from 'axios';

function NowPlaying() {
    const [movies, setMovies] = useState([]);
    const baseUrl = "https://image.tmdb.org/t/p/original/";
    useEffect(() => {
        const token = localStorage.getItem('jwtToken');
        const fetchMovies = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/v1/movie/now_playing', {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });

                setMovies(response.data);
                console.log(movies)
            } catch (error) {
                console.error("Error fetching 'now playing' movies:", error);
            }
        };

        fetchMovies();
    }, []);
  return (
    
    <div className='carousel'>
        <Carousel>
            {movies.map((movie, index) => (
                <Carousel.Item key={index}>
                    <img
                        src={baseUrl + movie.backdropPath}
                        alt={movie.title}
                    />
                    <div className='image-overlay'/>
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
