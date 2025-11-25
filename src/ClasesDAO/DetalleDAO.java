package Clasesdao;

import Clases.Detalle;
import SQL.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleDAO {

    // ================== INSERTAR DETALLE ==================
    public boolean insertar(Detalle d) {
        String sql = "INSERT INTO dbo.detalle " +
                     "(idfactura, idproducto, cantidad, precioUnitario) " +
                     "VALUES (?, ?, ?, ?)";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (cn == null) {
                throw new SQLException("Conexión nula");
            }

            // OJO: ya NO mandamos iddetalle
            ps.setInt(1, d.getIdfactura());
            ps.setInt(2, d.getIdproducto());
            ps.setInt(3, d.getCantidad());
            ps.setBigDecimal(4, d.getPrecioUnitario());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                return false;
            }

            // Leer el iddetalle generado (opcional, pero útil)
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int nuevoId = rs.getInt(1);
                    d.setIddetalle(nuevoId);
                }
            }

            return true;

        } catch (SQLException e) {
            System.out.println("DetalleDAO.insertar: " + e.getMessage());
            return false;
        }
    }

    // ================== LISTAR POR FACTURA ==================
    public List<Detalle> listarPorFactura(int idfactura) {
        List<Detalle> lista = new ArrayList<>();
        String sql = "SELECT iddetalle, idfactura, idproducto, cantidad, precioUnitario " +
                     "FROM dbo.detalle WHERE idfactura=? ORDER BY iddetalle";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            if (cn == null) throw new SQLException("Conexión nula");

            ps.setInt(1, idfactura);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Detalle(
                            rs.getInt("iddetalle"),
                            rs.getInt("idfactura"),
                            rs.getInt("idproducto"),
                            rs.getInt("cantidad"),
                            rs.getBigDecimal("precioUnitario")
                    ));
                }
            }

        } catch (SQLException e) {
            System.out.println("DetalleDAO.listarPorFactura: " + e.getMessage());
        }

        return lista;
    }

    // ================== ELIMINAR DETALLE ==================
    public boolean eliminar(int iddetalle) {
        String sql = "DELETE FROM dbo.detalle WHERE iddetalle=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            if (cn == null) throw new SQLException("Conexión nula");

            ps.setInt(1, iddetalle);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("DetalleDAO.eliminar: " + e.getMessage());
            return false;
        }
    }
}
