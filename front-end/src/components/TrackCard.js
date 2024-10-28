import React from "react";
import { Card, CardContent, Typography, CardMedia } from "@mui/material";

function TrackCard({ track }) {
    return (
        <Card>
            <CardMedia
            component="img"
            height="140"
            image={track.album.images[0]?.url || 'https://via.placeholder.com/150'}
            alt={track.name}
            />
            <CardContent>
                <Typography variant="h6">{track.name}</Typography>
                <Typography variant="body2" color="textSecondary">
                    {track.artists.map(artist => artist.name).join(', ')}
                </Typography>
            </CardContent>
        </Card>
    );
}

export default TrackCard;