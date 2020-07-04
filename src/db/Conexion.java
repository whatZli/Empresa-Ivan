package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

	static Connection conexion; // atributo para guardar el objeto Connection

	public static Connection getConexion() {
		if (conexion == null) {
			crearConexion();
		}
		return conexion;
	}

	// devuelve true si se ha creado correctamente
	public static boolean crearConexion() {
		String bd = null, login=null, password=null, host=null, url=null;

		try {

			Properties prop = new Properties();
			try (InputStream input = new FileInputStream("resources/db.properties")) {//Cargar el fichero

				// load a properties file
				prop.load(input);

				//Volcar propiedades a las variables
				bd = (prop.getProperty("bd"));
				login = (prop.getProperty("login"));
				password = (prop.getProperty("password"));
				host = (prop.getProperty("host"));
				url = (prop.getProperty("url"));

			} catch (IOException ex) {
				ex.printStackTrace();
			}

			// cargo el driver
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(url + host + "/" + bd, login, password);

			conexion.setAutoCommit(true);

		} catch (SQLException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static void desconectar() {
		try {
			conexion.close();
			conexion = null;
			// System.out.println("La conexion a la base de datos " + bd + " ha terminado");

		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexion");
		}
	}

}
