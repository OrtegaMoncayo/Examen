/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 59397
 */
public class Producto {
     private int idProducto;
    private String nombreProducto;
    private String marca;
    private int stock;
    private double precio;
    private int codigo;

    public Producto() {
    }

    public Producto(int idProducto, String nombreProducto, String marca, int stock, double precio, int codigo) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.marca = marca;
        this.stock = stock;
        this.precio = precio;
        this.codigo = codigo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
      public String imprimir() {
        return "DATOS PERSONALES\n"
                + "NOMBRE del Producto;" + getNombreProducto() + "\n"
                + "Marca del Producto:" + getMarca() + "\n"
                + "Stock disponible:" + getStock() + "\n"
                + "Precio del Producto:" + getPrecio() + "\n"
                + "Codigo del Producto:" + getCodigo() + "\n";
    }
    
    
}
