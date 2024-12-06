// import React, { useState } from "react";
// import "./Login.css";

// const Login = ({ onLogin }) => {
//   const [email, setEmail] = useState("");
//   const [password, setPassword] = useState("");

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     if (email === "user@example.com" && password === "password") {
//       onLogin();
//     } else {
//       alert("Invalid credentials!");
//     }
//   };

//   return (
//     <form className="login-form" onSubmit={handleSubmit}>
//       <h2>Login</h2>
//       <div className="input-group">
//         <label>Email:</label>
//         <input
//           type="email"
//           value={email}
//           onChange={(e) => setEmail(e.target.value)}
//           required
//         />
//       </div>
//       <div className="input-group">
//         <label>Password:</label>
//         <input
//           type="password"
//           value={password}
//           onChange={(e) => setPassword(e.target.value)}
//           required
//         />
//       </div>
//       <button type="submit" className="login-button">
//         Login
//       </button>
//     </form>
//   );
// };

// export default Login;
