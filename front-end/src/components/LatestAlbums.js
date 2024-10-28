import React, { useEffect, useState } from "react";
import axios from 'axios';
import { Grid2, Container, Typography } from "@mui/material";
import AlbumCard from './AlbumCard.js';

function LatestAlbums() {
    const [albums, setAlbums] = useState([]);

    useEffect(() => {
        axios.get('spotify/api/albums', {
            headers: {
                Authorization: 'Bearer TOKEN_DE_ACESSO',
            }
        })
        .then(response => setAlbums(response.data.items))
        .catch(error => console.error('Erro ao buscar os albuns:', error));
    }, []);

    return (
        <Container>
            <Typography variant="h4" component="h2" gutterBottom>
                Últimos Álbuns Lançados
            </Typography>
            <Grid2 container spacing={3}>
                {albums.map(album => (
                    <Grid2 item xs={12} sm={6} md={4} key={album.id}>
                        <AlbumCard album={album} />
                    </Grid2>
                ))}
            </Grid2>
        </Container>
    );
}

export default LatestAlbums;