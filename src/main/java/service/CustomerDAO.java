package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import utils.DatabaseConnection;
import utils.PasswordHashing;

public class CustomerDAO {
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet Set;
	private boolean isSuccess;
	private static final String inserting_query="insert into customer(firstname,lastname,gender,dob,phonenumber,email,address,password)"
			+ "values(?,?,?,?,?,?,?,?) ";
	
	public CustomerDAO()
	{
		conn=DatabaseConnection.getDbConnection();
	}
	 
	public boolean saveUser(Customer customer )
	{
		
		try 
		{
			statement=conn.prepareStatement("select count(*) from customer");
			Set = statement.executeQuery();
			
			if(Set.next())
			{
				if(check(customer))
				{
					isSuccess=false;
				}
				else 
				{
					int row=setcustomer(customer);
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
				int row = setcustomer(customer);
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

	private int setcustomer(Customer customer) {
		int row=0;
		
		try {
			statement= conn.prepareStatement(inserting_query);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getGender());
			statement.setDate(4, customer.getDob());
			statement.setLong(5, customer.getPhonenumber());
			statement.setString(6, customer.getEmail());
			statement.setString(7, customer.getAddress());
			statement.setString(8, customer.getPassword());
			
			row=statement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return row;
	}

	
	public boolean check(Customer customer) {
        boolean isFind = false;
        try {
            statement = conn.prepareStatement("select email,phonenumber from customer");
            Set = statement.executeQuery();

            while (Set.next()) {
                if (customer.getEmail().equals(Set.getString("email"))) {
                    isFind = true;
                    break;
                } else if (customer.getPhonenumber() == Set.getLong("phoneNumber")) {
                    isFind = true;
                    break;
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isFind;
    }
 
	public boolean customerLogin(String email, String password) throws SQLException
	{
		statement=conn.prepareStatement("select email,password,customerID from customer where email=?");
		statement.setString(1, email);
		Set= statement.executeQuery();
		 boolean isSuccess=false;
		
		if(Set.next())
		{
			String passwordFromDb=Set.getString("password");
			if(PasswordHashing.verifyPassword(password, passwordFromDb))
			{
				isSuccess=true;
			}
			else
			{
				isSuccess=false;
			}
		}
		return isSuccess;
	}
	public List<Customer> getAllCustomer() throws SQLException
	{
		statement=conn.prepareStatement("select * from customer");
		Set=statement.executeQuery();
		List<Customer> customerlist=new ArrayList<Customer>();
		
		while(Set.next())
		{
		
			int customerID=Set.getInt("customerID");
			String firstname=Set.getString("firstname");
			String lastname=Set.getString("lastname");
			String gender=Set.getString("gender");
			Date dob=Set.getDate("dob");
			long phonenumber=Set.getLong("phonenumber");
			String email=Set.getString("email");
			String address=Set.getString("address");
			
			Customer customer=new Customer();
			
			customer.setFirstName(firstname);
			customer.setLastName(lastname);
			customer.setGender(gender);
			customer.setDob(dob);
			customer.setPhonenumber(phonenumber);
			customer.setEmail(email);
			customer.setAddress(address);
			
			
			customerlist.add(customer);
			
		}return customerlist;
	}
	
	public Customer getCustomerByID(int customerID) throws SQLException
	{
		statement=conn.prepareStatement("select firstname,lastname,gender,dob,phonenumber,email,address"
				+ "from customer where customerID=?");
		statement.setInt(1, customerID);
		Set=statement.executeQuery();
		Customer customer=new Customer();
		
		if(Set.next())
		{	
			customer.setcustomerID(Set.getInt("customerID"));		
			customer.setFirstName(Set.getString("firstname"));
			customer.setLastName(Set.getString("lastname"));
			customer.setGender(Set.getString("gender"));
			customer.setDob(Set.getDate("dob"));
			customer.setPhonenumber(Set.getLong("phonenumber"));
			customer.setEmail(Set.getString("email"));
			customer.setAddress(Set.getString("address"));
			
		}
		return customer;
	}
	public int updateCustomer(Customer customer) throws SQLException
	{
		int row=0;
		int customerID = 0;
		statement.setInt(1, customerID);
		
		if(isEmailTakenByOther(customer.getEmail(),customer.getcustomerID()))
		{
			return row;
		}
		else if(isPNumberTakenByOther(customer.getPhonenumber(),customer.getcustomerID()))
		{
			return row;
		}
		else
		{
			statement=conn.prepareStatement("update customer set firstname=?,lastname=?,gender=?,dob=?,"
					+ "phonenumber=?,email=?,address=? where email=?");
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getGender());
			statement.setDate(4, customer.getDob());
			statement.setLong(5, customer.getPhonenumber());
			statement.setString(6, customer.getEmail());
			statement.setString(7, customer.getAddress());
			
			row=statement.executeUpdate();
			
		}return row;
		
	}

	private boolean isPNumberTakenByOther(long phonenumber, int getcustomerID) throws SQLException {
		// TODO Auto-generated method stub
		statement=conn.prepareStatement("select count (*) as count_id from customer where phonenumber=? and customerID!=?");
		statement.setLong(1, phonenumber);
		statement.setInt(2, getcustomerID);
		Set=statement.executeQuery();
		if(Set.next())
		{
			int row_num=Set.getInt("count_id");
			if(row_num>0)
			{
				return true;
			}
		}
		return false;
	}

	private boolean isEmailTakenByOther(String email, int getcustomerID) throws SQLException {
		// TODO Auto-generated method stub
		statement=conn.prepareStatement("select count (*) as count_id from customer where email=? and customerID!=?");
		statement.setString(1, email);
		statement.setInt(2, getcustomerID);
		Set=statement.executeQuery();
		if(Set.next())
		{
			int row_num=Set.getInt("count_id");
			if(row_num>0)
			{
				return true;
			}
		}
		return false;
	}
	


}

