package Classes_DAO;

import Classes_do_banco.Abastecimento;

import java.sql.*;

public class AbastecimentoDAO {

    public void inserir(Abastecimento abastecimento) {
        String sql = "INSERT INTO Abastecimento (placa_veiculo, data_hora, valor_total) VALUES (?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Trocando os '?' pelos dados do objeto abastecimento
            stmt.setString(1, abastecimento.getPlaca_veiculo());
            stmt.setTimestamp(2, Timestamp.valueOf(abastecimento.getData_hora()));
            stmt.setDouble(3, abastecimento.getValor_total());
            
            stmt.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}