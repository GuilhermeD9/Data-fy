import React from "react";
import { Container, Typography, Button } from "@mui/material";

const Home = () => {
    const handleLogin = () => {
        // Lógica para redirecionar o usuário para a página de login
        window.open('/spotify/api/authorize', '_blank');
    };

    return (
        <Container>
            <Typography variant="h4" component="h1" gutterBottom>
                Bem-vindo ao Spotify Explorer!
            </Typography>
            <Typography variant="body1" color="textSecondary" gutterBottom>
                Aqui você poderá explorar os artistas mais ouvidos de sua conta do Spotify. 
                Para começar, faça login na sua conta do Spotify e descubra suas músicas favoritas. 
                Aproveite e fique à vontade para explorar!
            </Typography>
            <Button 
                variant="contained" 
                color="primary" 
                onClick={handleLogin} 
                style={{ marginTop: "10px" }}
            >
                Fazer Login
            </Button>
        </Container>
    );
};

export default Home;
