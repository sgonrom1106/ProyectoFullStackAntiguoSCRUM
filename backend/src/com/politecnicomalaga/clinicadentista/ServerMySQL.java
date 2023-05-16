/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.clinicadentista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//String url = "jdbc:mysql://130.61.62.9:3306/ClinicaDentistadb";
//String username = "nico"; 
//String password = "CURSO2022";

public class ServerMySQL {

    private String sLastError;
    
    public ServerMySQL() {
        sLastError = "";
    }
    
    
    private Connection initDatabase()
    {
            Connection con = null;
            // Initialize all the information regarding
            // Database Connection
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql://bbdd:3306/";
            // Database name to access
            String dbName = "NOMBRE_DATABASE";
            String dbUsername = "nico";
            String dbPassword = "CURSO2022";

            try {
                Class.forName(dbDriver);
                con = DriverManager.getConnection(dbURL + dbName, dbUsername,dbPassword);
            } catch (Exception e) {
                sLastError = sLastError + "<p>Error conectando a la BBDD: " + e.getMessage()+ "</p>";
                e.printStackTrace();
            }
            return con;
    }
    
    public String getTratamientos() {/////////////////////////////////////////////////////////////////////////////////////////
        String resultado = "";
        String id, desc, fecha, precio, cobrado, dniPac;
        Connection con = null;
        Statement st = null;
        PreparedStatement ps = null;
        int iRows = 0;
        try {
            con = this.initDatabase();

            //st = con.createStatement();
            ps = con.prepareStatement("select * from Tratamiento");
            //ResultSet rs = st.executeQuery("select * from Pacientes");
            ResultSet rs = ps.executeQuery();
            while (rs.next())  //Mientras tengamos rows de salida...
            {
              iRows++;
              id = (rs.getString("Codigo"));
              desc = rs.getString("Descripcion");
              fecha = rs.getString("Fecha");         
              precio = rs.getString("Precio");
              cobrado = rs.getString("Cobrado");
              dniPac = rs.getString("Dni_Paciente");
              
              // save the results
              resultado += "<p>" + id + ";" +
							desc+ ";" +
							fecha + ";" +
							precio + ";" +
							cobrado + ";" +
							dniPac + "</p>\n";
            }
        } catch (Exception e) {
            sLastError = sLastError + "<p>Error accediendo a la BBDD Select: " + e.getMessage()+ "</p>";
            e.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (st!= null) st.close();
                if (con!=null) con.close();
            } catch (Exception e) {
                sLastError = sLastError + "<p>Error cerrando la BBDD: " + e.getMessage()+ "</p>";
                e.printStackTrace();
                     
            }
        }
        resultado += "\n<p>Rows recogidas: " + iRows + "</p>\n";
        if (sLastError.isEmpty()) return resultado;        
        else return resultado + sLastError;
    }
    
    
    
    public String getPacientes() {/////////////////////////////////////////////////////////////////////////////////////////
        String resultado = "";
        String id, nombre, apellidos, telefono, email, dni, fnac;
        Connection con = null;
        Statement st = null;
        PreparedStatement ps = null;
        int iRows = 0;
        try {
            con = this.initDatabase();

            //st = con.createStatement();
            ps = con.prepareStatement("select * from Pacientes");
            //ResultSet rs = st.executeQuery("select * from Pacientes");
            ResultSet rs = ps.executeQuery();
             // iteración sobre el resultset
            while (rs.next())  //Mientras tengamos rows de salida...
            {
              iRows++;
              id = (rs.getString("id"));
              nombre = rs.getString("Nombre");
              apellidos = rs.getString("Apellidos");         
              telefono = rs.getString("Telefono");
              fnac = rs.getString("fnac");
              email = rs.getString("Email");
              
              // save the results
              resultado += "<p>" + id + ";" +
                      nombre + ";" +
                      apellidos+ ";" +
                      telefono + ";" +
                      fnac + ";" +
                      email + "</p>\n";
            }
        } catch (Exception e) {
            sLastError = sLastError + "<p>Error accediendo a la BBDD Select: " + e.getMessage()+ "</p>";
            e.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (st!= null) st.close();
                if (con!=null) con.close();
            } catch (Exception e) {
                sLastError = sLastError + "<p>Error cerrando la BBDD: " + e.getMessage()+ "</p>";
                e.printStackTrace();
                     
            }
        }
        resultado += "\n<p>Rows recogidas: " + iRows + "</p>\n";
        if (sLastError.isEmpty()) return resultado;        
        else return resultado + sLastError;
    }
    

    public String insertTratamiento(String sCSV) {/////////////////////////////////////////////////////////////////////////////////////////
        String resultado = "<p>Error al insertar</p>";
        String id, desc, fecha, precio, cobrado, dniPac;
        Connection con = null;
       
        Paciente miPr = new Paciente(sCSV);
        
        PreparedStatement ps = null;
      
        try {
            con = this.initDatabase();
            //st = con.createStatement();
            ps = con.prepareStatement("insert into Tratamiento (DNI,Nombre,Apellidos,Telefono,Fnac,Email) values (?,?,?,?,?,?,?)");
            ps.setString(1, miPr.sDni);
            ps.setString(2,miPr.sNombre);
            ps.setString(3,miPr.sApellidos);
            ps.setString(4,miPr.sTelefono);
            ps.setString(5,miPr.sFNac);
            ps.setString(7,miPr.sEmail);

            
            
            if (ps.executeUpdate()!=0)
        		resultado = "<p>Paciente insertado correctamente</p>";
            else 
            	resultado = "<p>Algo ha salido mal con la sentencia Insert Pacientes</p>";            
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí
            
        } catch (Exception e) {
            sLastError = sLastError + "<p>Error accediendo a la BBDD Select: " + e.getMessage()+ "</p>";
            e.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (ps!= null) ps.close();
                if (con!=null) con.close();
            } catch (Exception e) {
                sLastError = sLastError + "<p>Error cerrando la BBDD: " + e.getMessage()+ "</p>";
                e.printStackTrace();
                     
            }
        }
        if (sLastError.isEmpty()) return resultado;        
        else return resultado + sLastError;
        
    }
    
    
    
    public String insertPaciente(String sCSV) {/////////////////////////////////////////////////////////////////////////////////////////
        String resultado = "<p>Error al insertar</p>";
        String id, nombre, fecha, direccion, telefono, email, contacto;
        Connection con = null;
       
        Paciente miPr = new Paciente(sCSV);
        
        PreparedStatement ps = null;
      
        try {
            con = this.initDatabase();
            //st = con.createStatement();
            ps = con.prepareStatement("insert into Paciente (DNI,Nombre,Apellidos,Telefono,Fnac,Email) values (?,?,?,?,?,?,?)");
            ps.setString(1, miPr.sDni);
            ps.setString(2,miPr.sNombre);
            ps.setString(3,miPr.sApellidos);
            ps.setString(4,miPr.sTelefono);
            ps.setString(5,miPr.sFNac);
            ps.setString(7,miPr.sEmail);

            
            
            if (ps.executeUpdate()!=0)
        		resultado = "<p>Paciente insertado correctamente</p>";
            else 
            	resultado = "<p>Algo ha salido mal con la sentencia Insert Pacientes</p>";            
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí
            
        } catch (Exception e) {
            sLastError = sLastError + "<p>Error accediendo a la BBDD Select: " + e.getMessage()+ "</p>";
            e.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (ps!= null) ps.close();
                if (con!=null) con.close();
            } catch (Exception e) {
                sLastError = sLastError + "<p>Error cerrando la BBDD: " + e.getMessage()+ "</p>";
                e.printStackTrace();
                     
            }
        }
        if (sLastError.isEmpty()) return resultado;        
        else return resultado + sLastError;
        
    }
    
}



// MYSQL SERVER:

/*
 DROP DATABASE IF EXISTS ClinicaDentistadb;
 Create database ClinicaDentistadb;
 Use ClinicaDentistadb; 

 Create table Paciente (
    DNI VARCHAR(9) NOT NULL PRIMARY KEY,
    Nombre VARCHAR(15),
    Apellidos VARCHAR(15),
    Telefono VARCHAR(9),
    Fnac DATE,
    Email VARCHAR(40) 
 );

 Create table Tratamiento (
    Codigo int AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(100),
    Fecha DATE,
    Precio FLOAT,
    Cobrado BOOLEAN DEFAULT FALSE,
	Dni_Paciente VARCHAR(9) NOT NULL,
	FOREIGN KEY (Dni_Paciente) REFERENCES Paciente (DNI)
	
 );
*/






/////////////////////////////////////////


/*package com.politecnicomalaga.clinicadentista;

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
*/
