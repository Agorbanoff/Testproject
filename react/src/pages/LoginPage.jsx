import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import "./LoginPage.css";
const LoginPage = () => {
  // Changed state from email to username
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    setErrorMessage("");
    fetch(`${import.meta.env.VITE_API_URL}/reddit/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      // Changed payload from { email, password } to { username, password }
      body: JSON.stringify({ username, password }),
    })
      .then((response) => {
        // Changed from response.json() to response.text() to handle plain text responses
        if (response.ok) {
          return response.text();
        } else {
          return response.text().then((errorText) => {
            throw new Error(errorText || "Failed to login");
          });
        }
      })
      .then((data) => {
       navigate('/');
        console.log(data);
      })
      .catch((err) => {
        setErrorMessage(err.message);
      });
  };

  return (
    <div className="login-page">
      <form className="login-form" onSubmit={handleSubmit}>
        <h2>Login</h2>
        {errorMessage && <p className="error-message">{errorMessage}</p>}
        <div className="input-group">
          <label>Username:</label> {/* Changed label from Email to Username */}
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="input-group">
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="login-button">
          Login
        </button>
        <p className="register-link">
          Don&#39;t have an account? <Link to="/register">Register here</Link>
        </p>
      </form>
    </div>
  );
};

export default LoginPage;
