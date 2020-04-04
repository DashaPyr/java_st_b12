package dd.pyrkova.soap;


import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

  @Test()
  public void testIP() {
    String myGeo = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("95.72.237.7");
    System.out.println(myGeo);
  }
}
