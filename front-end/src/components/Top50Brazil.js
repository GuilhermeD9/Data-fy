import React, { useEffect, useState } from "react";
import axios from "axios";
import { Grid2, Container, Typography } from "@mui/material";
import TrackCard from './TrackCard';

function Top50Brazil() {
    const[tracks, setTracks] = useState([]);

    useEffect(() => {
        axios.get('/spotify/api/top-region-tracks?region=brazil')
        .then(response => setTracks(response.data))
        .catch(error => console.error('Erro ao buscar as músicas:', error));
    }, []);

    return (
        <Container>
            <Typography variant="h4" component="h2" gutterBottom>
                As mais ouvidas no Brasil
            </Typography>
            <Grid2 container spacing={3}>
                {tracks.length > 0 ? (
                    tracks.map(track => (
                        <Grid2 item xs={12} sm={6} md={4} key={track.id}>
                            <TrackCard track={track} />
                        </Grid2>
                    ))
                ) : (
                    <Typography variant="body1">Nenhuma música disponível</Typography>
                )}
            </Grid2>
        </Container>
    );
}

export default Top50Brazil;