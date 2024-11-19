import React, { useEffect } from "react";
import "./NavigationBar.css";
import { useNavigate } from "react-router-dom";

const NavigationBar = ({ setAuthenticated }) => {
    const navigate = useNavigate();
    console.log("setAuthenticated prop:", setAuthenticated);
    const handleLogout = () => {
        localStorage.removeItem("jwtToken");
        setAuthenticated(false);
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
