/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 59397
 */
public class ConexionBDD {
      java.sql.Connection conexion;
     public java.sql.Connection conectar(){ 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection("jdbc:mysql://localhost/probedor_producto?autoReconnect=true&useSSL=false","root","R1ch4rdm0nc4y01");
            System.out.println("CONECTADO"); 
        } catch (ClassNotFoundException | SQLException e) 
        {
             System.out.println("ERROR DE CONEXION A LA BASE DE DATOS");
        }
        return conexion;
    }
    
}
