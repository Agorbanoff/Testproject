import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const DiscoverCommunities = () => {
  const [searchTerm, setSearchTerm] = useState('');

  // Example list of communities
  const allCommunities = [
    { name: 'funny', path: `/r/funny` },
    { name: 'pics', path: '/r/pics' }
  ];

  // Filter communities based on the search term
  const filteredCommunities = allCommunities.filter(community =>
    community.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="discover-communities">
      <h2>Discover Communities</h2>
      <input
        type="text"
        placeholder="Search communities..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      <ul>
        {filteredCommunities.map(community => (
          <li key={community.name}>
            <Link to={community.path}>{community.name}</Link>
          </li>
        ))}
      </ul>
      <Link to="/create-community">
        <button>Create Community</button>
      </Link>
    </div>
  );
};

export default DiscoverCommunities;
