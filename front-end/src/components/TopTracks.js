import React, { useEffect, useState } from "react";
import axios from "axios";
import { Grid2, Container, Typography } from "@mui/material";
import TrackCard from './TrackCard';

function TopTracks() {
    const[tracks, setTracks] = useState([]);

    useEffect(() => {
        axios.get('/spotify/api/top-user-tracks')
        .then(response => {
            const items = response.data || {};
            setTracks(items);
        })    
        .catch(error => console.error('Erro ao buscar as músicas:', error));
    }, []);

    return (
        <Container>
            <Typography variant="h4" component="h2" gutterBottom>
                Top user tracks
            </Typography>
            <Grid2 container spacing={3}>
                {tracks.length > 0 ? (
                    tracks.map(track => (
                        <Grid2 item xs={12} sm={6} md={4} key={track.id}>
                            <TrackCard track={track} />
                        </Grid2>    
                ))
            ) : (
                <Typography variant="body1" color="textSecondary">
                    Nenhuma música encontrada
                </Typography>
            )}      
            </Grid2>
        </Container>
    );
}

export default TopTracks;