package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.*;
import models.*;

/**
 *
 * @author Alex Tompkins - 821984
 */
public class UserDB {

    public List<User> getAll() throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        return em.createNamedQuery("User.findAll", User.class).getResultList();
        
    }

    public User get(String email) throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
        return query.setParameter("email", email).getSingleResult();
        
    }
    
    public void insert(User user) throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
        

    }

    public void update(User user) throws Exception {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
        
    }

    public void delete(User user) throws Exception {
        
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
        
    }
}
