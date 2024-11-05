import React from "react";
import './css/Sidebar.css';

const Sidebar = () => {
    return (
        <div className="sidebar">
            <h2>Funções disponíveis</h2>
            <ul>
                <li>Funções Globais</li>
                <ul>
                    <li><a href="/latest-albums">Álbuns lançamentos</a></li>
                    <li><a href="/top-world-tracks">Músicas mais escutadas no Mundo</a></li>
                    <li><a href="/top-brazil-tracks">Músicas mais escutadas no Brasil</a></li>
                </ul>
            </ul>
            <ul>
                <li>Funções de usuário</li>
                <ul>
                    <li><a href="/user-tracks">Suas músicas mais ouvidas</a></li>
                    <li><a href="/user-artists">Seus artistas mais ouvidos</a></li>
                    <li><a href="/recent-tracks">Você ouviu recentemente</a></li>
                    <li><a href="/user-genres">Seus maiores gêneros musicais</a></li>
                </ul>
            </ul>

        </div>
    );
}

export default Sidebar;