package Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import javax.xml.ws.ResponseWrapper;

import dbUtil.HibernateCF;
import model.Program;

public class FitnessProgramController {
	@RequestMapping("/add") 
	@ResponseWrapper()
	public String add(HttpServletRequest request) {
		
		/*Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		config.addAnnotatedClass(Program.class);
		SessionFactory sessionFactory =  config.buildSessionFactory();
		Session session = sessionFactory.openSession();*/
		
		//Session session =  null;
		Session session = HibernateCF.getSessionFactory().openSession();
		
		Program prog = new Program();
		prog.setName(request.getParameter("name"));
		prog.setNote(request.getParameter("note"));
		
		session.beginTransaction();
		session.save(prog);
		session.getTransaction.commit();
		session.close();
		
		return "this is from add - program";
		
	}
}
