package capasPresentacion;

public class PruebaFactura {
    public static void main(String[] args) {
        // Prueba con un idfactura que exista en tu tabla dbo.factura
        FacturaFrame f = new FacturaFrame(1, "Cliente de prueba");
        f.setVisible(true);
    }
}
