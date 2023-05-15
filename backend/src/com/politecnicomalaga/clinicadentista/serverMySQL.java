package com.politecnicomalaga.clinicadentista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.Statement;

public class serverMySQL {

	String url = "jdbc:mysql://130.61.62.9:3306/ClinicaDentistadb";
	String username = "nico"; 
	String password = "CURSO2022";

	Connection conexxion;
	public void connect() throws SQLException {
		conexxion = DriverManager.getConnection(url, username, password);
	}
	
	public void test() throws SQLException {
		
	}
	
	public String[] get

	public String[] getIncidencia() throws SQLException {
		String[] datosIncidencia = new String[8];
		String sql = "SELECT * FROM incidencia";
		try (Statement statement = conexxion.createStatement();
			java.sql.ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {
				datosIncidencia[0] = resultSet.getString("codigo");
				datosIncidencia[1] = resultSet.getString("dniPropio");
				datosIncidencia[2] = resultSet.getString("fecha");
				datosIncidencia[3] = resultSet.getString("hora");
				datosIncidencia[4] = resultSet.getString("matriculaPropia");
				datosIncidencia[5] = resultSet.getString("matriculaAjena");
				datosIncidencia[6] = resultSet.getString("descripcion");
				datosIncidencia[7] = resultSet.getString("dniAjeno");
			}
		} catch (SQLException e) {
			throw new SQLException("Error executing code: " + sql, e);
		}
		return datosIncidencia;
	}
	/*
	public void uploadOficinaData(Oficina oficina) throws SQLException {
		String sql;
		if(oficina.getsCodigo() < 0)
			 sql = String.format("INSERT INTO oficina (nombre, direccion, ciudad, CP, telefono, email) VALUES ('%s', '%s', '%s', '%s', '%s', '%s');", oficina.getsNombre(), oficina.getsDireccion(), oficina.getsCiudad(), oficina.getsCP(), oficina.getsTelefono(), oficina.getsEmail());
		else 
			 sql = String.format("INSERT INTO oficina VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');", oficina.getsCodigo(), oficina.getsNombre(), oficina.getsDireccion(), oficina.getsCiudad(), oficina.getsCP(), oficina.getsTelefono(), oficina.getsEmail());
		ejecutarCodigo(sql, conexxion);
	}
	
	
	public void uploadOficinaFull(Oficina oficina) throws SQLException { //TODOS los datos dentro de oficina.
		String sql = "";
		uploadOficinaData(oficina);
		int codOficina = oficina.getsCodigo();
		Cliente[] clientes = oficina.getMisClientes();
		for(int i = 0; i < clientes.length; i++) { //(dni, nombre, apellidos, codPoliza, email, direccion, telefono, codigoOficina)
			Cliente c = clientes[i];
			if(codOficina < 0)
				throw new SQLException("ERROR CODIGO AUTOMATICO DE OFICINA NO IMPLEMENTADO TODAVIA");
			sql += String.format("INSERT INTO cliente VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');", c.getDni(), c.getNombre(), c.getApellidos(), c.getCodPoliza(), c.getEmail(), c.getDireccion(), c.getTfno(), codOficina);
			
			for(int j = 0; j < c.getIncidencias().length; j++) {//(codigo, dniPropio, fecha, hora, matriculaPropia, matriculaAjena, descripcion, !! dniAjeno, diasUrgencia)
				Incidencia in = c.getIncidencias()[j];
				if (in instanceof IncidenciaAjena) {
			    	sql += String.format("INSERT INTO incidencia (codigo, dniPropio, fecha, hora, matriculaPropia, matriculaAjena, descripcion, dniAjeno) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');", in.getsCodigo(), c.getDni(), in.getsFecha(), in.getsHora(), in.getsMatriculaPropia(), in.getsMatriculaAjena(), in.getsDescripcion(), ((IncidenciaAjena)in).getDniAjeno());
			    } else if (in instanceof IncidenciaUrgente) {
			    	sql += String.format("INSERT INTO incidencia VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', NULL, '%s');", in.getsCodigo(), c.getDni(), in.getsFecha(), in.getsHora(), in.getsMatriculaPropia(), in.getsMatriculaAjena(), in.getsDescripcion(), ((IncidenciaUrgente)in).getDiasMaximo());
			    } else {
					sql += String.format("INSERT INTO incidencia (codigo, dniPropio, fecha, hora, matriculaPropia, matriculaAjena, descripcion) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');", in.getsCodigo(), c.getDni(), in.getsFecha(), in.getsHora(), in.getsMatriculaPropia(), in.getsMatriculaAjena(), in.getsDescripcion());
			    }
			}
		}
		
		ejecutarCodigo(sql, conexxion);
	}
		*/
	public void ejecutarCodigo(String codigo, Connection conexion) throws SQLException {
		String sqlError = "Null";
	    try (Statement statement = conexion.createStatement()) {
	    	for(String sql: codigo.split(";")) {
	    		sqlError = sql;
		        statement.executeUpdate(sql + ";");
	    	}
	    } catch (SQLException e) {
	        throw new SQLException("Error executing code: " + sqlError, e);
	    }
	}	
}


// MYSQL SERVER:

