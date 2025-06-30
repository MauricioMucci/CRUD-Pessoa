export interface EnderecoDTO {
    cep: number;
    rua: string;
    numero: number;
    cidade: string;
    estado: string;
}

export interface PessoaInputDTO {
    nome: string;
    nascimento: string; // Formato YYYY-MM-DD
    cpf: string;
    email: string;
    endereco: EnderecoDTO;
}

export interface PessoaOutputDTO {
    nome: string;
    nascimento: string; // Formato YYYY-MM-DD
    cpf: string;
    email: string;
    endereco: EnderecoDTO;
    dataHoraInclusaoRegistro: string; // Formato ISO 8601
    dataHoraUltimaAlteracaoRegistro: string; // Formato ISO 8601
}

export interface CreatePessoaDTO {
    idPessoa: number;
    mensagem: string;
}

export interface DeletePessoaDTO {
    mensagem: string;
}
