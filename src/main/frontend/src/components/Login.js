import React, { useState } from "react";
import axios from "axios";
import './Login.css'
import { useNavigate } from 'react-router-dom';

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
        navigate("/home");
        alert("Sikeres bejelentkezés!");
      } else {
        setErrorMessage("Hibás felhasználónév vagy jelszó!");
      }
    } catch (error) {
      setErrorMessage("Helytelen adatok vagy API hiba!");
    }
  };

  return (
    <div className="login-page">
      <div className="outer-box">
        <h2>Bejelentkezés</h2>
        <form onSubmit={handleSubmit}>
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
            Bejelentkezés
          </button>
        </form>
      </div>
    </div>
  );
};

export default Login;
