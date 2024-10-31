import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Sidebar from './components/Sidebar';
import TopTracks from './components/TopTracks';
import TopArtists from './components/TopArtists';
import LatestAlbums from './components/LatestAlbums';

function App() {
  return (
    <Router>
      <div className='app-container'>
        <Sidebar />
        <div className='content-wrapper'>
          <Header />
          <Routes>
            <Route path='/user-tracks' element={<TopTracks />} />
            <Route path='/user-artists' element={<TopArtists />} />
            <Route path='/latest-albums' element={<LatestAlbums />} />
          </Routes>
          <Footer />
        </div>
      </div>
    </Router>
  );
}

export default App;
