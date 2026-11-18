package Classes_DAO;

import java.sql.*;

public class Conexao {
    
    // Credenciais do banco
    private static final String URL = "jdbc:mysql://localhost:3306/FrotaGesta";
    private static final String USUARIO = "root";
    private static final String SENHA = "aluno"; 

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco: " + e.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        try {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}

