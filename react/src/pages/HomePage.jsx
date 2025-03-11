import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Link, useParams } from 'react-router-dom';

// Example array of post objects with a subreddit property added.
// A userVote property is added to track the current vote by the user.
const initialPosts = [
  { id: 1, name: 'Post One', subreddit: 'funny', description: 'This is the first post.', upvotes: 120, downvotes: 30, userVote: null },
  { id: 2, name: 'Post Two', subreddit: 'pics', description: 'This is the second post.', upvotes: 150, downvotes: 20, userVote: null },
  { id: 3, name: 'Post Three', subreddit: 'funny', description: 'This is the third post.', upvotes: 90, downvotes: 10, userVote: null },
  // ... more posts
];

// Utility function to sort posts by most liked (upvotes - downvotes)
const sortPostsByScore = (postsArray) => {
  return postsArray.sort((a, b) => (b.upvotes - b.downvotes) - (a.upvotes - a.downvotes));
};

// Reusable PostList component that accepts an optional subreddit prop
const PostList = ({ subreddit }) => {
  // Manage posts state so that updates (like vote toggles) re-render the component
  const [postList, setPostList] = useState([...initialPosts]);

  // Function to handle toggling an upvote on a post
  const handleToggleUpvote = (id) => {
    setPostList((prevPosts) =>
      prevPosts.map((post) => {
        if (post.id === id) {
          if (post.userVote === 'up') {
            // Remove the upvote
            return { ...post, userVote: null, upvotes: post.upvotes - 1 };
          } else if (post.userVote === 'down') {
            // Switch from downvote to upvote: remove one downvote and add one upvote
            return { ...post, userVote: 'up', upvotes: post.upvotes + 1, downvotes: post.downvotes - 1 };
          } else {
            // Add an upvote
            return { ...post, userVote: 'up', upvotes: post.upvotes + 1 };
          }
        }
        return post;
      })
    );
  };

  // Function to handle toggling a downvote on a post
  const handleToggleDownvote = (id) => {
    setPostList((prevPosts) =>
      prevPosts.map((post) => {
        if (post.id === id) {
          if (post.userVote === 'down') {
            // Remove the downvote
            return { ...post, userVote: null, downvotes: post.downvotes - 1 };
          } else if (post.userVote === 'up') {
            // Switch from upvote to downvote: remove one upvote and add one downvote
            return { ...post, userVote: 'down', downvotes: post.downvotes + 1, upvotes: post.upvotes - 1 };
          } else {
            // Add a downvote
            return { ...post, userVote: 'down', downvotes: post.downvotes + 1 };
          }
        }
        return post;
      })
    );
  };

  // Filter posts by subreddit if provided
  const filteredPosts = subreddit 
    ? postList.filter(post => post.subreddit.toLowerCase() === subreddit.toLowerCase())
    : postList;
    
  const sortedPosts = sortPostsByScore([...filteredPosts]);

  return (
    <div>
      <h1>{subreddit ? `Top posts in r/${subreddit}` : 'Most Liked Posts'}</h1>
      {sortedPosts.map(post => (
        <div key={post.id} style={{ border: '1px solid #ccc', margin: '10px', padding: '10px' }}>
          <h2>
            <Link to={`/post/${post.id}`}>
              {post.name}
            </Link>
          </h2>
          <p>{post.description}</p>
          <p>
            Score: {post.upvotes - post.downvotes} (Upvotes: {post.upvotes}, Downvotes: {post.downvotes})
          </p>
          <button
            onClick={() => handleToggleUpvote(post.id)}
            style={{
              marginRight: '10px',
              backgroundColor: post.userVote === 'up' ? 'darkgreen' : 'black'
            }}
          >
            Upvote
          </button>
          <button
            onClick={() => handleToggleDownvote(post.id)}
            style={{
              backgroundColor: post.userVote === 'down' ? 'purple' : 'black'
            }}
          >
            Downvote
          </button>
        </div>
      ))}
    </div>
  );
};

PostList.propTypes = {
  subreddit: PropTypes.string, // subreddit is optional and should be a string
};

// Homepage component (all subreddits)
const HomePage = () => {
  return <PostList />;
};

// Subreddit page component that extracts the subreddit name from the URL and passes it to PostList
const SubredditPage = () => {
  const { subredditName } = useParams(); // Assumes your route is set as "/r/:subredditName"
  return <PostList subreddit={subredditName} />;
};

export { HomePage, SubredditPage };
