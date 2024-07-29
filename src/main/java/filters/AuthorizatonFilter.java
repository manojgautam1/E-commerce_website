package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizatonFilter implements Filter {
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		
		
		String role=(String) request.getSession().getAttribute("role");
		if(role != null && role.equals("admin"))
		{
			if(role != null && role.equals("admin") && request.getRequestURI().endsWith("addproduct"))
			{
				request.getRequestDispatcher("addproduct").forward(request, response);
			}
			else if(role != null && role.equals("admin") && request.getRequestURI().endsWith("update"))
			{
				request.getRequestDispatcher("updateproduct").forward(request, response);
			}
			else if(role != null && role.equals("admin") && request.getRequestURI().endsWith("admin"))
			{
				response.sendRedirect(request.getContextPath()+"/admin");
			}
			else {
				request.getRequestDispatcher("admin").forward(request, response);
			}
		}
		else if(role != null && role.equals("customer"))
		{
			if(request.getRequestURI().endsWith("customer"))
			{
				request.getRequestDispatcher("logout").forward(request, response);
				
			}
			else if(role != null && role.equals("customer") && ! request.getRequestURI().endsWith("profile"))
			{
				response.sendRedirect(request.getContextPath()+"/profile");
				
			}
			else
			{
				request.getRequestDispatcher("profile").forward(request, response);
			}
		}
		else
		{
			chain.doFilter(request, response);
			
		}
		
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
