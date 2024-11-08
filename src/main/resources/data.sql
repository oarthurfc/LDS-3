-- Inserindo Alunos
INSERT INTO aluno (nome, email, senha, cpf, rg, endereco, instituicao, curso, saldo_moedas) VALUES 
('Ana Souza', 'ana.souza@email.com', 'senha123', '12345678901', 'MG1234567', 'Rua das Flores, 100', 'PUC Minas', 'Engenharia de Software', 100),
('Bruno Lima', 'bruno.lima@email.com', 'senha123', '98765432100', 'SP7654321', 'Avenida Central, 200', 'UFPR', 'Ciência da Computação', 50),
('Carlos Mendes', 'carlos.mendes@email.com', 'senha123', '45678912300', 'RJ5432100', 'Rua do Sol, 300', 'UFSC', 'Sistemas de Informação', 75);

-- Inserindo Professores
INSERT INTO professor (nome, email, senha, cpf, departamento, saldo_moedas) VALUES 
('Mariana Pereira', 'mariana.pereira@universidade.com', 'senha123', '32165498700', 'Computação', 1000),
('José Santos', 'jose.santos@universidade.com', 'senha123', '65432198700', 'Engenharia', 1200);

-- Inserindo Empresas
INSERT INTO empresa (nome, email, senha, cnpj) VALUES 
('Tech Solutions', 'contato@techsolutions.com', 'senha123', '12345678000123'),
('EduBooks', 'contato@edubooks.com', 'senha123', '98765432000198');

-- Inserindo Vantagens
INSERT INTO vantagem (descricao, foto, custo, empresa_id) VALUES 
('Desconto em restaurantes da universidade', 'foto1.jpg', 50, 1),
('Desconto de mensalidade', 'foto2.jpg', 100, 1),
('Desconto de 10% em livros acadêmicos', 'foto3.jpg', 30, 2),
('Acesso premium a conteúdos exclusivos', 'foto4.jpg', 40, 2);
