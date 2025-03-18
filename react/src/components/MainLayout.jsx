import React, { useState, useEffect } from 'react';
import { Outlet, Link } from 'react-router-dom';

const CommunitiesAside = () => {
  const [joinedCommunities, setJoinedCommunities] = useState([
    { name: 'funny', path: '/r/funny' },
    { name: 'pics', path: '/r/pics' }
  ]);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch(`${import.meta.env.VITE_API_URL}/reddit/joined-communities`, {
      method: "GET",
    })
      .then((response) => {
        if (response.ok) return response.json();
        else throw new Error("Couldn't fetch communities");
      })
      .then((data) => {
        setJoinedCommunities(data);
      })
      .catch((error) => {
        console.error(error);
        setError(error.message);
      });
  }, []);

  return (
    <aside className="communities-aside">
      <h3>Your Communities</h3>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <ul>
        <li>
          <Link to="/discover-communities">Discover Communities</Link>
        </li>
        {joinedCommunities.map((community) => (
          <li key={community.name}>
            <Link to={community.path}>{community.name}</Link>
          </li>
        ))}     
      </ul>
    </aside>
  );
};

const MainLayout = () => {
  return (
    <>
      <nav className="nav">
        <Link className="link" to="/login">Login</Link>
      </nav>
      <div className="layout">
        <CommunitiesAside />
        <main className="content">
          <Outlet />
        </main>
      </div>
    </>
  );
};

export default MainLayout;
