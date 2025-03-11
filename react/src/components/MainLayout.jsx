import React from 'react';
import { Outlet, Link } from 'react-router-dom';

const CommunitiesAside = () => {
  const joinedCommunities = [
    { name: 'funny', path: '/r/funny' },
    { name: 'pics', path: '/r/pics' }
  ];

  return (
    <aside className="communities-aside">
      <h3>Your Communities</h3>
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
        <Link className="link" to="/login">login</Link>
      </nav>
      <div className="content">
        {/* This is where the matched child routes will be rendered */}
        <Outlet />
      </div>
      <CommunitiesAside />
    </>
  );
};

export default MainLayout;
