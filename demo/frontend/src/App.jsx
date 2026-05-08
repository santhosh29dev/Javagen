import { BrowserRouter, Routes, Route } from 'react-router-dom';

function Home() {
  return (
    <div>
      <h1>Welcome to demo</h1>
      <p>TODO: Build your app here.</p>
    </div>
  );
}

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
      </Routes>
    </BrowserRouter>
  );
}
