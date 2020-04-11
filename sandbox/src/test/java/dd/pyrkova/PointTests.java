package dd.pyrkova;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance (){
    Point p1 = new Point(4,1);
    Point p2 = new Point(8,4);
    Assert.assertEquals(p1.distance(p2),5);
  }
}