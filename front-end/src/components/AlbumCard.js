import React from 'react';
import { Card, CardContent, Typography, CardMedia } from '@mui/material';

function AlbumCard({ album }) {
    if (!album) {
        album = {};
    }

    const formatDate = (dateString) => {
        const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
        const date = new Date(dateString);
        return date.toLocaleDateString('pt-BR', options);
    };

    return (
        <Card>
            <CardMedia
                component="img"
                height="300"
                width="300"
                image={album.images[0].url || 'https://via.placeholder.com/150'}
                alt={album.name}
            />
            <CardContent>
                <Typography variant="h6">{album.name}</Typography>
                <Typography variant="body2" color="textSecondary">
                    Artista: {album.artists.map(artist => artist.name).join(', ')}
                </Typography>
                <Typography variant="body2" color="textSecondary">
                    Data de lançamento: {formatDate(album.release_date)}
                </Typography>
            </CardContent>    
        </Card>
    );
}

export default AlbumCard;