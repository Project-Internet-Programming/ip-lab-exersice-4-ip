package Controllers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ResponseWrapper;

import dbUtil.DbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Controller
//@RequestMapping("/trainee")
@WebServlet("/TraineeController")
public class TraineeController{
	private static final long serialVersionUID = 1L;
       
	//@RequestMapping("/getAll")
	@ResponseWrapper()

   public String getAll() {
		String dbURL = "jdbc:mysql://localhost:3306/ip23db";
		String username = "root";
		String password = "";
		
		String result = null;
		int rowAffected = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // step 1
			Connection conn = DriverManager.getConnection(dbURL,username,password); // step 2
			System.out.println("connection successfully opened:" +conn.getMetaData());
			
			//String sql = "INSERT INTO trainee (name , wieght ,height, bmi) VALUES ('ali',77,7,1,77,21)";
			//PrepareStatement stmt = conn.createStatement();
			
			String sql = "INSERT INTO trainee (name , wieght ,height, bmi) VALUES (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,"ahmed");
			stmt.setDouble(2,80);
			stmt.setFloat(3,(float) 1.80);
			stmt.setDouble(4,21.2);
			
			//rowAffected = stmt.executeUpdate(sql);
			rowAffected = stmt.executeUpdate();
			
			//step 3
			//String sql = "SELECT * from TRAINEE";
			//Statement stmt = conn.createStatement();
			//step 4
			//ResultSet rs = stmt.executeQuery(sql);
					
			//step 5
			/*while(rs.next()) {
				System.out.println("ID: "+rs.getInt("id"));
				System.out.println("name: "+rs.getString(2));
				System.out.println("weight: "+rs.getDouble("weight"));
				
				result = result + "" +rs.getInt(1);
			}
			
			// step 6 & 7
			conn.close();*/
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		//return "this is from getAll - Trainee" + result;
		return "this is from add - Trainee , rowAffected=" + rowAffected ;
	}
	
	@RequestMapping("/getAll")
	@ResponseWrapper()
	public String getById(HttpServletRequest request) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
				Connection conn = DbConnect.openConnection();
				System.out.println("connection successfully opend: " +conn.getMetaData());
				
				String sql = "SELECT *FROM trainee WHERE id="+id;
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					System.out.println("ID: "+rs.getInt("id"));
					System.out.println("name: "+rs.getString(2));
				}
				conn.close();
		}catch (SQLException ex) {
			
		}
			ex.printStackTrace();
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
			return "this is from getById = Trainee";
	}

}
