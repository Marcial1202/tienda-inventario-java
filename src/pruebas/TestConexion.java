
package pruebas;

import SQL.Conexion;

import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        Connection cn = Conexion.getConexion();
        System.out.println("cn = " + cn);
    }
}
