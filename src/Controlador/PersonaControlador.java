/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Persona;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author 59397
 */
public class PersonaControlador {
    private Persona persona;

    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
     public void crearPersona(Persona p) {
        try {
            String consultaSQL = "INSERT INTO persona "
                        + "(nombres, apellidos, cedula, direccion, fechaNacimiento, telefono) "
                        + "VALUES (?, ?, ?, ?, ?, ?);";
            
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, p.getNombre());
            ejecutar.setString(2, p.getApellido());
            ejecutar.setString(3, p.getCedula());
            ejecutar.setString(4, p.getDireccion());
            ejecutar.setString(5, p.getFechaNacimiento());
            ejecutar.setString(6, p.getTelefono());
          
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

    public int buscarIdPersona(String cedula) {
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

    public void ActualisarPersona(String cedula, Persona p){
        try {
            String consultaSQL = "UPDATE persona SET "
                    + "nombre ='" + p.getNombre() + "', apellidos = '" + p.getApellido() + "', "
                    + "direccion ='" + p.getDireccion() + "', fechaNacimiento = '" + p.getFechaNacimiento() + "',"
                    + " telefono = '" + p.getTelefono() + "'"
                    + " WHERE cedula = '" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
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
    
}
