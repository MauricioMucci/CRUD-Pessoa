import React, { useState } from 'react';
import type {PessoaOutputDTO} from '../types';

interface PersonSearchProps {
    onSearch: (cpf: string) => void;
    searchResult: PessoaOutputDTO | null;
    isLoading: boolean;
    error: string | null;
}

export const PersonSearch: React.FC<PersonSearchProps> = ({ onSearch, searchResult, isLoading, error }) => {
    const [cpf, setCpf] = useState('');

    const handleSearch = () => {
        onSearch(cpf);
    };

    const formatDate = (dateString: string) => {
        return new Date(dateString).toLocaleString('pt-BR');
    }

    return (
        <div className="search-section">
            <h2>Consultar pessoas integradas</h2>
            <div className="search-bar">
                <input
                    type="text"
                    value={cpf}
                    onChange={(e) => setCpf(e.target.value)}
                    placeholder="CPF"
                />
                <button onClick={handleSearch} disabled={isLoading}>
                    {isLoading ? 'Consultando...' : 'Consultar'}
                </button>
            </div>

            {error && <p className="error-message">{error}</p>}

            {searchResult && (
                <div className="search-results">
                    <p><strong>Nome:</strong> {searchResult.nome}</p>
                    <p><strong>Nascimento:</strong> {new Date(searchResult.nascimento).toLocaleDateString('pt-BR', { timeZone: 'UTC' })}</p>
                    <p><strong>Data/Hora da Inclusão:</strong> {formatDate(searchResult.dataHoraInclusaoRegistro)}</p>
                    <p><strong>Data/Hora da ultima alteração:</strong> {formatDate(searchResult.dataHoraUltimaAlteracaoRegistro)}</p>
                </div>
            )}
        </div>
    );
};
