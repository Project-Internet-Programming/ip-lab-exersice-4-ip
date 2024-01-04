package dbUtil;

import java.awt.List;

import javax.activation.DataSource;

import model.Instructor;

public class InstructorDAO {
	jdbcTemplate jdbct = new jdbcTemplate(getDataSource());
	//the detail impl for allCRUD methods here
	
	
	//getAll
	public List<Instructor> getAll(){
		//lets use jdbc template provided by spring framework
		String sql = "select * from instructor";
		List<Instructor> iList = jdbct.query(sql,new BeanPropertyRowMapper<Instructor>(Instructor.class));
		return iList;  // list of all instructors dalam database table
	}
	//getById
	public Instructor findById(int id) {
		String sql = "select * from instructor where id = ?";
		Instructor inst = jdbct.queryForObject(sql , new BeanPropertyRowMapper<Instructor>(Instructor.class), id);
		return inst;
	}
	
	//add
	public int add(Instructor inst) {
		String sql = "insert into ''instructor ('name' , 'gender' , 'speciality') values (? , ? , ?)";
		Object args[] = {inst.getName(),inst.getGender(), inst.getSpeciality()};
		int rowAffected = jdbct.update(sql , args);
		return rowAffected;
	}
	
	// delete - complete this
	// update - complete this
	
	
	public DataSource getDataSource() {
		DataSource ds = null;
		
		String dbURL = "jdbc:mysql://localhost:3306/ip23db";
		String username = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ds = new DriverManagerDataSource(dbURL , username , password);
		return ds;
	}


}
