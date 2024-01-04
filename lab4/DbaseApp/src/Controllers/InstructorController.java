import java.awt.List;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import javax.xml.ws.ResponseWrapper;

import dbUtil.HibernateCF;
import dbUtil.InstructorDAO;
import model.Instructor;

@Controller
@RequestMapping("/instructor")

public class InstructorController {
	private static final HibernateCF HebernateCF = null;
	@RequestMapping("/getAll")
	@ResponseWrapper()
	
	public String getAll(Mode model) {
		/*InstructorDAO insdao = new Instructor();
		List<Instructor> ilist = insdao.getAll();
		model.addAtribute(ilist);*/
		
		Session session = HibernateCF.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		List<Program> plist = session.createQuery("from program").list();
		
		return "this is from getAll - INstructor"+ plist.toString();
	}
	@RequestMapping("/getById")
	//@ResponseBody()
	
	public String getById(HttpServletRequest request ,Mode mod) {
		
		Session session = HibernateCF.getSessionFactory().openSession();
		
		int id = Integer.parseInt(request.getParameter("id"));
		//InstructorDAO insdao = new InstructorDAD();
		
		//Instructor inst1 = insdao.findById(id);
		Program p = session.get(Program.class , id);
		mod.addAtribute("p" , p);
		
		//return "this is from getById - INstructor"+ p.toString();
		
		return "ProgramDisplayInfo";
	}
	@RequestMapping("/add")
	@ResponseWrapper()
	public String add(HttpServletRequest request) {
		Instructor i = new Instructor();
		i.setName(request.getParameter("name"));
		i.setGender(request.getParameter("gender"));
		i.setSpeciality(request.getParameter("speciality"));
		
		InstructorDAO insdao = new InstructorDAO();
		int rw = insdao.add(i);
		
		return "this is from add - Instructor ... row affected : "+rw;

	}
	@RequestMapping("/update") 
	@ResponseWrapper()
	public String update(HttpServletRequest request) {
			
		Session session = HebernateCF.getSessionFactory().openSession();
		
		Program p2u = session.get(Program.class,Integer.parseInt(request.getParameter("id")));
		p2u.setName("aerobic class");
		p2u.setName(request.getParameter("note"));
		
		session.beginTransaction();
		session.update(p2u);
		session.getTransaction().commit();
		
		return "this is from update - program";
	}
	@RequestMapping("/delete") 
	@ResponseWrapper()
	public String delete(HttpServletRequest request) {
		
		Session session = HibernateCF.getSessionFactory().openSession();
		
		Program p2d = session.get(Program.class ,Integer.parseInt(request.getParameter("id")));
		
		session.beginTransaction();
		session.update(p2d);
		session.getTransaction().commit();
		
		return "this is from delete - program: "+p2d.toString() + "has been deleted";
	}
}	