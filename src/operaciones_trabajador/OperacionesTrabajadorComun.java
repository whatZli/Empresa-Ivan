/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones_trabajador;

import app_empresa.Departamento;
import app_empresa.Empresa;
import app_empresa.Mensaje;
import app_empresa.Trabajador;
import interfaces_operaciones.IDepComun;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import menu.Menu;
import menu.MenuTrabajadorComun;

/**
 *
 * @author Formacion
 */
public class OperacionesTrabajadorComun implements IDepComun {

	MenuTrabajadorComun mtc = new MenuTrabajadorComun();

	// Método que devuelve un trabajador si lo encuentra en algún departamento de
	// la empresa
	// sino lo encuentra devuelve null
	public Trabajador comprobarUsuarioExiste(Empresa empresa, String codigoUsuario) {
		String claveDept;
		Iterator<String> departamentos = empresa.getDepartamentos().keySet().iterator();
		while (departamentos.hasNext()) {
			claveDept = departamentos.next();
			Departamento d = empresa.getDepartamentos().get(claveDept);

			String clave;
			Iterator<String> trabajadores = d.getListaTrabajadores().keySet().iterator();
			while (trabajadores.hasNext()) {
				clave = trabajadores.next();
				if (clave.equals(codigoUsuario)) {
					Trabajador t = d.getListaTrabajadores().get(clave);
					return t;
				}
			}
		}

		return null;
	}

	@Override
	public void enviarCorreo(Empresa nuevaEmpresa, String tDestino, Trabajador tOrigen) {
		Trabajador trabajadorEnviarEmail = this.comprobarUsuarioExiste(nuevaEmpresa, tDestino);
		if (trabajadorEnviarEmail != null) {
			Mensaje m = mtc.escribirMensaje(tOrigen);
			trabajadorEnviarEmail.addMensaje(m);
			System.out.println("--- Mensaje enviado");
		} else {
			System.out.println("--- El trabajador no existe");
		}
	}

	@Override
	public int checkCorreoNoContestado(Trabajador tOrigen, Empresa nuevaEmpresa) {
		Scanner scan = new Scanner(System.in);

		int menuComprobarNC = -1;
		do {
			int cont = 1;

			LinkedList<Mensaje> listaMensajes = tOrigen.getMensajes();

			System.out.println("---------------------------------");
			listaMensajes = tOrigen.getMensajes();
			System.out.println("--- Listado de correos No Contestados ");
			if (listaMensajes.isEmpty()) {
				System.out.println("--- Bandeja vacía");
				System.out.println("--- Introduce 0 para salir");
				menuComprobarNC = scan.nextInt();
			} else {
				for (Mensaje m : listaMensajes) {
					if (m.isContestado() == false) {
						System.out.println("* " + cont++ + ". Origen: " + m.gettOrigen().getUsuario() + " - Fecha - "
								+ m.getFecha() + " - Hora - " + m.getHora() + " - " + m.getAsunto());
					}
				}
				System.out.println("--- Introduce el número de mensaje que quieres contestar (0 para salir)");
				menuComprobarNC = scan.nextInt();
				if (menuComprobarNC > 0 && menuComprobarNC < cont) {
					Mensaje m = listaMensajes.get(menuComprobarNC - 1);
					System.out.println("--------------------------------------");
					System.out.println("| Origen: " + m.gettOrigen().getUsuario() + "      Fecha:" + m.getFecha() + " "
							+ m.getHora());
					System.out.println("| Asunto: " + m.getAsunto());
					System.out.println("| Mensaje: " + m.getCuerpo());
					System.out.println("---|");
					System.out.println("   |");
					m.setContestado(true);

					String tDestino = mtc.responderCorreoACompanero(m.gettOrigen().getUsuario());
					this.enviarCorreo(nuevaEmpresa, tDestino, tOrigen);

					System.out.println("");
				}
			}

			System.out.println("---------------------------------");
		} while (menuComprobarNC != 0);

		return menuComprobarNC;
	}

	@Override
	public int checkCorreoNoLeido(Trabajador tOrigen) {
		Scanner scan = new Scanner(System.in);

		int cont = 1;
		int menuComprobarNL = -1;

		System.out.println("---------------------------------");

		LinkedList<Mensaje> listaMensajes = tOrigen.getMensajes();
		listaMensajes = tOrigen.getMensajes();
		System.out.println("--- Listado de correos No Leídos ");
		if (listaMensajes.isEmpty()) {// Error aquí. la lista puede contener mensajes aunque estén leidos y no entra
										// en el if
			System.out.println("--- Bandeja vacía");
			System.out.println("--- Introduce el número de mensaje que quieres leer (0 para salir)");
			menuComprobarNL = scan.nextInt();
		} else {
			for (Mensaje m : listaMensajes) {
				if (m.isLeido() == false) {
					System.out.println("* " + cont++ + ". Origen: " + m.gettOrigen().getUsuario() + " - Fecha - "
							+ m.getFecha() + " - Hora - " + m.getHora() + " - " + m.getAsunto());
				}
			}
		}
		System.out.println("--- Introduce el número de mensaje que quieres leer (0 para salir)");
		menuComprobarNL = scan.nextInt();
		if (menuComprobarNL > 0 && menuComprobarNL < cont) {
			Mensaje m = listaMensajes.get(menuComprobarNL - 1);
			System.out.println("--------------------------------------");
			System.out.println(
					"| Origen: " + m.gettOrigen().getUsuario() + "      Fecha:" + m.getFecha() + " " + m.getHora());
			System.out.println("| Asunto: " + m.getAsunto());
			System.out.println("| Mensaje: " + m.getCuerpo());
			m.setLeido(true);
		}

		Menu.detenerFlujo();

		return menuComprobarNL;
	}

	@Override
	public void checkCorreoDiaConcreto(Trabajador t) {
		Scanner scan = new Scanner(System.in);
		String fecha;
		int cont = 1;

		System.out.println("---------------------------------");

		System.out.println("Introduzca una fecha (2020-07-08)");
		fecha = scan.nextLine();

		LinkedList<Mensaje> listaMensajes = t.getMensajes();
		listaMensajes = t.getMensajes();

		if (listaMensajes.isEmpty()) {
			System.out.println("--- Bandeja vacía");
		} else {
			for (Mensaje m : listaMensajes) {
				if (m.getFecha().equals(fecha)) {
					System.out.println("* " + cont++ + ". Origen: " + m.gettOrigen().getUsuario() + " - Fecha - "
							+ m.getFecha() + " - Hora - " + m.getHora() + " - " + m.getAsunto());
				}
			}
		}

		Menu.detenerFlujo();

	}

	@Override
	public void checkCorreoEntreFechas(Trabajador t) {
		Scanner scan = new Scanner(System.in);
		String fecha1, fecha2;
		int cont = 1;

		System.out.println("---------------------------------");

		System.out.print("Introduzca la primera fecha (2020-07-08): ");
		fecha1 = scan.nextLine();
		System.out.print("Introduzca la segunda fecha (2020-07-08): ");
		fecha2 = scan.nextLine();

		LinkedList<Mensaje> listaMensajes = t.getMensajes();
		listaMensajes = t.getMensajes();

		if (listaMensajes.isEmpty()) {
			System.out.println("--- Bandeja vacía");
		} else {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date convertedDate1;
			try {
				convertedDate1 = sdf.parse(fecha1);

				Date convertedDate2 = sdf.parse(fecha2);

				for (Mensaje m : listaMensajes) {

					Date convertedDateFechaMensaje = sdf.parse(m.getFecha());

					if (convertedDate1.compareTo(convertedDateFechaMensaje) < 0
							&& convertedDate2.compareTo(convertedDateFechaMensaje) > 0) {// Si la fecha1 que inserto es
																							// menor que la que tiene el
																							// mensaje y si la fecha2 es
																							// mayor que la que tiene el
																							// mensaje se muestra
						System.out.println("* " + cont++ + ". Origen: " + m.gettOrigen().getUsuario() + " - Fecha - "
								+ m.getFecha() + " - Hora - " + m.getHora() + " - " + m.getAsunto());
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		Menu.detenerFlujo();

	}

	@Override
	public void enviarCorreoJefeDept() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void enviarCorreoJefeEmp() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

}
