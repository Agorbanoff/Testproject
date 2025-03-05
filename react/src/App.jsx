import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Login from "./pages/LoginPage.jsx";
import Register from "./pages/RegisterPage.jsx";
import { HomePage, SubredditPage } from './pages/HomePage.jsx';

//import Post from "./pages/Post.jsx";
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
          <Route path="/homepage" element={<HomePage />} />
          <Route path="/r/:subredditName" element={<SubredditPage/>} />

          {/* <Route path="/posts/:postname" element={<Post />} /> */}
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
      <p>Sorry, the page you&#39;re looking for doesn&#39;t exist.</p>
    </div>
  );
};

export default App;
