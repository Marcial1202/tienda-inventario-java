package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver JDBC cargado correctamente.");

           String conexionUrl =
    "jdbc:sqlserver://localhost:1433;" +
    "databaseName=Tienda;" +
    "user=sa;" +
    "password=12345;" +
    "encrypt=true;" +
    "trustServerCertificate=true;" +
    "loginTimeout=30;";


            Connection con = DriverManager.getConnection(conexionUrl);
            System.out.println("Conexión establecida con éxito.");
            return con;

        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver JDBC: " + ex.toString());
            return null;
        } catch (SQLException ex) {
            System.out.println("Error de conexión: " + ex.toString());
            return null;
        }
    }
}
