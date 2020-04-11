package dd.pyrkova;

import dd.pyrkova.Rect;
import dd.pyrkova.Squ;

public class FirstProgram {
  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Darya");

    Squ s = new Squ(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rect r = new Rect(4,6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }
}
