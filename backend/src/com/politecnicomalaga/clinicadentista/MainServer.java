package com.politecnicomalaga.clinicadentista;

public class MainServer {

	private static serverMySQL mysql;
	
	public static void main(String[] args) {
		mysql = new serverMySQL();
		try {
			mysql.connect();
			mysql.ejecutarCodigo("CREATE DATABASE TEST_BORRAR;", mysql.conexxion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
