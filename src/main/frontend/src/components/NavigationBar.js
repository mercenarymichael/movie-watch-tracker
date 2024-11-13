import React from "react";
import "./NavigationBar.css";

const NavigationBar = () => {
    return (
        <nav className="navbar">
            <h1 className="navbar-logo">Movies</h1>
            <div className="navbar-links">
                <button className="navbar-button">Login</button>
                <button className="navbar-button">Details</button>
                <button className="navbar-button">Career</button>
            </div>
        </nav>
    )
}

export default NavigationBar;
