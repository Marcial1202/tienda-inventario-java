/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.math.BigDecimal;

/**
 *
 * @author AARON
 */
public class Detalle {
     private int iddetalle;
    private int idfactura;
    private int idproducto;
    private int cantidad;
    private BigDecimal precioUnitario;

    public Detalle() {}

    public Detalle(int iddetalle, int idfactura, int idproducto, int cantidad, BigDecimal precioUnitario) {
        this.iddetalle = iddetalle;
        this.idfactura = idfactura;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIddetalle() { return iddetalle; }
    public void setIddetalle(int iddetalle) { this.iddetalle = iddetalle; }

    public int getIdfactura() { return idfactura; }
    public void setIdfactura(int idfactura) { this.idfactura = idfactura; }

    public int getIdproducto() { return idproducto; }
    public void setIdproducto(int idproducto) { this.idproducto = idproducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }

    public BigDecimal getImporte() {
        return precioUnitario.multiply(new BigDecimal(cantidad));
    }

    @Override
    public String toString() {
        return "Detalle{id=" + iddetalle + ", factura=" + idfactura + ", producto=" + idproducto +
               ", cant=" + cantidad + ", pu=" + precioUnitario + "}";
    } 
}
