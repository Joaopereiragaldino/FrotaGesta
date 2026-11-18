package Classes_DAO;

import java.sql.*;

import Classes_do_banco.Usuario;

public class UsuarioDAO {

    public void inserir(Usuario usuario) {
        String insertSql = "INSERT INTO Usuario (nome, tipo, Stat_us) VALUES (?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTipo());
            stmt.setString(3, usuario.getStat_us());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public void deletar(int idUsuario) {
           String deleteSql = "DELETE FROM Usuario WHERE id_usuario = ?";
        
        try (Connection conn = Conexao.getConnection();
               PreparedStatement stmt = conn.prepareStatement(deleteSql)) {
            
            stmt.setInt(1, idUsuario);
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}