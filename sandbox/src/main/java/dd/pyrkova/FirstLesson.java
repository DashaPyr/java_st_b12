package dd.pyrkova;

public class FirstLesson {
  public static void main(String[] args) {
    Point p1 = new Point(1,1);
    Point p2 = new Point(5,4);
    System.out.println("Расстояние между точками {" + p1.i + "; " + p1.j + "} и {" + p2.i + "; " + p2.j + "} = " + p1.distance(p2));
  }
}
