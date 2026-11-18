DROP DATABASE IF EXISTS FrotaGesta;
CREATE DATABASE FrotaGesta;
USE FrotaGesta;

DROP TABLE IF EXISTS Veiculo;
CREATE TABLE Veiculo (
    placa VARCHAR(7) PRIMARY KEY NOT NULL,
    ano INT NOT NULL, 
    Stat_us VARCHAR(100) NOT NULL CHECK (Stat_us IN ('disponivel', 'viajando', 'manutencao')),
    quilometragem INT NOT NULL,
    km_manutencao INT NOT NULL,
    modelo VARCHAR(30) NOT NULL CHECK (modelo IN ('Carro', 'Van', 'Caminhao'))
);

DROP TABLE IF EXISTS Motorista;
CREATE TABLE Motorista (
    cpf VARCHAR(11) PRIMARY KEY NOT NULL,
    nome VARCHAR(50) NOT NULL,
    tipo_CNH VARCHAR(2) NOT NULL CHECK (tipo_CNH IN ('A', 'B', 'C', 'D', 'E', 'AB', 'AC', 'AD', 'AE')),
    Stat_us VARCHAR(100) NOT NULL CHECK (Stat_us IN ('disponivel', 'viajando'))
);
DROP TABLE IF EXISTS Usuario;
CREATE TABLE Usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('Operador', 'Gerente')),
    nome VARCHAR(20) NOT NULL,
    Stat_us VARCHAR(20) NOT NULL DEFAULT 'Ativo'
);
DROP TABLE IF EXISTS Operador;
CREATE TABLE Operador (
    id_usuario INT NOT NULL PRIMARY KEY,
    CONSTRAINT fkpk_id_operador FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS Gerente;
CREATE TABLE Gerente (
    id_usuario INT NOT NULL PRIMARY KEY,
    CONSTRAINT fkpk_id_gerente FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Manutencao;
CREATE TABLE Manutencao (
    id_manu INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Stat_us VARCHAR(100) NOT NULL CHECK (Stat_us IN ('Pendente', 'Andamento', 'Concluido')),
    data_gerada DATETIME NOT NULL,
    descricao VARCHAR(100),
    placa_veiculo VARCHAR(7) NOT NULL,
    CONSTRAINT fk_manutencao_veiculo FOREIGN KEY(placa_veiculo) REFERENCES Veiculo (placa) ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Viagem;
CREATE TABLE Viagem (
    id_viagem INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_motorista VARCHAR(11) NOT NULL,
    id_veiculo VARCHAR(7) NOT NULL,
    id_usuario INT NOT NULL,
    quilometragem_inicial INT NOT NULL,
    data_hora_saida DATETIME NOT NULL,
    quilometragem_final INT,
    data_hora_final DATETIME,
    Stat_us VARCHAR(100) CHECK (Stat_us IN ('Aberta', 'Concluida')),
    CONSTRAINT fk_id_motorista FOREIGN KEY (id_motorista) REFERENCES Motorista (cpf) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_id_veiculo FOREIGN KEY (id_veiculo) REFERENCES Veiculo (placa) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario) ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Abastecimento;
CREATE TABLE Abastecimento (
    id_abastecimento INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    placa_veiculo VARCHAR(7) NOT NULL,
    data_hora DATETIME NOT NULL,
    valor_total DECIMAL(8,2) NOT NULL, 
    
    CONSTRAINT fk_abastecimento_veiculo FOREIGN KEY (placa_veiculo) REFERENCES Veiculo (placa) ON DELETE RESTRICT ON UPDATE CASCADE
);
DELIMITER ; 

DROP TRIGGER IF EXISTS validar_ano;
DROP TRIGGER IF EXISTS verifica_manutencao;
DROP TRIGGER IF EXISTS verifica_insercao;
DROP PROCEDURE IF EXISTS encerra_viagem;
DROP TRIGGER IF EXISTS atualiza_status;

DELIMITER //

CREATE TRIGGER validar_ano 
BEFORE INSERT ON Veiculo
FOR EACH ROW
BEGIN
    IF NEW.ano < 1769 OR NEW.ano > YEAR(CURRENT_DATE()) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ano do veiculo invalido';
    END IF;
END//

CREATE TRIGGER verifica_manutencao 
BEFORE UPDATE ON Veiculo
FOR EACH ROW
BEGIN
    IF NEW.km_manutencao >= 10000 THEN 
        INSERT INTO Manutencao (Stat_us, data_gerada, descricao, placa_veiculo) 
        VALUES ('Pendente', CURRENT_DATE(), 'manutencao preventiva', NEW.placa);
        
        SET NEW.km_manutencao = 0;
        SET NEW.Stat_us = 'manutencao';
    END IF; 
END//

CREATE TRIGGER verifica_insercao
BEFORE INSERT ON Viagem
FOR EACH ROW
BEGIN
    DECLARE tipo_veiculo VARCHAR(30);
    DECLARE tipo_cnh VARCHAR(2);
    DECLARE stat_us_motorista VARCHAR(100);
    DECLARE stat_us_veiculo VARCHAR(100);
    
    SELECT v.modelo, v.stat_us INTO tipo_veiculo, stat_us_veiculo FROM Veiculo v WHERE NEW.id_veiculo = v.placa;
    SELECT m.tipo_CNH, m.stat_us INTO tipo_cnh, stat_us_motorista FROM Motorista m WHERE NEW.id_motorista = m.cpf;

    IF tipo_veiculo = 'Carro' AND tipo_cnh NOT IN('B', 'C', 'D', 'E', 'AB', 'AC', 'AD', 'AE') THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'CNH incompatível com o veículo';
    ELSEIF tipo_veiculo = 'Van' AND tipo_cnh NOT IN('D', 'E', 'AD', 'AE') THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'CNH incompatível com o veículo';
    ELSEIF tipo_veiculo = 'Caminhao' AND tipo_cnh NOT IN ('C', 'D', 'E', 'AC', 'AD', 'AE') THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'CNH incompatível com o veículo';
    ELSEIF stat_us_veiculo = 'viajando' OR stat_us_veiculo = 'manutencao' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Veículo indisponível para uso';
    ELSEIF stat_us_motorista = 'viajando' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Motorista já está em uma viagem';
    END IF;
END//

CREATE PROCEDURE encerra_viagem(
    IN viagem_id INT,
    IN quilo_final INT
)
BEGIN
    DECLARE quilo_ini INT;
    DECLARE v_placa VARCHAR(7);
    DECLARE m_cpf VARCHAR(11);
    
    START TRANSACTION;

    SELECT quilometragem_inicial, id_veiculo, id_motorista INTO quilo_ini, v_placa, m_cpf
    FROM Viagem 
    WHERE id_viagem = viagem_id;
    
    IF v_placa IS NULL THEN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Viagem não encontrada';
    ELSEIF quilo_final < quilo_ini THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Quilometragem final menor que a inicial';
    ELSE
        UPDATE Viagem 
        SET quilometragem_final = quilo_final, data_hora_final = NOW(), Stat_us = 'Concluida' 
        WHERE id_viagem = viagem_id;

        UPDATE Veiculo 
        SET quilometragem = quilo_final, 
            km_manutencao = km_manutencao + (quilo_final - quilo_ini), 
            Stat_us = 'disponivel' 
        WHERE placa = v_placa;

        UPDATE Motorista 
        SET Stat_us='disponivel'
        WHERE cpf = m_cpf;

        COMMIT;
    END IF;
END//

CREATE TRIGGER atualiza_status
AFTER INSERT ON Viagem
FOR EACH ROW
BEGIN
    UPDATE Veiculo SET Stat_us = 'viajando' WHERE placa = NEW.id_veiculo;
    UPDATE Motorista SET Stat_us = 'viajando' WHERE cpf = NEW.id_motorista;
END//

DELIMITER ;