package dd.pyrkova.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;
import java.util.Objects;

@XStreamAlias("user")
@Entity
@Table(name = "addressbook")
public class UserData {
  @XStreamOmitField
  @Id
//  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  @Type(type = "text")
  private String firstname;

  @Expose
  @Column(name = "middlename")
  @Type(type = "text")
  private String middlename;

  @Expose
  @Column(name = "lastname")
  @Type(type = "text")
  private String lastname;

  private String nickname;
  private String company;
  private String address;

  @Expose
  @Column(name = "emailone")
  @Type(type = "text")
  private String emailone;

  private String emailtwo;

  @Expose
  @Column(name = "phonehome")
  @Type(type = "text")
  private String phonehome;

  private String phonemobile;
  private String phonework;

  @Expose
  @Column(name = "birthday")
  private String birthday;

  @Expose
  @Column(name = "birthmonth")
  @Type(type = "text")
  private String birthmonth;

  @Expose
  @Column(name = "birthyear")
  private String birthyear;

  @Expose
  @Column(name = "group")
  @Type(type = "text")
  private String group;

  private String allPhones;
  private String allEmails;
  private File photo;

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() { return address; }

  public String getEmailone() {
    return emailone;
  }

  public String getEmailtwo() {
    return emailtwo;
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

  public String getBirthday() {
    return birthday;
  }

  public String getBirthmonth() {
    return birthmonth;
  }

  public String getBirthyear() {
    return birthyear;
  }

  public String getGroup() {
    return group;
  }

  public String getAllPhones() { return allPhones; }

  public String getAllEmails() { return allEmails;  }

  public File getPhoto() { return photo;  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public UserData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public UserData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public UserData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public UserData withCompany(String company) {
    this.company = company;
    return this;
  }

  public UserData withAddress(String address) {
    this.address = address;
    return this;
  }

  public UserData withEmailone(String emailone) {
    this.emailone = emailone;
    return this;
  }

  public UserData withEmailtwo(String emailtwo) {
    this.emailtwo = emailtwo;
    return this;
  }

  public UserData withPhonehome(String phonehome) {
    this.phonehome = phonehome;
    return this;
  }

  public UserData withPhonemobile(String phonemobile) {
    this.phonemobile = phonemobile;
    return this;
  }

  public UserData withPhonework(String phonework) {
    this.phonework = phonework;
    return this;
  }

  public UserData withBirthday(String birthday) {
    this.birthday = birthday;
    return this;
  }

  public UserData withBirthmonth(String birthmonth) {
    this.birthmonth = birthmonth;
    return this;
  }

  public UserData withBirthyear(String birthyear) {
    this.birthyear = birthyear;
    return this;
  }

  public UserData withGroup(String group) {
    this.group = group;
    return this;
  }

  public UserData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public UserData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public UserData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
            Objects.equals(firstname, userData.firstname) &&
            Objects.equals(lastname, userData.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }
}
