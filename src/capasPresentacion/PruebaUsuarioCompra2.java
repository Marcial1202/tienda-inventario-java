package capasPresentacion;



public class PruebaUsuarioCompra2 {

    public static void main(String[] args) {
        // Arrancar la ventana en el hilo de Swing
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new usuarioCompra2().setVisible(true);
            }
        });
    }
}
