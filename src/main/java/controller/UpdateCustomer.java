package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import service.CustomerDAO;

/**
 * Servlet implementation class UpdateCustomer
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/updatecustomer" })
public class UpdateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerdao;
	
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	customerdao=new CustomerDAO();
    }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Server at: ").append(request.getContextPath());
		//request.getRequestDispatcher("/WEB-INF/view/updatecustomer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		//int customerID=(Integer)(session.getAttribute("customerID"));
		Customer customer=new Customer();
		customer.setFirstName(request.getParameter("firstname"));
		customer.setLastName(request.getParameter("lastname"));
		customer.setGender(request.getParameter("gender"));
		Date dob=null;
		try {
			dob=new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday")).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customer.setDob(dob);
		customer.setPhonenumber(Long.parseLong(request.getParameter("phonenumber")));
		customer.setEmail(request.getParameter("email"));
		customer.setAddress(request.getParameter("address"));
		
		//customer.setcustomerID(customerID);
		
		try {
			int row=customerdao.updateCustomer(customer);
			if(row>0)
			{
				response.sendRedirect(request.getContextPath()+"/edit");
			}
			else
			{
				request.getRequestDispatcher("/WEB-INF/view/updatecustomer.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
