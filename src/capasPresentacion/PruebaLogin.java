package capasPresentacion;   // deja el mismo package que tengas

public class PruebaLogin {
    public static void main(String[] args) {

        // Lanza el login en el hilo de Swing
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Cambia "Login" por el nombre REAL de tu JFrame de login
                new acceso().setVisible(true);
            }
        });
    }
}
