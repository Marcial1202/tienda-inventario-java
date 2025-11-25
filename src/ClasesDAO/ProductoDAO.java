package Clasesdao;

import Clases.Producto;
import SQL.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

 public boolean insertar(Producto p) {
    String sqlInsert = "INSERT INTO dbo.producto (nombre, precioProducto, stock) " +
                       "VALUES (?, ?, ?)";

    System.out.println("SQL que se va a ejecutar en insertar(): " + sqlInsert);

    try (Connection cn = Conexion.getConexion()) {

        if (cn == null) {
            throw new SQLException("Conexión nula");
        }

        try (PreparedStatement ps = cn.prepareStatement(sqlInsert)) {
            ps.setString(1, p.getNombre());
            ps.setBigDecimal(2, p.getPrecioProducto());
            ps.setInt(3, p.getStock());

            int filas = ps.executeUpdate();
            return filas > 0;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(
            null,
            "Error SQL en insertar:\n" + e.getMessage(),
            "Error BD",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
        return false;
    }
}




  public boolean actualizar(Producto p) {
    String sql = "UPDATE dbo.producto " +
                 "SET nombre = ?, precioProducto = ?, stock = ?, activo = ? " +
                 "WHERE idproducto = ?";

    try (Connection cn = Conexion.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, p.getNombre());
        ps.setBigDecimal(2, p.getPrecioProducto());
        ps.setInt(3, p.getStock());
        ps.setBoolean(4, p.isActivo());
        ps.setInt(5, p.getIdproducto());

        int filas = ps.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        System.out.println("ProductoDAO.actualizar: " + e.getMessage());
        return false;
    }
}


    public boolean eliminar(int idproducto) {
        String sql = "DELETE FROM dbo.producto WHERE idproducto=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            if (cn == null) throw new SQLException("Conexión nula");

            ps.setInt(1, idproducto);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("ProductoDAO.eliminar: " + e.getMessage());
            return false;
        }
    }

    public Producto buscarPorId(int idproducto) {
        String sql = "SELECT idproducto, nombre, precioProducto, stock FROM dbo.producto WHERE idproducto=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            if (cn == null) throw new SQLException("Conexión nula");

            ps.setInt(1, idproducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                        rs.getInt("idproducto"),
                        rs.getString("nombre"),
                        rs.getBigDecimal("precioProducto"),
                        rs.getInt("stock")
                            
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("ProductoDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }
    // Sumar stock a un producto
public boolean agregarStock(int idproducto, int cantidadASumar) {
    String sql = "UPDATE dbo.producto SET stock = stock + ? WHERE idproducto = ?";

    try (Connection cn = Conexion.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        if (cn == null) throw new SQLException("Conexión nula");

        ps.setInt(1, cantidadASumar);
        ps.setInt(2, idproducto);

        return ps.executeUpdate() == 1;

    } catch (SQLException e) {
        System.out.println("ProductoDAO.agregarStock: " + e.getMessage());
        return false;
    }
}

// Lista de productos con stock bajo (<= umbral)
public List<Producto> listarConStockBajo(int umbral) {
    List<Producto> lista = new ArrayList<>();
    String sql = "SELECT idproducto, nombre, precioProducto, stock " +
                 "FROM dbo.producto WHERE stock <= ? ORDER BY stock ASC";

    try (Connection cn = Conexion.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        if (cn == null) throw new SQLException("Conexión nula");

        ps.setInt(1, umbral);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("idproducto"),
                    rs.getString("nombre"),
                    rs.getBigDecimal("precioProducto"),
                    rs.getInt("stock")
                ));
            }
        }

    } catch (SQLException e) {
        System.out.println("ProductoDAO.listarConStockBajo: " + e.getMessage());
    }
    return lista;
}


 public List<Producto> listarTodos() {
    List<Producto> lista = new ArrayList<>();
    String sql = "SELECT idproducto, nombre, precioProducto, stock, activo " +
                 "FROM dbo.producto ORDER BY nombre";

    try (Connection cn = Conexion.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (cn == null) throw new SQLException("Conexión nula");

        while (rs.next()) {
            Producto p = new Producto();
            p.setIdproducto(rs.getInt("idproducto"));
            p.setNombre(rs.getString("nombre"));
            p.setPrecioProducto(rs.getBigDecimal("precioProducto"));
            p.setStock(rs.getInt("stock"));
            p.setActivo(rs.getBoolean("activo"));  // <- aquí usamos la columna
            lista.add(p);
        }

    } catch (SQLException e) {
        System.out.println("ProductoDAO.listarTodos: " + e.getMessage());
    }
    return lista;
}



    public List<Producto> listarParaUsuario() {
    List<Producto> lista = new ArrayList<>();

    String sql = "SELECT idproducto, nombre, precioProducto, stock, activo " +
                 "FROM dbo.producto " +
                 "WHERE activo = 1 AND stock > 0 " +  // 👈 solo activos y con stock
                 "ORDER BY nombre";

    try (Connection cn = Conexion.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            lista.add(new Producto(
                rs.getInt("idproducto"),
                rs.getString("nombre"),
                rs.getBigDecimal("precioProducto"),
                rs.getInt("stock"),
                rs.getBoolean("activo")
            ));
        }

    } catch (SQLException e) {
        System.out.println("ProductoDAO.listarParaUsuario: " + e.getMessage());
    }

    return lista;
}
    
    public boolean registrarVenta(int idproducto, int cantidad) {
    String sql = "UPDATE dbo.producto " +
                 "SET stock = stock - ? " +
                 "WHERE idproducto = ? AND stock >= ?";

    try (Connection cn = Conexion.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        if (cn == null) {
            throw new SQLException("Conexión nula");
        }

        ps.setInt(1, cantidad);      // stock = stock - cantidad
        ps.setInt(2, idproducto);    // producto a actualizar
        ps.setInt(3, cantidad);      // solo si hay stock suficiente

        int filas = ps.executeUpdate();
        return filas > 0;            // true si se actualizó

    } catch (SQLException e) {
        System.out.println("ProductoDAO.registrarVenta: " + e.getMessage());
        return false;
    }
}


}


