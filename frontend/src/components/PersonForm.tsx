import React, { useState, useEffect } from 'react';
import type {PessoaInputDTO, EnderecoDTO} from '../types';
import { fetchAddressByCep } from '../services/api';

interface PersonFormProps {
    onSave: (person: PessoaInputDTO) => void;
    initialData?: PessoaInputDTO | null;
}

const initialAddress: EnderecoDTO = {
    cep: 0,
    rua: '',
    numero: 0,
    cidade: '',
    estado: '',
};

const initialFormState: PessoaInputDTO = {
    nome: '',
    nascimento: '',
    cpf: '',
    email: '',
    endereco: initialAddress,
};

export const PersonForm: React.FC<PersonFormProps> = ({ onSave, initialData }) => {
    const [formData, setFormData] = useState<PessoaInputDTO>(initialFormState);

    useEffect(() => {
        if (initialData) {
            setFormData(initialData);
        } else {
            setFormData(initialFormState);
        }
    }, [initialData]);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleAddressChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        // Converte para número se for 'cep' ou 'numero'
        const numericValue = (name === 'cep' || name === 'numero') ? Number(value) : value;
        setFormData(prev => ({
            ...prev,
            endereco: { ...prev.endereco, [name]: numericValue },
        }));
    };

    const handleCepSearch = async () => {
        if (String(formData.endereco.cep).length !== 8) {
            alert("O CEP deve conter 8 digítos. Por favor, verifique e tente novamente.");
            return
        };
        try {
            const addressData = await fetchAddressByCep(String(formData.endereco.cep));
            setFormData(prev => ({
                ...prev,
                endereco: {
                    ...prev.endereco,
                    rua: addressData.rua,
                    cidade: addressData.cidade,
                    estado: addressData.estado,
                },
            }));
        } catch (error) {
            alert("Não foi possível encontrar o CEP informado.");
        }
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onSave(formData);
        setFormData(initialFormState); // Limpa o formulário após salvar
    };

    return (
        <form onSubmit={handleSubmit} className="form-section">
            <h2>Cadastro de Pessoa</h2>
            <div className="form-grid">
                <input name="nome" value={formData.nome} onChange={handleChange} placeholder="Nome" required />
                <input name="nascimento" type="date" value={formData.nascimento} onChange={handleChange} placeholder="Data nascimento" required />
                <input name="cpf" value={formData.cpf} onChange={handleChange} placeholder="CPF" required />
                <input name="email" type="email" value={formData.email} onChange={handleChange} placeholder="E-mail" required />
            </div>

            <fieldset className="address-fieldset">
                <legend>Endereço</legend>
                <div className="form-grid-address">
                    <div className="cep-container">
                        <input
                            name="cep"
                            type="text"
                            inputMode="numeric"
                            pattern="[0-9]*"
                            maxLength={8}
                            value={formData.endereco.cep || ''}
                            onChange={handleAddressChange}
                            placeholder="CEP"
                            required />
                        <button type="button" onClick={handleCepSearch} className="search-btn">🔍</button>
                    </div>
                    <input name="rua" value={formData.endereco.rua} onChange={handleAddressChange} placeholder="Rua" required />
                    <input name="numero" type="number" value={formData.endereco.numero || ''} onChange={handleAddressChange} placeholder="Número" required />
                    <input name="cidade" value={formData.endereco.cidade} onChange={handleAddressChange} placeholder="Cidade" required />
                    <input name="estado" value={formData.endereco.estado} onChange={handleAddressChange} placeholder="Estado" required />
                </div>
            </fieldset>

            <button type="submit" className="save-btn">Salvar</button>
        </form>
    );
};
