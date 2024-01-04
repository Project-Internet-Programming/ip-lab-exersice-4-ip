package Controllers;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "program")
public class Program {

	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}
	@Id
	@Column(name = "id")
	int id;
	@Column(name = "name")
	String name;
	@Column(name = "day")
	String day;
	@Column(name = "time")
	String time;
	@Column(name = "note")
	String note;
	@Column(name = "instructor_id")
	int instructor_id;

}
