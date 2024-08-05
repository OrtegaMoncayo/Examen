/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 59397
 */
public class Proveedor extends Persona {

    private String contacto;
    private int idPersona;
    private int idProveedor;

    public Proveedor() {
    }

    public Proveedor(String contacto, int idPersona, int idProveedor) {
        this.contacto = contacto;
        this.idPersona = idPersona;
        this.idProveedor = idProveedor;
    }

    public Proveedor(String contacto, int idPersona, int idProveedor, String nombre, String apellido, String cedula, String direccion, String telefono, String fechaNacimiento) {
        super(idPersona, nombre, apellido, cedula, direccion, telefono, fechaNacimiento);
        this.contacto = contacto;
        this.idPersona = idPersona;
        this.idProveedor = idProveedor;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String imprimir() {
        return "DATOS PERSONALES\n"
                + "NOMBRE;" + getNombre() + "\n"
                + "APELLIDO:" + getApellido() + "\n"
                + "CEDULA:" + getCedula() + "\n"
                + "DIRECCION:" + getDireccion() + "\n"
                + "FECHA DE NACIMIENTO:" + getFechaNacimiento() + "\n"
                + "TELEFONO:" + getTelefono() + "\n"
                + "CONTACTO:" + getContacto() + "\n";
    }

}
