package dd.pyrkova.addressbook.appmanager;

import dd.pyrkova.addressbook.model.GroupData;
import dd.pyrkova.addressbook.model.Groups;
import dd.pyrkova.addressbook.model.UserData;
import dd.pyrkova.addressbook.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {
  private final SessionFactory sessionFactory;

  public DbHelper(){
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData" ).list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Users users(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> resultu = session.createQuery( "from UserData where deprecated = '0000-00-00'" ).list();
    session.getTransaction().commit();
    session.close();
    return new Users(resultu);
  }

  public Users groupUsers(int gId){
    Session session = sessionFactory.openSession();
    GroupData group = (GroupData) session.createQuery("from GroupData where id=" + gId).getSingleResult();
    Users users = (Users) group.getUsers();
    session.close();
    return users;
  }

  public UserData userById(int userId){
    Session session = sessionFactory.openSession();
    UserData resutluserid = (UserData) session.createQuery("from UserData where id = " + userId).getSingleResult();
    session.close();
    return resutluserid;
  }

  public GroupData groupById(int groupId){
    Session session = sessionFactory.openSession();
    GroupData resultgroupid = (GroupData) session.createQuery("from GroupData where id = " +groupId).getSingleResult();
    return resultgroupid;
  }
}
