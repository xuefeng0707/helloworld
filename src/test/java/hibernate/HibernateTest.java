package hibernate;

/*import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("/hibernate/hibernate.cfg.xml")
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		User user = new User();
		user.setUsername("test");
		user.setPassword("test");
		session.save(user);
		
		user = new User();
		user.setUsername("test01");
		user.setPassword("test01");
		session.save(user);
		
		List<User> users = session.createQuery("from User where username='test'").list();
		for(User u : users) {
			System.out.println(u);
		}
		
		session.getTransaction().commit();
		session.close();
	}
}
*/