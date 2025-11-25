package pruebas;

import Clasesdao.ProductoDAO;
import Clases.Producto;
import java.util.List;

public class TestProductoDAO {

    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();

        // Probar listar todos los productos
        List<Producto> lista = dao.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("La lista está vacía :(");
        } else {
            System.out.println("Productos en la base de datos:");
            for (Producto p : lista) {
                System.out.println(p);
            }
        }
    }
}
