import React from "react";
import { useParams } from "react-router-dom";

// Static array of post objects
const posts = [
  { id: 1, name: 'Post One', subreddit: 'funny', description: 'This is the first post.', upvotes: 120, downvotes: 30 },
  { id: 2, name: 'Post Two', subreddit: 'pics', description: 'This is the second post.', upvotes: 150, downvotes: 20 },
  { id: 3, name: 'Post Three', subreddit: 'funny', description: 'This is the third post.', upvotes: 90, downvotes: 10 },
];

const PostPage = () => {
  // Retrieve the postId parameter from the URL
  const { postId } = useParams();

  // Find the post in the static array, converting postId to a number for comparison
  const post = posts.find((p) => p.id === Number(postId));

  if (!post) {
    return <div>Post not found.</div>;
  }

  return (
    <div>
      <h2>{post.name}</h2>
      <p>Subreddit: {post.subreddit}</p>
      <p>{post.description}</p>
      <p>
        <strong>Upvotes:</strong> {post.upvotes} | <strong>Downvotes:</strong> {post.downvotes}
      </p>
    </div>
  );
};

export default PostPage;
