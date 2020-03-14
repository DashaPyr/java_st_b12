package dd.pyrkova.addressbook.generators;

import dd.pyrkova.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<UserData> users = generatorUsers(count);
    save(users, file);
  }

  private static void save(List<UserData> users, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (UserData user : users) {
      writer.write(String.format("%s;%s;%s;%s;%s\n", user.getFirstname(),
              user.getMiddlename(), user.getLastname(), user.getPhonehome(), user.getEmailone()));
    }
    writer.close();
  }

  private static List<UserData> generatorUsers(int count) {
    List<UserData> users = new ArrayList<UserData>();
    for (int i = 0; i < count; i++){
      users.add(new UserData().withFirstname(String.format("Dasha %s", i))
              .withMiddlename("V").withLastname(String.format("P %s", i))
              .withPhonehome(String.format("+%s%s", i, i)).withEmailone(String.format("%s@g.c", i)));
    }
    return users;
  }
}
