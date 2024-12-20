import React, { useEffect, useState } from "react";
import "../style/NavigationBar.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const NavigationBar = () => {
    const navigate = useNavigate();
    const [searchQuery, setSearchQuery] = useState("");
    const handleLogout = () => {
        localStorage.removeItem("jwtToken");
        alert("Tokens have been removed");
        navigate("/");
    };
    const handleSearchChange = (event) => {
        setSearchQuery(event.target.value);
    };

    const handleSearchSubmit = async (event) => {
        event.preventDefault();
        const token = localStorage.getItem('jwtToken');
        try {
            const response = await axios.get('http://localhost:8080/api/v1/movie/search', {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                params: {
                    name: searchQuery
                },
            });
            navigate(`/movie/${response.data}`)
        } catch (error) {
            console.error('Error while searching: ', error);
        }

        
    };
    return (
        <nav className="navbar">
            <h1 className="navbar-logo">Movies</h1>
            <div className="navbar-links">
                
                <form className="navbar-search" onSubmit={handleSearchSubmit}>
                    <input
                        type="text"
                        value={searchQuery}
                        onChange={handleSearchChange}
                        placeholder="Search movies..."
                        className="navbar-search-input"
                    />
                </form>
                <button className="navbar-button">Account</button>
                <button className="navbar-button">Watchlist</button>
                <button className="navbar-button" onClick={handleLogout}>Log-out</button>
            </div>
            
        </nav>
    )
}

export default NavigationBar;
