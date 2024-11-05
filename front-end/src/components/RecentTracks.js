import { useState, useEffect } from "react";
import { Grid2, Container, Typography, Button } from "@mui/material";
import TrackCard from './TrackCard';
import axios from "axios";

function RecentTracks() {
    const [tracks, setTracks] = useState([]);

    useEffect(() => {
        fetchTopArtists();
    }, []);

    const fetchTopArtists = () => {
        axios.get('/spotify/api/recent-tracks')
            .then(response => {
                const items = response.data || [];
                setTracks(items);
            })
            .catch(error => console.error('Erro ao buscar as últimas músicas', error));
    };

    const handleLogin = () => {
        window.open("http://localhost:8080/spotify/api/authorize", "_blank");
        setTimeout(() => {
            fetchTopArtists();
        }, 5000); // 5 segundos
    };

    return (
        <Container>
            <Typography variant="h4" component="h2" gutterBottom>
                Últimas Músicas Ouvidas
            </Typography>
            <Grid2 container spacing={3}>
                {tracks.length > 0 ? (
                    tracks.map(track => (
                        <Grid2 item xs={12} sm={6} md={4} key={track.track.id}>
                            <TrackCard track={track.track} />
                        </Grid2>
                    ))
                ) : (
                    <Grid2 item xs={12}>
                        <Typography variant="body1" color="#fffff">
                            Não está aparecendo nada? Experimente clicar em "Fazer login" para ter acesso às suas músicas.
                        </Typography>
                        <Button variant="contained" color="primary" onClick={handleLogin} style={{ marginTop: "10px" }}>
                            Fazer Login
                        </Button>    
                    </Grid2>
                )}
            </Grid2>
        </Container>
    );
}

export default RecentTracks;