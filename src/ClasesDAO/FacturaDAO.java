package Clasesdao;

import Clases.Factura;
import SQL.Conexion;

import java.sql.*;
import java.time.LocalDate;

public class FacturaDAO {

    public boolean insertar(Factura f) {
        String sql = "INSERT INTO dbo.factura (idfactura, fechaFactura) VALUES (?,?)";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            if (cn == null) throw new SQLException("Conexión nula");

            ps.setInt(1, f.getIdfactura());
            ps.setDate(2, Date.valueOf(f.getFechaFactura()));
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("FacturaDAO.insertar: " + e.getMessage());
            return false;
        }
    }

    public Factura buscarPorId(int idfactura) {
        String sql = "SELECT idfactura, fechaFactura FROM dbo.factura WHERE idfactura=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            if (cn == null) throw new SQLException("Conexión nula");

            ps.setInt(1, idfactura);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Factura(
                        rs.getInt("idfactura"),
                        rs.getDate("fechaFactura").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("FacturaDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public boolean eliminar(int idfactura) {
        String sql = "DELETE FROM dbo.factura WHERE idfactura=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            if (cn == null) throw new SQLException("Conexión nula");

            ps.setInt(1, idfactura);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("FacturaDAO.eliminar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarFecha(int idfactura, LocalDate nuevaFecha) {
        String sql = "UPDATE dbo.factura SET fechaFactura=? WHERE idfactura=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            if (cn == null) throw new SQLException("Conexión nula");

            ps.setDate(1, Date.valueOf(nuevaFecha));
            ps.setInt(2, idfactura);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("FacturaDAO.actualizarFecha: " + e.getMessage());
            return false;
        }
    }
    
    
    public int crearFacturaHoy() {
        String sqlGetId = "SELECT ISNULL(MAX(idfactura), 0) + 1 AS nuevoId FROM dbo.factura";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement psId = cn.prepareStatement(sqlGetId);
             ResultSet rs = psId.executeQuery()) {

            if (cn == null) {
                throw new SQLException("Conexión nula");
            }

            int nuevoId = 1;
            if (rs.next()) {
                nuevoId = rs.getInt("nuevoId");
            }

            // Crear objeto Factura con fecha de hoy
            Factura f = new Factura(nuevoId, java.time.LocalDate.now());

            boolean ok = insertar(f);  // usa tu método insertar(Factura f)

            return ok ? nuevoId : -1;

        } catch (SQLException e) {
            System.out.println("FacturaDAO.crearFacturaHoy: " + e.getMessage());
            return -1;
        }
    }

    
}
