import React from 'react';
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';

function Header() {
    return (
        <AppBar position='static'>
            <Toolbar>
                <Typography variant='h6' style={{ flexGrow: 1 }}>
                    Data-fy
                </Typography>
                <Button color='inherit' component={Link} to="/albums">Álbuns</Button>
                <Button color='inherit' component={Link} to="/">Top Tracks</Button>
                <Button color='inherit' component={Link} to="/artists">Top Artists</Button>
            </Toolbar>
        </AppBar>
    );
}

export default Header;