/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
import Modelo.Proveedor;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author 59397
 */
public class ProductoControlador {

    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void crearProducto(Producto p) {
        try {
            String consultaSQL = "INSERT INTO producto (prod_nombre, stock, prod_precio, prod_marca,codigo) VALUES (?, ?, ?, ?,?)";
            ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, p.getNombreProducto());
            ejecutar.setInt(2, p.getStock());
            ejecutar.setDouble(3, p.getPrecio());
            ejecutar.setString(4, p.getMarca());
            ejecutar.setInt(5, p.getCodigo());
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("EL PEDIDO HA SIDO CREADA CON ÉXITO");
                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
                ejecutar.close();
            }
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }

    public Producto buscarDatosProducto(int codigo) {
        Producto pr = new Producto();
        try {
            String consultaSQL = "SELECT * FROM producto p "
                    + "WHERE p.codigo = ?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setInt(1, codigo);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {

                pr.setNombreProducto(resultado.getString("nombreProducto"));
                pr.setMarca(resultado.getString("marca"));
                pr.setPrecio(resultado.getDouble("precio"));
                pr.setStock(resultado.getInt("stock"));
                pr.setCodigo(resultado.getInt("codigo"));

                pr.imprimir();
                resultado.close();
                return pr;
            } else {
                System.out.println("Ingrese una cédula válida");
                resultado.close();
            }
        } catch (Exception e) {
            System.out.println("Comuníquese con el administrador" + e);
        }
        return pr;

    }

    public void ActualisarProducto(int codigo, Producto p) {
        try {
            String consultaSQL = "UPDATE producto SET "
                    + "prod_nombre= '?' ,stock ='?'"
                    + ",prod_precio='?',prod_marca='?'"
                    + ",codigo='?'"
                    + "WHERE "
                    + "codigo = (SELECT codigo FROM producto WHERE codigo = '" + codigo + "')";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, p.getNombreProducto());
            ejecutar.setInt(2, p.getStock());
            ejecutar.setDouble(3, p.getPrecio());
            ejecutar.setString(4, p.getMarca());
            ejecutar.setInt(5, codigo);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("LA PERSONA HA SIDO CREADA CON ÉXITO");
                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
                ejecutar.close();
            }

        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }

    public void eliminarProducto(int codigo) {
        try {
            String consultaSQL = "DELETE FROM producto WHERE codigo='?';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setInt(1, codigo);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("LA PERSONA HA SIDO ELIMINADA");
                ejecutar.close();
            } else {
                System.out.println("ERROR AL ELIMINAR PERSONA");
            }
        } catch (Exception e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }

    }

    public Producto buscarProducto(int codigo) {
        Producto pr = new Producto();
        try {
            String consultaSQL = "SELECT * FROM productos WHERE codigo = ?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setInt(1, codigo);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {

                pr.setNombreProducto(resultado.getString("nombreProducto"));
                pr.setMarca(resultado.getString("marca"));
                pr.setPrecio(resultado.getDouble("precio"));
                pr.setStock(resultado.getInt("stock"));
                pr.setCodigo(resultado.getInt("codigo"));

                pr.imprimir();
                resultado.close();

            } else {
                System.out.println("Ingrese una cédula válida");
                resultado.close();
            }
        } catch (Exception e) {
            System.out.println("Comuníquese con el administrador" + e);
        }
        return pr;

    }

    public ArrayList<Producto> listaProducto() {
        ArrayList<Producto> listaProducto = new ArrayList<>();

        try {
            String consultaSQL = "SELECT * FROM productos WHERE codigo = ?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            while (resultado.next()) {
                Producto prod = new Producto();

                prod.setNombreProducto(resultado.getString("prod_nombre"));
                prod.setMarca(resultado.getString("prod_marca"));
                prod.setPrecio(resultado.getDouble("prod_precio"));
                prod.setStock(resultado.getInt("stock"));
                prod.setCodigo(resultado.getInt("codigo"));

                listaProducto.add(prod);

            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de clientes: " + e);

        }

        return listaProducto;
    }

}
