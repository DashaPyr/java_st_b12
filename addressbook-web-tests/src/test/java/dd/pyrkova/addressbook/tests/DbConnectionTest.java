package dd.pyrkova.addressbook.tests;

import dd.pyrkova.addressbook.model.GroupData;
import dd.pyrkova.addressbook.model.Groups;
import dd.pyrkova.addressbook.model.UserData;
import dd.pyrkova.addressbook.model.Users;
import org.testng.annotations.Test;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnection() {
    Connection conn = null;

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=&serverTimezone=UTC");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select l.group_id, l.group_name, l.group_header, l.group_footer  from group_list l");
 //     ResultSet us = st.executeQuery("select u.id, u.firstname, u.lastname from addressbook u");
      Groups groups = new Groups();
      Users users = new Users();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }
      rs.close();
 /*     while (us.next()){
        users.add(new UserData().withId(us.getInt("id"))
                .withFirstname(us.getString("firstname")).withLastname(us.getString("lastname")));
      }
      us.close();*/
      st.close();
      conn.close();

      System.out.println(groups);
  //    System.out.println(users);

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
