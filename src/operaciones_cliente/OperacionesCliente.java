/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones_cliente;

import db.Conexion;
import app_empresa.Cliente;
import app_empresa.Trabajador;
import hilos.ComprobarValidezPedido;
import interfaces_operaciones.ICliente;
import main.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import menu.Menu;

/**
 *
 * @author Formacion
 */
public class OperacionesCliente implements ICliente {

    Scanner scan = new Scanner(System.in);
    //Logger
    Logger logger = LogManager.getLogger(OperacionesCliente.class);

    @Override
    public void listarArticulos() {
        System.out.println("--- Listado de Art�culos ---");
        Connection con = Conexion.getConexion();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from producto");

            while (rs.next()) {
                System.out.print("Código - " + rs.getInt(1));
                System.out.print(" | Nombre - " + rs.getString(2));
                System.out.println();
            }

            rs.close();

            if (con != null) {
                //Conexion.desconectar();
            }
        } catch (Exception e) {
            logger.warn("No hay conexi�n a la base de datos");
        }

        System.out.print("\n--- PULSE INTRO PARA CONTINUAR ---\n");
        try {
            System.in.read();
        } catch (IOException ex) {
        }

    }

    @Override
    public void verArticulo(int cod_articulo) {
        System.out.println("Introduce el código del artículo para buscar");
        int codArticulo = scan.nextInt();

        Connection con = Conexion.getConexion();
        try {
            if (con != null) {
                String consulta = "select * from producto where cod_producto = ? ";
                PreparedStatement ps = con.prepareStatement(consulta);
                ps.setInt(1, codArticulo);

                // process the results
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("--- Ver el artículo " + codArticulo + " ---");
                    System.out.println("ID - " + rs.getInt(1));
                    System.out.println("Nombre - " + rs.getString(2));
                    System.out.println("Descripción - " + rs.getString(3));
                } else {
                    System.out.println("No se encuentra el código del producto buscado");
                }

                Menu.detenerFlujo();

                rs.close();
                ps.close();

                //Conexion.desconectar();
            } else {
                System.out.println("Conexion no realizada");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void comprarArticlo(Cliente c) {

        System.out.print("Introduce el código del artículo para comprar: ");
        int codArticuloComprar = scan.nextInt();

        System.out.print("Introduce la cantidad: ");
        int cantidadComprar = scan.nextInt();

        Connection con = Conexion.getConexion();

        if (con != null) {

            try {
                final ComprobarValidezPedido cvbd = new ComprobarValidezPedido();
                cvbd.setCon(con);
                cvbd.setC(c);
                cvbd.setCantidadComprar(cantidadComprar);
                cvbd.setCodArticuloComprar(codArticuloComprar);

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            cvbd.hacerPedido();
                        } catch (InterruptedException ex) {
                            System.out.println(ex);
                        }
                    }
                }.start();

                Thread.sleep(1000);

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            cvbd.confirmacion();
                        } catch (InterruptedException ex) {
                            System.out.println(ex);
                        }
                    }
                }.start();

            } catch (InterruptedException ex) {
                System.out.println(ex);
            }

        } else {
            System.out.println("Conexion no realizada");
        }

    }

    @Override
    public void verTusCompras(Cliente c) {

        System.out.println("Ver los pedidos de " + c.getNombre());

        Connection con = Conexion.getConexion();
        try {
            if (con != null) {
                String consulta = "select * from pedido where cod_cliente=?;";
                PreparedStatement ps = con.prepareStatement(consulta);

                ps.setString(1, c.getCod_cliente());

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.print("| Cod. pedido - " + rs.getInt(1));
                    System.out.print("| Cod. producto - " + rs.getString(3));
                    System.out.print("| Cantidad - " + rs.getString(4));

                    System.out.println();
                }

                rs.close();

                ps.close();

                //Conexion.desconectar();
                Menu.detenerFlujo();

            } else {
                System.out.println("Conexion no realizada");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void lanzarHilo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void detenerHilo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
