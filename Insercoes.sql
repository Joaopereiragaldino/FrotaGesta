USE FrotaGesta;

INSERT INTO Usuario (tipo, nome, Stat_us) VALUES 
('Gerente', 'Ana Silva', 'Ativo'),
('Operador', 'Carlos Souza', 'Ativo'),
('Operador', 'Fernanda Lima', 'Ativo');

INSERT INTO Gerente (id_usuario) VALUES 
(1);

INSERT INTO Operador (id_usuario) VALUES 
(2),
(3);

INSERT INTO Veiculo (placa, ano, Stat_us, quilometragem, km_manutencao, modelo) VALUES
('ABC1234', 2022, 'disponivel', 45200, 8200, 'Carro'),
('AAA1A23', 2021, 'manutencao', 68150, 12000, 'Van'),
('ABC4321', 2023, 'disponivel', 22800, 3100, 'Caminhao');

INSERT INTO Motorista (cpf, nome, tipo_CNH, Stat_us) VALUES
('11111111111', 'Joao Martins', 'B', 'disponivel'),
('22222222222', 'Paula Costa', 'D', 'disponivel'),
('33333333333', 'Roberto Carlos', 'C', 'disponivel');

INSERT INTO Abastecimento (placa_veiculo, data_hora, valor_total) VALUES
('ABC1234', '2026-07-01 09:30:00', 150.75),
('ABC4321', '2026-07-03 14:45:00', 180.20);

INSERT INTO Manutencao (Stat_us, data_gerada, descricao, placa_veiculo) VALUES
('Pendente', '2026-07-01 10:00:00', 'Troca de óleo', 'AAA1A23'),
('Concluido', '2026-07-02 14:30:00', 'Revisao dos freios', 'ABC1234');

INSERT INTO Viagem (id_motorista, id_veiculo, id_usuario, quilometragem_inicial, data_hora_saida, Stat_us) VALUES
('11111111111', 'ABC1234', 2, 5200, '2026-07-09 08:00:00', 'Aberta'),
('33333333333', 'ABC4321', 3, 22800, '2026-07-09 09:15:00', 'Aberta');


