import React from 'react';
import { Card, CardContent, Typography, CardMedia } from '@mui/material';
import './css/Card.css';

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
        <Card className='card'>
            <CardMedia
                component="img"
                height="300"
                width="300"
                image={album.images[0].url || 'https://via.placeholder.com/150'}
                alt={album.name}
            />
            <CardContent className='card-content'>
                <Typography variant="h3">{album.name}</Typography>
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