import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from "./pages/LoginPage.jsx";
import Register from "./pages/RegisterPage.jsx";
import { HomePage, SubredditPage } from './pages/HomePage.jsx';
import PostPage from './pages/PostPage.jsx';
import NotFound from './pages/NotFound.jsx'; // Make sure you have this page
import DiscoverCommunities from './pages/DiscoverCommunities.jsx';
import MainLayout from './components/MainLayout.jsx';
import CreateCommunity from './pages/CreateCommunity.jsx';
import './App.css';

const App = () => {
  return (
    <Router>
      <Routes>
        {/* Routes that should NOT show the aside */}
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* Wrap pages that should show the aside with MainLayout */}
        <Route element={<MainLayout />}>
          <Route path="/" element={<HomePage />} />
          <Route path="/r/:subredditName" element={<SubredditPage />} />
          <Route path="/post/:postId" element={<PostPage />} />
          <Route path="/discover-communities" element={<DiscoverCommunities />} />
          <Route path="/create-community" element={<CreateCommunity />} />
        </Route>

        {/* NotFound page remains outside the MainLayout */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
};

export default App;
