/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones_trabajador;

import app_empresa.Empresa;
import app_empresa.Trabajador;
import interfaces_operaciones.IDepCompras;
import interfaces_operaciones.IDepComun;
import interfaces_operaciones.IDepVentas;

/**
 *
 * @author Formacion
 */
public class OperacionesTrabajadorCyV implements IDepComun, IDepCompras, IDepVentas {
	
	@Override
	public void checkCorreoDiaConcreto(Trabajador t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkCorreoEntreFechas(Trabajador t) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void enviarCorreoJefeDept() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enviarCorreoJefeEmp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void altaProducto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void bajaProducto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void aplicarDescuento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarPagos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enviarCorreo(Empresa nuevaEmpresa, String tDestino, Trabajador tOrigen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int checkCorreoNoContestado(Trabajador t, Empresa nuevaEmpresa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int checkCorreoNoLeido(Trabajador t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	

}
