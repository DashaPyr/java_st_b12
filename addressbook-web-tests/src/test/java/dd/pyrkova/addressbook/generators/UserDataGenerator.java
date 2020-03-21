package dd.pyrkova.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;
import dd.pyrkova.addressbook.model.GroupData;
import dd.pyrkova.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {

  @Parameter (names = "-c", description = "User count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    UserDataGenerator generator = new UserDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<UserData> users = generatorUsers(count);
    if (format.equals("csv")){
      saveAsCsv(users, new File(file));
    } else if (format.equals("xml")){
      saveAsXml(users, new File(file));
    } else if (format.equals("json")){
      saveAsJson(users, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<UserData> users, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(users);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<UserData> users, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(UserData.class);
    String xml = xstream.toXML(users);
    try (Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }

  private static void saveAsCsv(List<UserData> users, File file) throws IOException {
    try (Writer writer = new FileWriter(file)){
      for (UserData user : users) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", user.getFirstname(), user.getMiddlename(),
                user.getLastname(), user.getNickname(), user.getCompany(), user.getAddress(), user.getPhonehome(),
                user.getEmailone(), user.getBirthday(), user.getBirthmonth(), user.getBirthyear()));
      }
    }
  }

  private static List<UserData> generatorUsers(int count) {
    List<UserData> users = new ArrayList<UserData>();
 //   File photo = new File("src/test/resouces/catbus.jpg");
    for (int i = 0; i < count; i++){
      users.add(new UserData().withFirstname(String.format("Dasha %s", i+20))
              .withMiddlename("V").withLastname(String.format("P %s", i+20)).withNickname("Dd").withCompany("A").withAddress("MWO")
              .withPhonehome(String.format("+%s%s", i, i)).withEmailone(String.format("%s@g.c", i+20))
              .withBirthday(String.format("%s", i+1)).withBirthmonth("January").withBirthyear(String.format("199%s", i)));
    }
    return users;
  }
}
