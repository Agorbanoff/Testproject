import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Login from "./pages/Login.jsx";
 import Register from "./pages/Register.jsx";
import './App.css'; // Import the CSS file

const App = () => {
  return (
    <Router>
      <nav className="nav">
        <Link className="link" to="/login">login</Link>
      </nav>

      <div className="content">
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />



          {/* Fallback route for 404 Not Found */}
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </Router>
  );
};

// Optional: 404 Not Found Component
const NotFound = () => {
  return (
    <div className="not-found">
      <h2>404 - Page Not Found</h2>
      <p>Sorry, the page youre looking for doesnt exist.</p>
    </div>
  );
};

export default App;
