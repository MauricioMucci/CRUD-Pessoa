import React, { useState, useEffect, useCallback } from 'react';
import { PersonForm } from './components/PersonForm';
import { PeopleList } from './components/PeopleList';
import { PersonSearch } from './components/PersonSearch';
import type {PessoaInputDTO, PessoaOutputDTO} from './types';
import * as api from './services/api';
import './App.css';

function App() {
    const [people, setPeople] = useState<PessoaOutputDTO[]>([]);
    const [personToEdit, setPersonToEdit] = useState<PessoaInputDTO | null>(null);
    const [searchResult, setSearchResult] = useState<PessoaOutputDTO | null>(null);
    const [searchLoading, setSearchLoading] = useState(false);
    const [searchError, setSearchError] = useState<string | null>(null);

    const loadPeople = useCallback(async () => {
        try {
            const data = await api.fetchPeople();
            setPeople(data);
        } catch (error) {
            console.error("Erro ao carregar pessoas:", error);
            alert("Falha ao carregar a lista de pessoas.");
        }
    }, []);

    useEffect(() => {
        loadPeople();
    }, [loadPeople]);

    const handleSave = async (personData: PessoaInputDTO) => {
        try {
            // Aqui você poderia adicionar uma lógica para diferenciar criação de edição
            // Por exemplo, verificando se personToEdit não é nulo.
            // O backend não provê um endpoint de UPDATE, então vamos sempre criar.
            const response = await api.createPerson(personData);
            alert(response.mensagem);
            setPersonToEdit(null); // Limpa o modo de edição
            loadPeople(); // Recarrega a lista
        } catch (error) {
            console.error("Erro ao salvar pessoa:", error);
            alert(`Falha ao salvar pessoa: ${error instanceof Error ? error.message : 'Erro desconhecido'}`);
        }
    };

    const handleDelete = async (cpf: string) => {
        if (window.confirm(`Tem certeza que deseja excluir a pessoa com CPF ${cpf}?`)) {
            try {
                const response = await api.deletePerson(cpf);
                alert(response.mensagem);
                loadPeople(); // Recarrega a lista
            } catch (error) {
                console.error("Erro ao deletar pessoa:", error);
                alert("Falha ao deletar pessoa.");
            }
        }
    };

    const handleEdit = (person: PessoaOutputDTO) => {
        // Converte PessoaOutput para PessoaInput para preencher o formulário
        setPersonToEdit({
            nome: person.nome,
            cpf: person.cpf,
            email: person.email,
            nascimento: person.nascimento,
            endereco: person.endereco,
        });
        window.scrollTo(0, 0); // Rola a tela para o topo para ver o formulário preenchido
    };

    const handleSearch = async (cpf: string) => {
        if (!cpf) return;
        setSearchLoading(true);
        setSearchError(null);
        setSearchResult(null);
        try {
            const result = await api.fetchPersonByCpf(cpf);
            setSearchResult(result);
        } catch (error) {
            console.error("Erro ao buscar pessoa:", error);
            setSearchError(error instanceof Error ? error.message : 'CPF não encontrado.');
        } finally {
            setSearchLoading(false);
        }
    };

    return (
        <div className="container">
            <h1>Controle de Pessoas</h1>
            <PersonForm onSave={handleSave} initialData={personToEdit} />
            <PeopleList people={people} onDelete={handleDelete} onEdit={handleEdit} />
            <PersonSearch
                onSearch={handleSearch}
                searchResult={searchResult}
                isLoading={searchLoading}
                error={searchError}
            />
        </div>
    );
}

export default App;
