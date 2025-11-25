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
public class Producto {
    private int idproducto;
    private String nombre;
    private BigDecimal precioProducto;
    private int stock;
    private boolean activo;

    public Producto() {}

    public Producto(int idproducto, String nombre, BigDecimal precioProducto, int stock,boolean activo) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.precioProducto = precioProducto;
        this.stock = stock;
        this.activo = activo;
    }
    public Producto(int idproducto, String nombre, BigDecimal precioProducto, int stock) {
    this(idproducto, nombre, precioProducto, stock, true); // activo por defecto
}


    public int getIdproducto() { return idproducto; }
    public void setIdproducto(int idproducto) { this.idproducto = idproducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public BigDecimal getPrecioProducto() { return precioProducto; }
    public void setPrecioProducto(BigDecimal precioProducto) { this.precioProducto = precioProducto; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    
    public boolean isActivo() {
    return activo;
}
public void setActivo(boolean activo) {
    this.activo = activo;
}

    @Override
    public String toString() {
        return "Producto{id=" + idproducto + ", nombre='" + nombre + "', precio=" + precioProducto + ", stock=" + stock +  ", activo=" + activo + "}";
    }
}
