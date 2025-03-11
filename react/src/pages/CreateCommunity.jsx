import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const CreateCommunity = () => {
  const [communityName, setCommunityName] = useState('');
  const [description, setDescription] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!communityName.trim()) {
      setError('Community name is required');
      return;
    }

    setLoading(true);
    setError('');

    try {
      const response = await fetch('/api/communities', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ communityName, description })
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Error creating community');
      }

      const data = await response.json();
      navigate(`/r/${data.communityName.toLowerCase()}`);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  // Define a style object for input and textarea
  const inputStyle = {
    backgroundColor: 'black',
    color: 'white',
    border: '1px solid #ccc',
    padding: '8px',
    borderRadius: '4px'
  };

  return (
    <div className="create-community">
      <h2>Create Community</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="communityName">Community Name:</label>
          <input  
            type="text"
            id="communityName"
            value={communityName}
            onChange={(e) => setCommunityName(e.target.value)}
            placeholder="Enter community name"
            style={inputStyle}
          />
        </div>
        <div>
          <label htmlFor="description">Description (optional):</label>
          <textarea
            id="description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="Enter a short description"
            style={{ ...inputStyle, resize: 'vertical' }}
          />
        </div>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <button type="submit" disabled={loading}>
          {loading ? 'Creating...' : 'Create Community'}
        </button>
      </form>
    </div>
  );
};

export default CreateCommunity;
