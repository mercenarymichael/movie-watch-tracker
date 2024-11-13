import React from "react";
import './PopularMovies.css'; // Tedd hozzá a CSS fájlt

const PopularMovies = () => {
    const placeholderImage = "https://via.placeholder.com/154x231?text=Poster"; // Placeholder kép

    // Példa filmek száma
    const movies = new Array(10).fill(0);

    return (
        <div className="movies-container">
            <h1>Popular Movies</h1>
        
            <div className="grid-base">
                {movies.map((_, index) => (
                    <div className="movie-card" key={index}>
                        <img
                            src={placeholderImage} 
                            alt={`Movie ${index + 1}`}
                            className="movie-poster"
                        />
                    </div>
                ))}
            </div>
        </div>
    );
}

export default PopularMovies;
