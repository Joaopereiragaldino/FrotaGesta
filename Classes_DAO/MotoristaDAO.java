package Classes_DAO;

import java.sql.*;

import Classes_do_banco.Motorista;

public class MotoristaDAO {

    public void inserir(Motorista motorista) {
        String sql = "INSERT INTO Motorista (cpf,nome,tipo_CNH,Stat_us) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Trocando os '?' pelos dados do objeto Motorista
            stmt.setString(1, motorista.getCpf());
            stmt.setString(2, motorista.getNome());
            stmt.setString(3, motorista.getTipo_CNH());
            stmt.setString(4, motorista.getStat_us());
            
            stmt.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deletar(String cpf) {
           String deleteSql = "DELETE FROM Motorista WHERE cpf = ?";
        
        try (Connection conn = Conexao.getConnection();
               PreparedStatement stmt = conn.prepareStatement(deleteSql)) {
            
            stmt.setString(1, cpf);
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
