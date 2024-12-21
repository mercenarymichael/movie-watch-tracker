import Form from "react-bootstrap/Form";
import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import axios from "axios";
import "../style/AddMovie.css";

function AddMovie() {
  const [username, setUsername] = useState("");
  const [tmdbId, setTmdbId] = useState("");
  const token = localStorage.getItem("jwtToken");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/movie/watch_list",
        null,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
          params: {
            id: tmdbId,
            username: username,
          },
        }
      );
      alert("Movie added successfully!");
      console.log(response.data);
    } catch (error) {
      console.error("Error adding movie:", error);
      alert("Failed to add movie");
    }
  };

  return (
    <div className="outer-container">
      <div className="outer-box">
        <h1>Add movie to account</h1>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>tmdb id</Form.Label>
            <Form.Control
              className="custom-control"
              type="number"
              name="tmdbMovieId"
              onChange={(e) => setTmdbId(e.target.value)}
              required
              placeholder="123456"
            />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>username</Form.Label>
            <Form.Control
              className="custom-control"
              type="text"
              name="username"
              onChange={(e) => setUsername(e.target.value)}
              required
              placeholder="megamind1234"
            />
          </Form.Group>

          <Button variant="primary" type="submit">
            Submit
          </Button>
        </Form>
      </div>
    </div>
  );
}

export default AddMovie;
