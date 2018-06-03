package com.agrovision.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author Jesus
 *
 */
public class SessionUtil {
    private static SessionUtil instance=new SessionUtil();
    private SessionFactory sessionFactory;
    
    /**
     * 
     * @return
     */
    public static SessionUtil getInstance(){
            return instance;
    }
    
    /**
     * 
     */
    private SessionUtil(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
                
        sessionFactory = configuration.buildSessionFactory();
    }
    
    /**
     * 
     * @return
     */
    public static Session getSession(){
        Session session =  getInstance().sessionFactory.openSession();
        
        return session;
    }

}