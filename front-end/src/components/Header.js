import React from 'react';
import { AppBar, Toolbar, Typography, } from '@mui/material';
import './css/Header.css'

function Header() {
    return (
        <AppBar position='static' className='header'>
            <Toolbar>
                <Typography variant='h4'>
                    Data-Fy
                </Typography>
            </Toolbar>
        </AppBar>
    );
}

export default Header;