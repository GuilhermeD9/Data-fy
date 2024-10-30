import React from "react";
import './css/Sidebar.css';

const Sidebar = () => {
    return (
        <div className="sidebar">
            <h2>Funções disponíveis</h2>
            <ul>
                <li><a href="/latest-albums">Álbums</a></li>
                <li><a href="/user-tracks">Músicas mais escutadas do usuário</a></li>
                <li><a href="/user-artists">Artistas mais escutados do usuário</a></li>
            </ul>
        </div>
    );
}

export default Sidebar;