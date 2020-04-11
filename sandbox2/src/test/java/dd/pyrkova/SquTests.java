package dd.pyrkova;

import org.testng.annotations.Test;

public class SquTests {

  @Test
  public void testArea(){
    Squ s = new Squ(3);
    assert s.area() == 9;
  }
}
