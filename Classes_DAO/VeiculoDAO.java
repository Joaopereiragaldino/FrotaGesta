package Classes_DAO;

import java.sql.*;

import Classes_do_banco.Veiculo;

public class VeiculoDAO {

    public void inserir(Veiculo veiculo) {
    String insertSql = "INSERT INTO Veiculo (placa, ano, stat_us, quilometragem, km_manutencao, modelo) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmt = conn.prepareStatement(insertSql)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setInt(2, veiculo.getAno());
            stmt.setString(3, veiculo.getStat_us());
            stmt.setInt(4, veiculo.getQuilometragem());
            stmt.setInt(5, veiculo.getKm_manutencao());
            stmt.setString(6, veiculo.getModelo());

            stmt.execute();

    }   catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }  
     
    public void deletar(String placa) {
           String deleteSql = "DELETE FROM Veiculo WHERE placa = ?";
        
        try (Connection conn = Conexao.getConnection();
               PreparedStatement stmt = conn.prepareStatement(deleteSql)) {
            
            stmt.setString(1, placa);
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}