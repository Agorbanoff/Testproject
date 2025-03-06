import React from 'react';
import PropTypes from 'prop-types';
import { Link, useParams } from 'react-router-dom';

// Example array of post objects with a subreddit property added
const posts = [
  { id: 1, name: 'Post One', subreddit: 'funny', description: 'This is the first post.', upvotes: 120, downvotes: 30 },
  { id: 2, name: 'Post Two', subreddit: 'pics', description: 'This is the second post.', upvotes: 150, downvotes: 20 },
  { id: 3, name: 'Post Three', subreddit: 'funny', description: 'This is the third post.', upvotes: 90, downvotes: 10 },
  // ... more posts
];

// Utility function to sort posts by most liked (upvotes - downvotes)
const sortPostsByScore = (postsArray) => {
  return postsArray.sort((a, b) => (b.upvotes - b.downvotes) - (a.upvotes - a.downvotes));
};

// Reusable PostList component that accepts an optional subreddit prop
const PostList = ({ subreddit }) => {
  // If subreddit is provided, filter posts to that subreddit; otherwise, show all posts.
  const filteredPosts = subreddit 
    ? posts.filter(post => post.subreddit.toLowerCase() === subreddit.toLowerCase())
    : posts;
    
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
