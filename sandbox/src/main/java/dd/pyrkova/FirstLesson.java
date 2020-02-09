package dd.pyrkova;

public class FirstLesson {
  public static void main(String[] args) {
    Point p1 = new Point();
    Point p2 = new Point();
    p1.i = 1;
    p1.j = 1;
    p2.i = 5;
    p2.j = 4;
    System.out.println("Расстояние между точками {" + p1.i + "; " + p1.j + "} и {" + p2.i + "; " + p2.j + "} = " + distance(p1, p2));
  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt((p2.i - p1.i) * (p2.i - p1.i) + (p2.j - p1.j) * (p2.j - p1.j));
  }
}
