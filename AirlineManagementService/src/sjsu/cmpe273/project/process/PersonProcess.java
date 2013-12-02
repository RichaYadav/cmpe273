package sjsu.cmpe273.project.process;

import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.dao.PersonDao;

public class PersonProcess {
	PersonDao personDao = new PersonDao();
	public void createPersonProcess(UserBean user){
		personDao.createPerson();
	}
	
	public PersonBean findPersonProcess(UserBean user){
		return personDao.selectPerson(user.getPerson().getPassport_number());
	}
	
	public UserBean loginProcess(String email , String password){
		return personDao.findUser(email, password);
	}
}
