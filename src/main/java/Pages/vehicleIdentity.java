package Pages;


import cucumberTest.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class vehicleIdentity extends Hooks {

    @FindBy(xpath="//a[contains(text(),'Check Another')]")
    private WebElement checkAnotherVehicle;


   // public Map<String, String> getVehicldetails() {
      public String getVehicldetails() {

          //Map<String, String> vehicleInformation = new HashMap<String, String>();

          try {
              Thread.sleep(5000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          int vehicledet = driver.findElements(By.xpath("//h4[text()='Vehicle Identity']/parent::div/following-sibling::div/dl")).size();


          //get the vehilcle Properties and its value

          String getvehicleprop = null;
          String getvehiclepropValue=null;
          String getVehicleProperties = null;
          for (int i = 1; i <= 5; i++) {
              getvehicleprop = driver.findElement(By.xpath("//h4[text()='Vehicle Identity']/parent::div/following-sibling::div/dl[" + i + "]/dt")).getText();

              getvehiclepropValue = driver.findElement(By.xpath("//h4[text()='Vehicle Identity']/parent::div/following-sibling::div/dl[" + i + "]/dd")).getText();

              if(i==1) {
                  getVehicleProperties = getvehiclepropValue;
              }else {
                  getVehicleProperties = getVehicleProperties.concat(getvehiclepropValue);
              }
              //vehicleInformation.put(getvehicleprop, getvehiclepropValue);


          }
          //System.out.println(getVehicleProperties.toUpperCase().replaceAll(" ",""));
          return getVehicleProperties.toUpperCase().replaceAll(" ","");

      }

    public void clickCheckAnotherVehicle(){

        checkAnotherVehicle.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}
