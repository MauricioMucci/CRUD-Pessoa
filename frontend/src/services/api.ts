import type {PessoaInputDTO, PessoaOutputDTO, EnderecoDTO, CreatePessoaDTO, DeletePessoaDTO} from '../types';

const API_BASE_URL = 'http://localhost:8080';

async function handleResponse<T>(response: Response): Promise<T>;
async function handleResponse(response: Response): Promise<void>;

/**
 * Função auxiliar para tratar respostas da API
 * @param response
 */
async function handleResponse<T>(response: Response): Promise<T | void> {
    if (!response.ok) {
        const error = await response.json();
        throw new Error(error.message || 'Ocorreu um erro na requisição.');
    }

    const text = await response.text();
    if (text) {
        return JSON.parse(text) as T;
    }
}

/**
 * Busca o endereço a partir de um CEP.
 * GET /endereco/cep/{cep}
 */
export const fetchAddressByCep = async (cep: string): Promise<EnderecoDTO> => {
    const response = await fetch(`${API_BASE_URL}/endereco/cep/${cep}`);
    return handleResponse<EnderecoDTO>(response);
};

/**
 * Busca a lista de todas as pessoas cadastradas.
 * GET /pessoa/list
 */
export const fetchPeople = async (): Promise<PessoaOutputDTO[]> => {
    const response = await fetch(`${API_BASE_URL}/pessoa/list`);
    return handleResponse<PessoaOutputDTO[]>(response);
};

/**
 * Busca uma pessoa específica pelo CPF.
 * GET /pessoa/cpf/{cpf}
 */
export const fetchPersonByCpf = async (cpf: string): Promise<PessoaOutputDTO> => {
    const response = await fetch(`${API_BASE_URL}/pessoa/cpf/${cpf}`);
    // O ResponseEntity.of pode retornar um corpo vazio com status 200/204 se não encontrar
    if (response.status === 200 && response.headers.get('content-length') === '0') {
        throw new Error('Pessoa não encontrada.');
    }
    return handleResponse<PessoaOutputDTO>(response);
};


/**
 * Cria uma nova pessoa.
 * POST /pessoa
 */
export const createPerson = async (personData: PessoaInputDTO): Promise<CreatePessoaDTO> => {
    const response = await fetch(`${API_BASE_URL}/pessoa`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(personData),
    });
    return handleResponse<CreatePessoaDTO>(response);
};

/**
 * Deleta uma pessoa pelo CPF.
 * DELETE /pessoa/cpf/{cpf}
 */
export const deletePerson = async (cpf: string): Promise<DeletePessoaDTO> => {
    const response = await fetch(`${API_BASE_URL}/pessoa/cpf/${cpf}`, {
        method: 'DELETE',
    });
    return handleResponse<DeletePessoaDTO>(response);
};
