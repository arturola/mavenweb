package mavenweb.servlets;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

/**
 * Servlet implementation class ServletPruebasRemoto
 */
@WebServlet("/ServletPruebasRemoto")
public class ServletPruebasRemoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       // Establecer el Datasource
	
	@Resource(name="jdbc/productos")
	private DataSource miPool;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPruebasRemoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter salida = response.getWriter();
		response.setContentType("text/plain");
		
		Connection miConexion=null;
		Statement miStatement=null;
		ResultSet miResultSet=null;
		
		try {
			
			miConexion = miPool.getConnection();
			String miSql = "SELECT * FROM products"; 
			miStatement = miConexion.createStatement();
			miResultSet = miStatement.executeQuery(miSql);
			
			while(miResultSet.next()) {
				String nombre_articulo=miResultSet.getString(2);
				salida.println(nombre_articulo);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} 
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
