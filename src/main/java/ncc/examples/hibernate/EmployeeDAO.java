package ncc.examples.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ncc.examples.hibernate.Employee;
import ncc.examples.hibernate.HibernateUtil;

import java.util.UUID;



public class EmployeeDAO {

    static private EmployeeDAO employeeDAO;

    static private SessionFactory sessionFactory;

    public static EmployeeDAO getInstance() {
        if (employeeDAO == null) {
            employeeDAO = new EmployeeDAO();
        }
        if (sessionFactory == null) {
            sessionFactory = HibernateUtil.getSessionFactory();
        }
        return employeeDAO;
    }

    private EmployeeDAO() {}

    public Employee getEmployee(UUID id) {
        Session session = sessionFactory.openSession();
        Employee employee = (Employee) session.get(Employee.class, id);
        session.close();
        return employee; 
    }

    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.save(employee);

        session.getTransaction().commit();

        session.close(); 
    }

}
