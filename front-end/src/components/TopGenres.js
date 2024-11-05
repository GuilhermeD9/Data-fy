import { useState, useEffect } from "react";
import { Container, Typography, Grid2, Button, Card, CardContent } from "@mui/material";
import axios from 'axios';

function TopGenres() {
    const [genres, setGenres] = useState([]);

    useEffect(() => {
        fetchTopGenres();
    }, []);

    const fetchTopGenres = () => {
        axios.get('/spotify/api/top-user-genres')
            .then(response => {
                const items = response.data || [];

                const formattedGenres = Object.keys(items).map(key => ({
                    name: key,
                    count: items[key]
                }));
                setGenres(formattedGenres);
            })
            .catch(error => console.error('Erro ao buscar os gêneros', error));
    };

    const handleLogin = () => {
        window.open("http://localhost:8080/spotify/api/authorize", "_blank");
        setTimeout(() => {
            fetchTopGenres();
        }, 5000); // 5 segundos
    };

    return (
        <Container>
            <Typography variant="h4" component="h2" gutterBottom align="center" style={{ marginBottom: '20px' }}>
                Gêneros mais escutados
            </Typography>
            <Grid2 container spacing={3}>
                {genres && genres.length > 0 ? (
                    genres.map((genre, index) => (
                        <Grid2 item xs={12} sm={6} md={4} key={index}>
                            <Card style={{ backgroundColor: '#333', color: '#FFF' }}>
                                <CardContent>
                                    <Typography variant="h6" component="div" style={{ fontWeight: 'bold' }}>
                                        {genre.name}
                                    </Typography>
                                    <Typography variant="body2" color="#00ff1699">
                                        {`${genre.count} vezes`}
                                    </Typography>
                                </CardContent>
                            </Card>
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
    )
}

export default TopGenres;