package service;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProductDetails;
import utils.DatabaseConnection;

public class ProductDAO {
	
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resultset;
	private boolean isSuccess;
	private static final String inserting_query="insert into products(name,description,price,quantity,categoryId,image_data,image_name) values(?,?,?,?,?,?,?)";
	
	public ProductDAO() {
		conn=DatabaseConnection.getDbConnection();
		
		
	}
	
	//It checks whether the products table has data and count rows if so
	/*If check(product) returns false, the setproduct(product) method is called, 
	 * which inserts the ProductDetails object's data into the table.*/
	
	public boolean saveProduct(ProductDetails product)
	{
		
		try 
		{
			statement=conn.prepareStatement("select count(*) from products");
			resultset = statement.executeQuery();
			
			if(resultset.next())
			{
				if(check(product))
				{
					isSuccess=false;
				}
				else 
				{
					int row=setproduct(product);
					if(row>0)
					{
						isSuccess=true;
					}else
					{
						isSuccess=false;
					}
				}
			}
			else
			{
				int row = setproduct(product);
				if (row > 0) 
				{
				isSuccess = true;
				}
			}
		}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
}

	private int setproduct(ProductDetails product) {
		// TODO Auto-generated method stub
		int row=0;
		
		try {
			
			statement=conn.prepareStatement(inserting_query);
			
			Blob blob=conn.createBlob();
			
			
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setInt(3, product.getPrice());
			statement.setInt(4, product.getQuantity());
			statement.setInt(5, product.getCategoryId());
			
			//image
			
			blob.setBytes(1, product.getImage_data());
			statement.setBlob(6, blob);
			statement.setString(7, product.getImage_name());
			
			row=statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	private boolean check(ProductDetails product) {
		// checking if there is a same product name existing already
		boolean isSame=false;
		
		try {
			statement=conn.prepareStatement("select name from products");
			resultset=statement.executeQuery();
			
			while (resultset.next()) 
			{
				if (product.getName().equals(resultset.getString("name"))) 
				{
					isSame = true;
					break;
				} 
			}
			}
				catch (SQLException e) 
				{
			// TODO Auto-generated catch block
			e.printStackTrace();
				}
		
		return isSame;
	}
	
	public List<ProductDetails> getAllProduct() throws SQLException
	{
		statement=conn.prepareStatement("select * from products");
		resultset=statement.executeQuery();
		List<ProductDetails> productlist=new ArrayList<ProductDetails>();
		
		while(resultset.next())
		{
			//int id=resultset.getInt("productID");
			String name=resultset.getString("name");
			String description=resultset.getString("description");
			int price =resultset.getInt("price");
			int quantity=resultset.getInt("quantity");
			int categoryId=resultset.getInt("categoryId");
			
			ProductDetails product=new ProductDetails();
			product.setName(name);
			product.setDescription(description);
			product.setPrice(price);
			product.setQuantity(quantity);
			product.setCategoryId(categoryId);
			product.setImage_data(resultset.getBlob("image_data").getBytes(1, (int)resultset.getBlob("image_data").length()));
			product.setImage_name(resultset.getString("image_name"));
		    
			productlist.add(product);
		}
		resultset.close();
        statement.close();
        conn.close();
		return productlist;
	}
	
	public ProductDetails getProductById(int productID) throws SQLException
	{
		statement=conn.prepareStatement("select name,description,price,qunatity,categoryId,image_name from products where productID=?");
		statement.setInt(1, productID);
		resultset= statement.executeQuery();
		
		ProductDetails product= new ProductDetails();
		if(resultset.next())
		{
			product.setName(resultset.getString("name"));
			product.setDescription(resultset.getString("description"));
			product.setPrice(resultset.getInt("price"));
			product.setQuantity(resultset.getInt("quantity"));
			product.setCategoryId(resultset.getInt("categoryId"));
			//product.setImage_data(resultSet.getBlob());
			product.setImage_name(resultset.getString("image_name"));
			
		}
		return product;
	}
	
	public int updateProduct(ProductDetails product) throws SQLException {
		int row=0;
		if(isProductNameSimilar(product.getName(),product.getProudctID()))
		{
			return row;
		}
		else
		{
			statement=conn.prepareStatement("update products set name=?,description=?,price=?,quantity=?,categoryId=?,image_data=?,image_name=?");
		}
		return row;
		
	}

	private boolean isProductNameSimilar(String name, int proudctID) throws SQLException {
		// TODO Auto-generated method stub
		statement=conn.prepareStatement("select count (*) as count_id from products where name=? and productID=?");
		statement.setString(1, name);
		statement.setInt(2, proudctID);
		if(resultset.next())
		{
			int row_num=resultset.getInt("count_id");
			if(row_num>0)
			{
				return true;
			}
		}
		return false;
	}

}
