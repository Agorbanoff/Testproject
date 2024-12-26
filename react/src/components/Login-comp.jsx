import React, { useState } from "react";
import { Link } from "react-router-dom";
// Import the CSS (ensure the path is correct)
import "./LoginComp.css"; // Rename or adjust if necessary

const LoginComp = ({ isOpen, onClose }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    setErrorMessage(""); // Reset error message

    fetch("https://example.com/api/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ email, password }),
    })
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          return response.json().then((error) => {
            throw new Error(error.message || "Failed to login");
          });
        }
      })
      .then((data) => {
        alert("Login successful!"); // Replace with your logic (e.g., redirect)
        console.log(data);
        onClose(); // Close the modal upon successful login
      })
      .catch((err) => {
        setErrorMessage(err.message);
      });
  };

  // Close the modal when pressing the 'Escape' key
  React.useEffect(() => {
    const handleEsc = (event) => {
      if (event.key === "Escape" && isOpen) {
        onClose();
      }
    };
    window.addEventListener("keydown", handleEsc);

    return () => {
      window.removeEventListener("keydown", handleEsc);
    };
  }, [isOpen, onClose]);

  if (!isOpen) return null; // Do not render anything if the modal is not open

  return (
    <div className={`login-overlay ${isOpen ? "active" : ""}`} onClick={onClose}>
      <div
        className="login-popup"
        onClick={(e) => e.stopPropagation()} // Prevent closing when clicking inside the popup
      >
        <span className="close-button" onClick={onClose}>
          &times;
        </span>
        <form className="login-form" onSubmit={handleSubmit}>
          <h2>Login</h2>
          {errorMessage && <p className="error-message">{errorMessage}</p>}
          <div className="input-group">
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              autoFocus
            />
          </div>
          <div className="input-group">
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="login-button">
            Login
          </button>
          <p className="register-link">
            Don't have an account? <Link to="/register">Register here</Link>
          </p>
        </form>
      </div>
    </div>
  );
};

export default LoginComp;
