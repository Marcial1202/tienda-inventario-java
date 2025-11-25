package capasPresentacion;

import javax.swing.UIManager;

public class AppMain {

    public static void main(String[] args) {
        // 1) Opcional: dejar Nimbus como look & feel (puedes copiarlo del main de alguna ventana)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // si falla, no pasa nada, se queda el look & feel por defecto
            e.printStackTrace();
        }

        // 2) Lanzar la primera ventana de tu sistema (el login)
        java.awt.EventQueue.invokeLater(() -> {
            new acceso().setVisible(true);   // <-- tu JFrame de login
        });
    }
}
