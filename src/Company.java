import java.util.ArrayList;

public class Company {

	private int companyId;
	private String companyName;
	private ArrayList<User> employees;
	
	public Company(String name, User user) {
		companyName = name;
		employees.add(user);
		companyId = 0; //TODO
	}
	
	public String name() {
		return companyName;
	}
	
	public int id() {
		return companyId;
	}
	
	public ArrayList<User> employeeList() {
		return employees;
	}
	
	public int employeeCount() {
		return employees.size();
	}
	
	public boolean assign(User user) {
		employees.add(user);
		return true;
	}
	
	
	
	
	
}
