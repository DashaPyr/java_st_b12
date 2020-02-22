package d.pyrkova;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTests {

  @Test
  public void test0() {
    Equation e = new Equation(1,1,1);
    Assert.assertEquals(e.RootNumber(), 0);
  }

  @Test
  public void test1() {
    Equation e = new Equation(1,2,1);
    Assert.assertEquals(e.RootNumber(), 1);
  }

  @Test
  public void test2() {
    Equation e = new Equation(1,5,4);
    Assert.assertEquals(e.RootNumber(), 2);
  }
}
