package dd.pyrkova.addressbook;

public class EmailData {
  private final String emailone;
  private final String emailtwo;

  public EmailData(String emailone, String emailtwo) {
    this.emailone = emailone;
    this.emailtwo = emailtwo;
  }

  public String getEmailone() {
    return emailone;
  }

  public String getEmailtwo() {
    return emailtwo;
  }
}
