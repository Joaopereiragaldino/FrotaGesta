package Classes_DAO;

import java.sql.*;

import Classes_do_banco.Viagem;


public class ViagemDAO {

    public void inserir(Viagem viagem) {
        String insertSql = "INSERT INTO Viagem (id_motorista, id_veiculo, id_usuario, quilometragem_inicial, data_hora_saida, Stat_us) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSql)) {

            // Trocando os '?' pelos dados do objeto viagem
            stmt.setString(1, viagem.getId_motorista());
            stmt.setString(2, viagem.getId_veiculo());
            stmt.setInt(3, viagem.getId_usuario());
            stmt.setInt(4, viagem.getQuilometragem_inicial());

            stmt.setTimestamp(5, Timestamp.valueOf(viagem.getData_hora_saida()));
            
            stmt.setString(6, "Aberta");
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void encerra_viagem(int idViagem, int kmFinal) {
        String callSql = "{call encerra_viagem(?,?)}";
        
        try (Connection conn = Conexao.getConnection();
             CallableStatement stmt = conn.prepareCall(callSql)) {

            // Trocando os '?' pelos dados do objeto Motorista
            stmt.setInt(1, idViagem);
            stmt.setInt(2, kmFinal);

            stmt.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
