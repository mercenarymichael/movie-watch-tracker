import React, { useEffect, useState } from "react";
import "../style/NavigationBar.css";
import { useNavigate, Link } from "react-router-dom";
import axios from "axios";
import {jwtDecode} from "jwt-decode";
import logo from '../../assets/cinetrack.png';

const Navigation = () => {
    const navigate = useNavigate();
    const [searchQuery, setSearchQuery] = useState("");
    const [isAdmin, setIsAdmin] = useState(false);

    useEffect(() => {
        const token = localStorage.getItem("jwtToken");
        if (token) {
            try {
                const decodedToken = jwtDecode(token);
                const roles = decodedToken.roles.map(role => role.authority);
                if (decodedToken && roles.includes("ADMIN")) {
                    setIsAdmin(true);
                }
            } catch (error) {
                console.error("Invalid token: ", error);
            }
        }
    }, []);


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
            <img className="navbar-logo" 
                src={logo} 
                alt="Logo" 
                onClick={() => navigate("/home")}
            />
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
                {isAdmin && (
                    <button className="navbar-button" onClick={() => navigate("/admin")}>Admin</button>
                )}
                <button className="navbar-button" onClick={() => navigate("/account")} >Account</button>
                <button className="navbar-button" onClick={() => navigate("/watchlist")} >Watchlist</button>
                <button className="navbar-button" onClick={handleLogout}>Log-out</button>
            </div>
            
        </nav>
    )
}

export default Navigation;
