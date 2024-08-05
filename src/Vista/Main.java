/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.PersonaControlador;
import Controlador.ProductoControlador;
import Controlador.ProveedorControlador;
import Modelo.Persona;
import Modelo.Producto;
import Modelo.Proveedor;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 59397
 */
public class Main {

    public static void main(String[] args) {
        Scanner es = new Scanner(System.in);
        int i = 1;
        do {
            System.out.println("Elija la opción que Usted requiera ejecutar:\n"
                    + "1.Crear Provedor\n"
                    + "2.Actualizar Proveedor\n"
                    + "3.Buscar Proveedor\n"
                    + "4.Eliminar Provedor\n"
                    + "5.Crear Producto\n"
                    + "6.Actualizar de Producto\n"
                    + "7.Buscar Producto\n"
                    + "8.Eliminar Producto"
                    + "9.Lista de Producto\n"
                    + "0.Salir\n");
            int op = es.nextInt();
            if (op == 1) {
                System.out.println("Ingrese los siguientes datos informativos");
                Persona p = new Persona();
                System.out.print("Ingrese su Nombre: ");
                p.setNombre(es.nextLine());
                System.out.print("Ingrese su Apellidos: ");
                p.setApellido(es.nextLine());
                System.out.print("Ingrese su número de cédula: ");
                p.setCedula(es.nextLine());
                System.out.print("Ingrese una Dirección: ");
                p.setDireccion(es.nextLine());
                System.out.print("Ingrese su Fecha de Nacimiento(AAAA/MM/DD): ");
                p.setFechaNacimiento(es.nextLine());
                System.out.print("Ingrese un número Telefónico: ");
                p.setTelefono(es.nextLine());

                PersonaControlador pC = new PersonaControlador();
                pC.crearPersona(p);

                System.out.println("Ingrese los siguientes datos Proveedor");
                int idPersona = pC.buscarIdPersona(p.getCedula());

                Proveedor pr = new Proveedor();
                System.out.print("Ingrese el Contacto del Proveedor: ");
                pr.setContacto(es.nextLine());
                pr.setIdPersona(idPersona);

                ProveedorControlador prC = new ProveedorControlador();
                prC.crearProveedor(pr);

            } else if (op == 2) {
                System.out.println("INGRESE LA CEDULA");
                String cedula = es.next();

                ProveedorControlador prC = new ProveedorControlador();

                Proveedor pr1 = prC.buscarDatosProveedor(cedula);
                System.out.println(pr1.imprimir());

                System.out.println("Actualizar los datos del cliente");
                Persona p = new Persona();

                System.out.println("Ingrese el nuevo Nombre:");
                p.setNombre(es.next());
                System.out.println("Ingrese el nuevo Apellido:");
                p.setApellido(es.next());
                System.out.println("Ingrese el nuevo Cedula:");
                p.setCedula(es.next());
                System.out.println("Ingrese el nuevo Direccion:");
                p.setDireccion(es.next());
                System.out.println("Ingrese el nuevo Fecha de Nacimiento (AAAA/MM/DD):");
                p.setFechaNacimiento(es.next());
                System.out.println("Ingrese el nuevo Telefono:");
                p.setTelefono(es.next());

                Proveedor pr = new Proveedor();
                System.out.println("Ingrese el nuevo Contacto");
                pr.setContacto(es.next());

                prC.ActualisarProveedor(cedula, pr, p);

            } else if (op == 3) {
                System.out.println("INGRESE LA CEDULA");
                String cedula = es.next();
                ProveedorControlador prC = new ProveedorControlador();
                ArrayList<Proveedor> listaProveedor = prC.listaProveedorPersona(cedula);

                for (Proveedor l : listaProveedor) {
                    System.out.println(l.imprimir());
                }

            } else if (op == 4) {
                System.out.println("INGRESE LA CEDULA");
                String cedula = es.next();
                PersonaControlador pC = new PersonaControlador();
                ProveedorControlador prC = new ProveedorControlador();
                prC.eliminarProveedor(cedula);

            } else if (op == 5) {
                Producto pr = new Producto();
                System.out.print("Ingrese su Nombre de Producto: ");
                pr.setNombreProducto(es.nextLine());
                System.out.print("Ingrese su Stock: ");
                pr.setStock(es.nextInt());
                es.nextLine();
                System.out.print("Ingrese su Precio: ");
                pr.setPrecio(es.nextDouble());
                es.nextLine();
                System.out.print("Ingrese su Marca: ");
                pr.setMarca(es.nextLine());
                System.out.print("Ingrese su Codigo: ");
                pr.setCodigo(es.nextInt());
                es.nextLine();

                ProductoControlador prC = new ProductoControlador();
                prC.crearProducto(pr);

            } else if (op == 6) {
                System.out.println("Ingrese el código del producto:");
                int codigo = es.nextInt();
                es.nextLine();

                ProductoControlador prC = new ProductoControlador();
                Producto pr1 = prC.buscarDatosProducto(codigo);
                System.out.println(pr1.imprimir());

                System.out.println("Actualizar los datos del producto");
                Producto pr = new Producto();

                System.out.print("Ingrese el nuevo nombre del producto: ");
                pr.setNombreProducto(es.nextLine());
                System.out.print("Ingrese la nueva marca del producto: ");
                pr.setMarca(es.nextLine());
                System.out.print("Ingrese el nuevo precio del producto: ");
                pr.setPrecio(es.nextDouble());
                es.nextLine();
                System.out.print("Ingrese el nuevo stock del producto: ");
                pr.setStock(es.nextInt());
                es.nextLine();
                prC.ActualisarProducto(codigo, pr);

            } else if (op == 7) {
                System.out.println("Ingrese el código del producto:");
                int codigo = es.nextInt();

                ProductoControlador pC = new ProductoControlador();
                Producto p = pC.buscarProducto(codigo);
                p.imprimir();

            } else if (op == 8) {
                System.out.println("Ingrese el código del producto:");
                int codigo = es.nextInt();
                es.nextLine();
                ProductoControlador pC = new ProductoControlador();
                pC.eliminarProducto(codigo);

            } else if (op == 9) {
                ProductoControlador pC = new ProductoControlador();
                ArrayList<Producto> listaProductos = pC.listaProducto();
                for (Producto l : listaProductos) {
                    System.out.println(l.imprimir());
                }
            } else if (op == 0) {
                i = 0;
            }

        } while (i
                == 1);

    }

}
