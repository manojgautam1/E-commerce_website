package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import service.CustomerDAO;

/**
 * Servlet implementation class EditCustomer
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/edit" })
public class EditCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private CustomerDAO customerdao;
       
       @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	customerdao= new CustomerDAO();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*int customerID=Integer.valueOf(request.getParameter("customerID"));

        
		try {
			 Customer customer = customerdao.getCustomerByID(customerID);
			 request.setAttribute("customerlist", customer);
			 HttpSession session=request.getSession();
			 session.setAttribute("customerID", customerID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

		request.getRequestDispatcher("/WEB-INF/view/updatecustomer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
