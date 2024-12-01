import React from 'react';
import NowPlaying from './NowPlaying'
import PopularMovies from './PopularMovies';
import NavigationBar from './NavigationBar';

const Home = () => {
    return (
        <div>
            <NavigationBar />
            <NowPlaying />
            <PopularMovies />
        </div>
    );
}

export default Home;