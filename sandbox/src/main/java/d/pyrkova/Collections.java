package d.pyrkova;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String[] args){
//    String[] langs = new String[4]; langs[0] = "Java"; langs[1] = "C#"; langs[2] = "Python"; langs[3] = "PHP";
//    for (int i = 0; i < langs.length; i++){
//      System.out.println("I want to learn " + langs[i]);
//    }

    String[] langs = {"Java", "C#", "Python", "PHP"};

    List<String> languages = Arrays.asList("Java", "Python", "C++");

//    List<String> languages = new ArrayList<String>();
//    languages.add("Java");
//    languages.add("Python");
//    languages.add("C++");

    for (String l : langs){
      System.out.println("I want to learn " + l);
    }

    for (String ln : languages){
      System.out.println("I want to learn " + ln);
    }
  }
}
