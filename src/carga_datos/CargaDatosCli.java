/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carga_datos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import app_empresa.Cliente;
import app_empresa.Empresa;
import hibernate.HibernateUtils;

/**
 *
 * @author Formacion
 */
public class CargaDatosCli {

	public Empresa CargaDatosCli(Empresa nuevaEmpresa) {
		// Crear clientes
		for (int i = 0; i < 100; i++) {
			Cliente cliente = new Cliente(i, "c" + i, "p", 0, 0, "Cliente" + i, "Apellido1", "Apellido2", "71041502E",
					"cliente" + i + "@gmail.com", "Mujer", "22-06-2020", null, true);
			nuevaEmpresa.addCliente(cliente);

		}
		this.borrarClientesBD();
		this.cargaDatosCliBD();
		return nuevaEmpresa;
	}

	public void cargaDatosCliBD() {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

		Session session = sessionFactory.openSession();

		for (int i = 0; i < 100; i++) {
			Transaction tr = session.beginTransaction();
			Cliente c = new Cliente(i, "c" + i, "p", 0, 0, "Cliente" + i, "Apellido" + i, "Apellido" + i, "71041502E",
					"cliente" + i + "cliente" + i + "@gmail.com", "Mujer", "06-07-2020", null, true);
			session.save(c);
			tr.commit();
		}

	}

	public void borrarClientesBD() {

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tr = session.beginTransaction();

		String stringQuery = "DELETE FROM Cliente";
		Query query = session.createQuery(stringQuery);
		query.executeUpdate();
		
		tr.commit();

	}

}
