import React from 'react';
import { Card, CardContent, Typography, CardMedia } from '@mui/material';

function AlbumCard({ album }) {
    if (!album) {
        album = {};
    }

    return (
        <Card>
            <CardMedia
                component="img"
                height="140"
                image={album.images[0].url || 'https://via.placeholder.com/150'}
                alt={album.name}
            />
            <CardContent>
                <Typography variant="h6">{album.name}</Typography>
                <Typography variant="body2" color="textSecondary">
                    Artista: {album.artists.map(artist => artist.name).join(', ')}
                </Typography>
                <Typography variant="body2" color="textSecondary">
                    Data de lançamento: {album.release_date}
                </Typography>
            </CardContent>    
        </Card>
    );
}

export default AlbumCard;