import React from "react";
import { Container, Typography } from "@mui/material";

const Home = () => {

    return (
        <Container>
            <Typography variant="h4" component="h1" gutterBottom>
                Bem-vindo ao Data-Fy!
            </Typography>
            <Typography variant="body1" color="white" gutterBottom>
                Aqui você poderá explorar os artistas mais ouvidos de sua conta do Spotify e as
                novidades do aplicativo. 
                Para começar, explore as funcionalidades da barra lateral e descubra suas músicas favoritas. 
                Aproveite e fique à vontade para explorar!
            </Typography>
        </Container>
    );
};

export default Home;
