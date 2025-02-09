/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Persona;
import Modelo.Proveedor;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author 59397
 */
public class ProveedorControlador {

    private Proveedor proveedor;

    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void crearProveedor(Proveedor pr) {
        try {
            String consultaSQL = "INSERT INTO proveedor(contacto, idpersona) VALUES (?, ?)";
            ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, pr.getContacto());
            ejecutar.setInt(2, pr.getIdPersona());
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

    public int buscarIdPersonaProveedor(String cedula) {
        try {
            String consultaSQL = "select idpersona from persona where cedula='" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                int idPersonas = resultado.getInt("idPersona");
                return idPersonas;
            } else {
                System.out.println("ingrese una cedula valida ");
            }

        } catch (Exception e) {
            System.out.println("COMUNIQUESE CON EL ALMINISTRADOR DEL SISTEMA PARA EL SISTEMA" + e);
        }
        return 0;

    }

    public Proveedor buscarDatosProveedor(String cedula) {
        Proveedor pr = new Proveedor();
        try {
            String consultaSQL = "SELECT * FROM persona p "
                    + "JOIN proveedor pr ON p.idpersona = pr.idpersona "
                    + "WHERE p.cedula = '" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {

                pr.setNombre(resultado.getString("nombres"));
                pr.setApellido(resultado.getString("apellidos"));
                pr.setCedula(resultado.getString("cedula"));
                pr.setDireccion(resultado.getString("direccion"));
                pr.setFechaNacimiento(resultado.getString("fechaNacimiento"));
                pr.setTelefono(resultado.getString("telefono"));
                pr.setContacto(resultado.getString("contacto"));

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

    public void ActualisarProveedor(String cedula, Proveedor pr, Persona p) {
        try {
            String consultaSQLPersona = "UPDATE persona SET cedula = ?, nombres = ?, apellidos = ?, direccion = ?, fechaNacimiento = ?, telefono = ? WHERE cedula = ?";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQLPersona);
            ejecutar.setString(1, cedula);
            ejecutar.setString(2, p.getNombre());
            ejecutar.setString(3, p.getApellido());
            ejecutar.setString(4, p.getDireccion());
            ejecutar.setString(5, p.getFechaNacimiento());
            ejecutar.setString(6, p.getTelefono());

            int resPersona = ejecutar.executeUpdate();

            if (resPersona > 0) {
                System.out.println("LOS DATOS PERSONALES HAN SIDO ACTUALIZADOS CON ÉXITO");

                // Luego actualizamos la tabla 'cliente'
                String consultaSQL = "UPDATE proveedor SET contacto = ? WHERE idpersona = (SELECT idpersona FROM persona WHERE cedula = ?)";
                ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
                ejecutar.setString(1, pr.getContacto());
                ejecutar.setString(2, cedula);

                int resCliente = ejecutar.executeUpdate();

                if (resCliente > 0) {
                    System.out.println("EL CARNET DE PROMOCIÓN HA SIDO ACTUALIZADO CON ÉXITO");
                } else {
                    System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
                }

                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
            }

            ejecutar.close();

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }

    public void eliminarProveedor(String cedula) {
        try {
            String consultaSQL = "DELETE FROM persona WHERE cedula='" + cedula + "'";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
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

    public ArrayList<Proveedor> listaProveedorPersona(String cedula) {
        ArrayList<Proveedor> listaProveeedor = new ArrayList<>();

        try {
            String consultaSQL = "SELECT persona.cedula, persona.nombres, persona.apellidos, persona.telefono, proveedores.contacto "
                           + "FROM personas "
                           + "JOIN proveedores ON personas.idpersona = proveedores.idpersona "
                           + "WHERE personas.cedula = ?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, cedula);
            resultado = ejecutar.executeQuery();
            while (resultado.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setCedula(resultado.getString("cedula"));
                proveedor.setNombre(resultado.getString("nombres"));
                proveedor.setApellido(resultado.getString("apellidos"));
                proveedor.setContacto(resultado.getString("contacto"));
                proveedor.setTelefono(resultado.getString("telefono"));
                proveedor.setContacto(resultado.getString("contacto"));
                listaProveeedor.add(proveedor);

            }
            resultado.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de clientes: " + e);

        }

        return listaProveeedor;
    }

}
