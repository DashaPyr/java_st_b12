package dd.pyrkova.addressbook;

public class BirthData {
  private final String birthday;
  private final String birthmonth;
  private final String birthyear;

  public BirthData(String birthday, String birthmonth, String birthyear) {
    this.birthday = birthday;
    this.birthmonth = birthmonth;
    this.birthyear = birthyear;
  }

  public String getBirthday() {
    return birthday;
  }

  public String getBirthmonth() {
    return birthmonth;
  }

  public String getBirthyear() {
    return birthyear;
  }
}
