package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = Util.getHibernateInstance().getSession();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = Util.getHibernateInstance().getSession();
            session.createSQLQuery("DROP TABLE IF EXISTS users");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = Util.getHibernateInstance().getSession();
            session.save(User.builder()
                    .name(name)
                    .lastName(lastName)
                    .age(age)
                    .build());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = Util.getHibernateInstance().getSession();
            session.delete(User.builder().id(id).build());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        try {
            session = Util.getHibernateInstance().getSession();
            List<User> list = session.createCriteria(User.class).list();
            return list;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = Util.getHibernateInstance().getSession();
            session.createQuery("DELETE FROM User");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
