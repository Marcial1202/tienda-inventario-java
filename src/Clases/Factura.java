/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalDate;

/**
 *
 * @author AARON
 */
public class Factura {
    private int idfactura;
    private LocalDate fechaFactura;

    public Factura() {}

    public Factura(int idfactura, LocalDate fechaFactura) {
        this.idfactura = idfactura;
        this.fechaFactura = fechaFactura;
    }

    public int getIdfactura() { return idfactura; }
    public void setIdfactura(int idfactura) { this.idfactura = idfactura; }

    public LocalDate getFechaFactura() { return fechaFactura; }
    public void setFechaFactura(LocalDate fechaFactura) { this.fechaFactura = fechaFactura; }

    @Override
    public String toString() {
        return "Factura{id=" + idfactura + ", fecha=" + fechaFactura + "}";
    }  
}
