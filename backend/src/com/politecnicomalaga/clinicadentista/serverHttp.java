package com.politecnicomalaga.clinicadentista;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class serverHttp extends HttpServlet {

	    public void start() {
	        System.out.println("Inicializando servidor");
	    }

	    public void doGet(HttpServletRequest request, HttpServletResponse response) {
	        System.out.println("Recibiendo petición del cliente...");
	        
	    }

	    public void stop() {
	        System.out.println("Deteniendo el servidor...");
	    }
	}
