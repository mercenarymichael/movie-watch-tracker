import React, { useEffect } from "react";
import "../style/NavigationBar.css";
import { useNavigate } from "react-router-dom";

const NavigationBar = () => {
    const navigate = useNavigate();
    const handleLogout = () => {
        localStorage.removeItem("jwtToken");
        alert("Tokens have been removed");
        navigate("/");
    };

    return (
        <nav className="navbar">
            <h1 className="navbar-logo">Movies</h1>
            <div className="navbar-links">
                <button className="navbar-button">Account</button>
                <button className="navbar-button">Watchlist</button>
                <button className="navbar-button" onClick={handleLogout}>Log-out</button>
            </div>
        </nav>
    )
}

export default NavigationBar;
