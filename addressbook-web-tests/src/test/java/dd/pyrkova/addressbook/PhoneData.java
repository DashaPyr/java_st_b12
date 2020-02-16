package dd.pyrkova.addressbook;

public class PhoneData {
  private final String phonehome;
  private final String phonemobile;
  private final String phonework;

  public PhoneData(String phonehome, String phonemobile, String phonework) {
    this.phonehome = phonehome;
    this.phonemobile = phonemobile;
    this.phonework = phonework;
  }

  public String getPhonehome() {
    return phonehome;
  }

  public String getPhonemobile() {
    return phonemobile;
  }

  public String getPhonework() {
    return phonework;
  }
}
