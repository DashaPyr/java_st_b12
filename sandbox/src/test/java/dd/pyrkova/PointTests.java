package dd.pyrkova;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class PointTests {
  public void testDistance (){
    Point p1 = new Point(3,7);
    Point p2 = new Point(8,8);
    Assert.assertEquals(p1.distance(p2),4);
  }
}
