import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import TopTracks from './components/TopTracks';
import TopArtists from './components/TopArtists';
import LatestAlbums from './components/LatestAlbums';

function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path='/' element={<TopTracks />} />
        <Route path='/artists' element={<TopArtists />} />
        <Route path='/albums' element={<LatestAlbums />} />
      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
