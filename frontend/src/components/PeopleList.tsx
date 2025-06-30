import React from 'react';
import type {PessoaOutputDTO} from '../types';

interface PeopleListProps {
    people: PessoaOutputDTO[];
    onDelete: (cpf: string) => void;
    onEdit: (person: PessoaOutputDTO) => void;
}

export const PeopleList: React.FC<PeopleListProps> = ({ people, onDelete, onEdit }) => {
    return (
        <div className="list-section">
            <h2>Pessoas Cadastradas</h2>
            <table>
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Nascimento</th>
                    <th>CPF</th>
                    <th>Cidade / Estado</th>
                    <th>Ação</th>
                </tr>
                </thead>
                <tbody>
                {people.map((person) => {
                    return (
                        <tr key={person.cpf}>
                            <td>{person.nome}</td>
                            <td>{new Date(person.nascimento).toLocaleDateString('pt-BR', { timeZone: 'UTC' })}</td>
                            <td>{person.cpf}</td>
                            <td>{`${person.endereco.cidade} / ${person.endereco.estado}`}</td>
                            <td>
                                <div className="action-buttons">
                                    <button onClick={() => onEdit(person)} className="icon-btn">✏️</button>
                                    <button onClick={() => onDelete(person.cpf)} className="icon-btn">❌</button>
                                </div>
                            </td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>
    );
};
