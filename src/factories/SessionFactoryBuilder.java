package factories;

import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.Equipment;

public class SessionFactoryBuilder {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory()
	{
		try
		{
			if(sessionFactory == null)
			{
				sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Equipment.class).buildSessionFactory();
			}
			
		}catch(SessionException se)
		{
			se.printStackTrace();
		}
		return sessionFactory;
	}
	
	public static void closeSessionFactory()
	{
		try
		{
			if(sessionFactory != null)
			{
				sessionFactory.close();
			}
		}catch(SessionException se)
		{
			se.printStackTrace();
		}
	}
}
