package dd.pyrkova;

public class Point {
  public double i;
  public double j;

  public Point (double i, double j){
    this.i = i;
    this.j = j;
  }

  public double distance(dd.pyrkova.Point p2){
    return Math.sqrt((p2.i - this.i) * (p2.i - this.i) + (p2.j - this.j) * (p2.j - this.j));
  }
}
