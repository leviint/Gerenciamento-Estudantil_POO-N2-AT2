CREATE DATABASE GerenciamentoEstudantil;
USE GerenciamentoEstudantil;

CREATE TABLE Pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    tipo ENUM('Estudante', 'Professor') NOT NULL
);

CREATE TABLE Estudante (
    id INT PRIMARY KEY,
    matricula VARCHAR(50) UNIQUE NOT NULL,
    FOREIGN KEY (id) REFERENCES Pessoa(id) ON DELETE CASCADE
);

CREATE TABLE Professor (
    id INT PRIMARY KEY,
    especialidade VARCHAR(100) NOT NULL,
    FOREIGN KEY (id) REFERENCES Pessoa(id) ON DELETE CASCADE
);

CREATE TABLE Curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomeCurso VARCHAR(100) UNIQUE NOT NULL,
    cargaHoraria INT NOT NULL,
    professor_id INT UNIQUE NULL,
    FOREIGN KEY (professor_id) REFERENCES Professor(id)
);

CREATE TABLE Matriculas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estudante_id INT NOT NULL,
    curso_id INT NULL,
    FOREIGN KEY (estudante_id) REFERENCES Estudante(id) ON DELETE CASCADE,
    FOREIGN KEY (curso_id) REFERENCES Curso(id) ON DELETE CASCADE,
    UNIQUE(estudante_id, curso_id)
);

CREATE TABLE Associacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    professor_id INT NOT NULL,
    curso_id INT NOT NULL,
    FOREIGN KEY (professor_id) REFERENCES Professor(id) ON DELETE CASCADE,
    FOREIGN KEY (curso_id) REFERENCES Curso(id) ON DELETE CASCADE,
    UNIQUE(professor_id, curso_id)
);

CREATE INDEX idx_pessoa_nome_tipo ON Pessoa(nome, tipo);
CREATE INDEX idx_curso_nome ON Curso(nomeCurso);
