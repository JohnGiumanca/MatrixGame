package com.ParadiseIslandSPA;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Succes
 */
@WebServlet("/Shop")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// PrintWriter out = response.getWriter();
		// String email = request.getParameter("email");
		// String password = request.getParameter("password");
		// out.println("<h2>" + email +"<h2>");
		// out.println("<h2>" + password +"<h2>");
		// //Este bine aici; decomenteaza dupa john
		// if(Receptioner.loginClient(email, password))
		// request.getRequestDispatcher("Shop.jsp").forward(request,response);
		// else
		// out.println("<h2>Naspa</h2>");
		// // ca sa ajungem la pagina :

		PrintWriter out = response.getWriter();
		String zona = request.getParameter("Zona");
		String bar = request.getParameter("Bar");
		String restaurant = request.getParameter("Restaurant");
		String servicii = request.getParameter("Servicii");
		
		out.println("<h2>" + "...................."+"<h2>");

		//request.getRequestDispatcher("Shop.jsp").forward(request, response);
		
		String tipProdus;
		if (zona != null) {
			tipProdus = "Zona";
		} else if (bar != null) {
			 tipProdus = "Bar";
		}
		else if (restaurant != null) {
			tipProdus = "Restaurant";
		} else {
			tipProdus = "Servicii";
		}

		List<DetaliiProdus> produse = Receptioner.afisareProduse(tipProdus);
		for (DetaliiProdus pr : produse) {
			System.out.println(pr.getNumeProdus());
			out.println("<h2>" + pr.getNumeProdus() +"<h2>");
		}
		 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);

	}

}
