import React, { useEffect, useState } from "react";
import axios from "axios";
import { Grid2, Container, Typography, Button } from "@mui/material";
import TrackCard from './TrackCard';

function Top50World() {
    const[tracks, setTracks] = useState([]);

    useEffect(() => {
        fetchTop50World();
    })

    const fetchTop50World = () => {
        axios.get('/spotify/api/top-world-tracks')
        .then(response => {
            const items = response.data || {};
            setTracks(items);
        })    
        .catch(error => console.error('Erro ao buscar as músicas:', error));
    };

    const handleLogin = () => {
        window.open("http://localhost:8080/spotify/api/authorize", "_blank")
        setTimeout (() => {
            fetchTop50World();
        }, 5000); //5seg
    };

    return (
        <Container>
            <Typography variant="h4" component="h2" gutterBottom>
                As mais ouvidas no mundo
            </Typography>
            <Grid2 container spacing={3}>
                {tracks.length > 0 ? (
                    tracks.map(track => (
                        <Grid2 item xs={12} sm={6} md={4} key={track.id}>
                            <TrackCard track={track} />
                        </Grid2>    
                ))
            ) : (
                <Grid2 item xs={12}>
                        <Typography variant="body1" color="#fffff">
                            Não está aparecendo nada? Experimente clicar em "Fazer login" para ter acesso as suas músicas.
                        </Typography>
                        <Button variant="contained" color="primary" onClick={handleLogin} style={{ marginTop: "10px" }} >Fazer Login</Button>    
                    </Grid2>
            )}      
            </Grid2>
        </Container>
    );
}

export default Top50World;