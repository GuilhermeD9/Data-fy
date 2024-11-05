import React from "react";
import './css/Sidebar.css';

const Sidebar = () => {
    return (
        <div className="sidebar">
            <h2>Funções disponíveis</h2>
            <ul>
                <li>Funções Globais</li>
                <ul>
                    <li><a href="/latest-albums">*Álbums</a></li>
                    <li><a href="/top-world-tracks">*As mais ouvidas no mundo</a></li>
                </ul>
            </ul>
            <ul>
                <li>Funções de usuário</li>
                <ul>
                    <li><a href="/user-tracks">*Músicas mais escutadas do usuário</a></li>
                    <li><a href="/user-artists">*Artistas mais escutados do usuário</a></li>
                    <li><a href="/recent-tracks">*Músicas ouvidas recentemente</a></li>
                    <li><a href="/user-genres">*Gêneros mais escutados</a></li>
                </ul>
            </ul>

        </div>
    );
}

export default Sidebar;