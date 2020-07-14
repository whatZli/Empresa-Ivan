/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import app_empresa.Cliente;
import app_empresa.Empresa;
import app_empresa.Login;
import app_empresa.Persona;
import app_empresa.Trabajador;
import carga_datos.CargaEmpresa;
import hibernate.HibernateUtils;
import menu.MenuLogin;

/**
 *
 * @author Formacion
 */
public class Main {

	public static void main(String[] args) {

		// DeclaraciÃ³n de variables
		MenuLogin ml = new MenuLogin();
		Login l = new Login();
		Trabajador userTraLogueado = null;
		Cliente userCliLogueado = null;
		Boolean salir=false;
		// Logger
		Logger logger = LogManager.getLogger(Main.class);

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();

		// Crear empresa y carga de datos
		Empresa nuevaEmpresa = CargaEmpresa.CargaEmpresa();
		logger.trace("Cargando datos empresa");

		// Lanzar formulario login
		do {


			if (!salir) {
				String usuarioContrasena = ml.formularioLogIn();

				String[] aUsuarioContrasena = usuarioContrasena.split("-");
				String usuarioIntroducido = aUsuarioContrasena[0];
				String passwordIntroducido = aUsuarioContrasena[1];

				userTraLogueado = l.validarUsuarioContrasenaTrabajador(usuarioIntroducido, passwordIntroducido,
						nuevaEmpresa);

				if (userTraLogueado == null) {// Si no hay un trabajador se va a buscar un cliente
					userCliLogueado = l.validarUsuarioContrasenaClienteBD(usuarioIntroducido, passwordIntroducido);

					if (userCliLogueado != null) {// Si se obtiene un cliente
						logger.info("Se ha encontrado el cliente");
						SwitchClientes.switchClientes(userCliLogueado);

					} else {// Si no se ha encontrado un Trabajador ni un Cliente
						// System.out.println("No se ha encontrado el usuario");
						logger.info("No se ha encontrado el usuario");
					}
				}

				if (userTraLogueado != null) {

					logger.info("Se ha encontrado el trabajador");
					SwitchTrabajadores.switchTrabajadores(userTraLogueado, nuevaEmpresa);

				}
			}
			if(salir) {
				System.out.println("Saliendo de la aplicación");
				break;
			}
			salir = ml.formularioSalir();

		} while (userTraLogueado == null || userCliLogueado == null);

	}

}
