package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerdao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginform=request.getParameter("submit");
		
		if(loginform !=null)
		{
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			
				if(email.equals("admin@gmail.com") && password.equals("admin123"))
				{
					HttpSession session = request.getSession();
					session.setAttribute("email", email);
					session.setAttribute("role", "admin");
					session.setMaxInactiveInterval(30 * 60);
					response.sendRedirect(request.getContextPath()+"/admin");
					HttpSession usersession= request.getSession();
					usersession.setAttribute("email", usersession);
					usersession.setMaxInactiveInterval(30 * 60);
					
					Cookie userCookie=new Cookie("email",email);
					userCookie.setMaxAge(30 * 60);
					response.addCookie(userCookie);
					
				}
				else {
					boolean isSuccess;
					try {
						isSuccess = customerdao.customerLogin(email, password);
						if(isSuccess)
						{
							HttpSession session=request.getSession();
							session.setAttribute("email", email);
							session.setAttribute("role", "customer");
							session.setMaxInactiveInterval(30 *60);
							response.sendRedirect(request.getContextPath()+"/userhome");
							HttpSession usersession= request.getSession();
							usersession.setAttribute("email", usersession);
							usersession.setMaxInactiveInterval(30 * 60);
							
							Cookie userCookie=new Cookie("email",email);
							userCookie.setMaxAge(30 * 60);
							response.addCookie(userCookie);
						}
						else {
							request.setAttribute("error", "invalid username or password");
							doGet(request, response);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}else 
		{doGet(request,response);}
	}
}