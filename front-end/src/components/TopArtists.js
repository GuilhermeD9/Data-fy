import React, { useEffect, useState} from "react";
import axios from 'axios';
import { Grid2, Container, Typography, Button } from "@mui/material";
import ArtistCard from './ArtistCard';

function TopArtists() {
    const [artists, setArtists] = useState([]);

    useEffect(() => {
        fetchTopArtists();
    })

    const fetchTopArtists = () => {
        axios.get('/spotify/api/top-user-artists')
        .then(response => {
            const items = response.data || [];
            setArtists(items);
        })
        .catch(error => console.error('Erro ao buscar os artistas', error));
    };

    const handleLogin = () => {
        window.open("http://localhost:8080/spotify/api/authorize", "_blank")
        setTimeout (() => {
            fetchTopArtists();
        }, 5000); //5seg
    };

    return (
        <Container>
            <Typography variant="h4" component="h2" gutterBottom>
                Artistas mais ouvidos do usuário
            </Typography>
            <Grid2 container spacing={3}>
                {artists.length > 0 ? (
                    artists.map(artist => (
                        <Grid2 item xs={12} sm={6} md={4} key={artist.id}>
                            <ArtistCard artist={artist} />
                        </Grid2>
        
                    ))
                ) : (
                    <Grid2 item xs={12}>
                        <Typography variant="body1" color="#fffff">
                            Não está aparecendo nada? Experimente clicar em "Fazer login" para ter acesso aos seus artisas.
                        </Typography>
                        <Button variant="contained" color="primary" onClick={handleLogin} style={{ marginTop: "10px" }} >Fazer Login</Button>    
                    </Grid2>
                )}
            </Grid2>
        </Container>
    );
}

export default TopArtists;