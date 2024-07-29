package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerDAO;
import utils.PasswordHashing;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static CustomerDAO customerdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	customerdao=new CustomerDAO();
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String gender=request.getParameter("gender");
		Date dob=null;
		
		try {
			dob= new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday")).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long phonenumber=Long.parseLong(request.getParameter("phonenumber"));
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String password= request.getParameter("password");
		String retypePassword=request.getParameter("retypePassword");
		
		if(!password.equals(retypePassword))
		{
			request.setAttribute("firstname", firstname);
			request.setAttribute("error", "password doesnot match");
			request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request, response);
		}
		
		Customer customer= new Customer();
		customer.setFirstName(firstname);
		customer.setLastName(lastname);
		customer.setGender(gender);
		customer.setDob(dob);
		customer.setPhonenumber(phonenumber);
		customer.setEmail(email);
		customer.setAddress(address);
		customer.setPassword(PasswordHashing.getPasswordHash(password));
		
		if(customerdao.saveUser(customer))
		{
			response.sendRedirect(request.getContextPath()+"/login");
		}
		else
		{
			request.setAttribute("error1", "email or phonenumber is in already use!!");
			doGet(request,response);
		}
		
		
		
	}

}
