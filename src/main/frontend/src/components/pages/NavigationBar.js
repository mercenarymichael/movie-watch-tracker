import Navbar from "react-bootstrap/Navbar";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import React, { useEffect, useState } from "react";
import "../style/NavigationBar.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { jwtDecode } from "jwt-decode";
import logo from "../../assets/cinetrack.png";

const NavigationBar = () => {
  const navigate = useNavigate();
  const [searchQuery, setSearchQuery] = useState("");
  const [isAdmin, setIsAdmin] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("jwtToken");
    if (token) {
      try {
        const decodedToken = jwtDecode(token);
        const roles = decodedToken.roles.map((role) => role.authority);
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
    alert("Successfully signed out!");
    navigate("/");
  };

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleSearchSubmit = async (event) => {
    event.preventDefault();
    const token = localStorage.getItem("jwtToken");
    try {
      const response = await axios.get(
        "http://localhost:8080/api/v1/movie/search",
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
          params: {
            name: searchQuery,
          },
        }
      );
      navigate(`/movie/${response.data}`);
    } catch (error) {
      console.error("Error while searching: ", error);
    }
  };

  return (
    <Navbar className="justify-content-between">
      <img
        className="navbar-logo"
        src={logo}
        alt="Logo"
        onClick={() => navigate("/home")}
      />
      <Form inline onSubmit={handleSearchSubmit} className="search-form">
        <Row>
          <Col xs="auto">
            <Form.Control
              type="text"
              value={searchQuery}
              placeholder="Search"
              onChange={handleSearchChange}
              className=" mr-sm-2"
            />
          </Col>
          <Col xs="auto" className="button-group">
            {isAdmin && (
              <Button onClick={() => navigate("/admin")}>Admin</Button>
            )}

            <Button onClick={() => navigate("/account")}>Account</Button>
            <Button onClick={() => navigate("/watchlist")}>Watchlist</Button>
            <Button onClick={handleLogout}>Log out</Button>
          </Col>
        </Row>
      </Form>
    </Navbar>
  );
};

export default NavigationBar;
