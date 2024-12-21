import React, { useState } from "react";
import axios from "axios";
import '../style/Login.css'
import { Link, useNavigate } from 'react-router-dom';

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/v1/auth/authenticate", {
        username,
        password,
      });
      console.log(response.data.token)
      if (response.data.token) {
        const token = response.data.token;
        localStorage.setItem("jwtToken", token);
        localStorage.setItem("username", username);
        navigate("/home");
        alert("Successful sign in!");
      } else {
        setErrorMessage("Incorrect username or password!");
      }
    } catch (error) {
      setErrorMessage("Incorrect data or API error!");
    }
  };

  return (
    <div className="login-page">
      <div className="outer-box">
        <h1>Sign in</h1>
        <form onSubmit={handleSubmit} className="login-form">
          <div style={{ marginBottom: "10px" }}>
            <label>Username: </label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div style={{ marginBottom: "20px" }}>
            <label>Password: </label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          {errorMessage && <div className="error-message">{errorMessage}</div>}
          <button type="submit">
            Sign in
          </button>
          <p>Don't have an account? <Link to="/register">Register</Link></p>
        </form>
      </div>
    </div>
  );
};

export default Login;
