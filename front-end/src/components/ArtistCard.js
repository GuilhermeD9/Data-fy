import React from "react";
import { Card, CardContent, Typography, CardMedia } from "@mui/material";

function ArtistCard({ artist }) {
    return (
        <Card>
            <CardMedia
                component="img"
                height="140"
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