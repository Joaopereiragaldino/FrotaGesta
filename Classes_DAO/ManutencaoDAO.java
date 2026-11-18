package Classes_DAO;

import java.sql.*;

import Classes_do_banco.Manutencao;

public class ManutencaoDAO {

    public void inserir(Manutencao manutencao) {
        String sql = "INSERT INTO Manutencao (Stat_us, data_gerada, descricao, placa_veiculo) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Trocando os '?' pelos dados do objeto manutencao
            stmt.setString(1, manutencao.getStat_us());
            stmt.setTimestamp(2, Timestamp.valueOf(manutencao.getData_gerada()));
            stmt.setString(3, manutencao.getDescricao());
            stmt.setString(4, manutencao.getPlaca_veiculo());
            
            stmt.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void concluirManutencao(String placaVeiculo) {

        String updateManutencaoSql = "UPDATE Manutencao SET Stat_us = 'Concluido' WHERE placa_veiculo = ? AND Stat_us IN ('Pendente', 'Andamento')";
        String updateVeiculoSql = "UPDATE Veiculo SET Stat_us = 'disponivel' WHERE placa = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmtManu = conn.prepareStatement(updateManutencaoSql);
             PreparedStatement stmtVei = conn.prepareStatement(updateVeiculoSql)) {

            conn.setAutoCommit(false); 

            try {
                // Atualiza a tabela de Manutenção
                stmtManu.setString(1, placaVeiculo);
                stmtManu.executeUpdate();

                stmtVei.setString(1, placaVeiculo);
                stmtVei.executeUpdate();

                conn.commit(); // Confirma a transação

            } catch (SQLException ex) {
                conn.rollback(); // Rollback
                throw new RuntimeException("Erro ao processar transação: " + ex.getMessage());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro de conexão com o banco: " + e.getMessage());
        }
    }
}
