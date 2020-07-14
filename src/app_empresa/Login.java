/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_empresa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import hibernate.HibernateUtils;
import menu.MenuLogin;

/**
 *
 * @author Formacion
 */
public class Login {

	// Recorre todos los departamentos y dentro
	// de cada departamento recorre todos los
	// trabajadores hasta encontrar uno
	public Trabajador validarUsuarioContrasenaTrabajador(String usuario, String password, Empresa empresa) {

		boolean encontrado = false;
		String claveDept;
		Iterator<String> departamentos = empresa.getDepartamentos().keySet().iterator();
		while (departamentos.hasNext() && !encontrado) {
			claveDept = departamentos.next();
			Departamento d = empresa.getDepartamentos().get(claveDept);

			HashMap<String, Trabajador> listaTrabajadores;

			listaTrabajadores = d.getListaTrabajadores();

			Trabajador t = listaTrabajadores.get(usuario);

			if (t != null) {
				// System.out.println("Se ha encontrado el trabajador");
				encontrado = true;

				if (t.getPassword().equals(password)) {
					// System.out.println("La contraseña es correcta");
					return t;
				} else {
					System.out.println("La contraseña es incorrecta");
				}
			}

		}

		return null;
	}

	public Cliente validarUsuarioContrasenaCliente(String usuario, String password, Empresa empresa) {

		// System.out.println("Se va a validar "+ usuario +" "+ password);
		HashMap<String, Cliente> listaClientes;

		listaClientes = empresa.getClientes();

		Cliente c = listaClientes.get(usuario);

		if (c != null) {
			if (c.getPassword().equals(password)) {
				return c;
			}
		}

		return null;
	}

	public Cliente validarUsuarioContrasenaClienteBD(String usuario, String password) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

		Session session = sessionFactory.openSession();
		
		String hql = "FROM Cliente C WHERE C.cod_cliente = :cod_cliente AND C.password = :password";
		Query query = session.createQuery(hql);
		query.setParameter("cod_cliente",usuario);
		query.setParameter("password",password);
		List<Cliente> results = query.list();
		
		if(!results.isEmpty()) {
			for(Cliente c : results) {
				return c;
			}
		}
		return null;

		

	}

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

}
