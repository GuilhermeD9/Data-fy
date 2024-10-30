import React from "react";
import { Card, CardContent, Typography, CardMedia } from "@mui/material";
import './css/Card.css'

function ArtistCard({ artist }) {
    return (
        <Card className="card">
            <CardMedia
                component="img"
                image={artist.images[0]?.url || 'https://via.placeholder.com/150'}
                alt={artist.name}
            />
            <CardContent>
                <Typography variant="h6">{artist.name}</Typography>
                <Typography variant="body2" color="textSecondary">
                    Popularidade: {artist.popularity}
                </Typography>
            </CardContent>  
        </Card>
    );
}

export default ArtistCard;