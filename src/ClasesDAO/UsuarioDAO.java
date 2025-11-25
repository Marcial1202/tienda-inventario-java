package ClasesDAO;

import Clases.Usuario;
import SQL.Conexion;
import java.sql.*;

public class UsuarioDAO {

     public Usuario buscarPorUsuarioYClave(String username, String password) {
        String sql = "SELECT idusuario, username, password, rol " +
                     "FROM dbo.usuarios " +
                     "WHERE username = ? AND password = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            if (cn == null) {
                throw new SQLException("Conexión nula");
            }

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt("idusuario"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("rol")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("UsuarioDAO.buscarPorUsuarioYClave: " + e.getMessage());
        }

        return null; // no encontrado
    }
}
