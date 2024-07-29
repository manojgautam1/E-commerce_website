package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.ProductDetails;
import service.ProductDAO;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/addproduct" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 *2,
					maxFileSize= 1024 *1024 * 10,
					maxRequestSize =1024 * 1024 *50)
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ProductDAO productdao;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	productdao=new ProductDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("/WEB-INF/view/addproduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String description=request.getParameter("description");
		int price=Integer.parseInt(request.getParameter("price"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		int categoryId=Integer.parseInt(request.getParameter("categoryId"));
		
		Part filePart= request.getPart("image");
		String image_name=filePart.getSubmittedFileName();
		InputStream imageStream=filePart.getInputStream();
		ByteArrayOutputStream process= new ByteArrayOutputStream();
		
		int bytesRead;
		//int sizeInBytes= 2 * 1024 *1024;
		byte[] data =new byte[2* 1024 * 1024];
		while((bytesRead = imageStream.read(data)) !=-1)
		{
			process.write(data, 0, bytesRead);
		}
		byte[] imageData=process.toByteArray();
		
		ProductDetails product= new ProductDetails();
		
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setQuantity(quantity);
		product.setCategoryId(categoryId);
		
		product.setImage_data(imageData);
		product.setImage_name(image_name);
		
		
		if(productdao.saveProduct(product)) {
			
			response.sendRedirect(request.getContextPath()+"/admin"); 
		} 
		else 
		{
			request.setAttribute("error1", "invalid data");
			response.sendRedirect(request.getContextPath()+"/addproduct");
			//doGet(request, response);
		}

		
		
	}

}
