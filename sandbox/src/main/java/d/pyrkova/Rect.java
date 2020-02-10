package d.pyrkova;

public class Rect {
  public double a;
  public double b;

  public Rect(double a, double b){
    this.a = a;
    this.b = b;
  }

  public double area(){
    return this.a * this.b;
  }
}
