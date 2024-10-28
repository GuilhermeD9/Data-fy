import React, { useEffect, useState} from "react";
import axios from 'axios';
import { Grid2, Container, Typography } from "@mui/material";
import ArtistCard from './ArtistCard';

function TopArtists() {
    const [artists, setArtists] = useState([]);

    useEffect(() => {
        axios.get('spotify/api/top-user-artists', {
            headers: {
                Authorization: 'Bearer TOKEN_DE_ACESSO'
            }
        })
        .then(response => setArtists(response.data.items))
        .catch(error => console.error('Erro ao buscar os artistas', error));
    }, []);

    return (
        <Container>
            <Typography variant="h4" component="h2" gutterBottom>
                Top Artists
            </Typography>
            <Grid2 container spacing={3}>
                {artists.map(artist => (
                    <Grid2 item xs={12} sm={6} md={4} key={artist.id}>
                        <ArtistCard artist={artist} />
                    </Grid2>
                ))}
            </Grid2>
        </Container>
    );
}

export default TopArtists;