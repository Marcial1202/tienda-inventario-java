package market;

import capasPresentacion.acceso;   // <--- importa tu ventana de login

public class Market {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new acceso().setVisible(true);   // <--- abre la ventana
        });
    }
}
