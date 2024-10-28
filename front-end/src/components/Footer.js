import React from "react";
import { Typography, Container } from "@mui/material";

function Footer() {
    return (
        <footer style={{ marginTop: '2rem', padding: '1rem 0', backgroundColor: '#f8f8f8' }}>
            <Container maxWidth="sm">
                <Typography variant="body2" color="textSecondary" align="center">
                © 2024 Data-fy - Todos os direitos reservados.
                </Typography>
            </Container>
        </footer>
    );
}

export default Footer;