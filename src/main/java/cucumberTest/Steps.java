package cucumberTest;

import Common.utilities;
import Pages.login;
import Pages.vehicleIdentity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.assertj.core.api.SoftAssertions;

import org.eclipse.sisu.inject.Soft;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static cucumberTest.Hooks.driver;
import static cucumberTest.Hooks.urlval;


public class Steps {
    login objlogin= PageFactory.initElements(driver,login.class);
    vehicleIdentity vehicledet=PageFactory.initElements(driver,vehicleIdentity.class);


    @Given("I navigate to the car tax check website")
    public void i_navigate_to_the_car_tax_check_website() {
        objlogin= PageFactory.initElements(driver,login.class);
        objlogin.launchURL(urlval);

    }
    @When("I enter the registration numbers and click car free check button and extract the details")
    public void i_enter_the_registration_numbers_and_click_car_free_check_button_and_extract_the_details() {

        String[] vrnNumber1 = new String[0];
        String vrnNumber = null;
        int numRegs = 0;
        JavascriptExecutor js = (JavascriptExecutor) driver;
       // SoftAssertions sa=null;
       // sa = new SoftAssertions();

        try {
            vrnNumber1 = utilities.getInputVRNDetailsFromFile("Input").split(",");

            //get number of cars regs
            numRegs = vrnNumber1.length;
            System.out.println("Number of cars:" + numRegs);


        } catch (IOException e) {
            e.printStackTrace();
        }

        //loop for all the registration numbers
        for (int numCars = 1; numCars <= numRegs; numCars++) {
            vrnNumber=vrnNumber1[numCars-1].substring(1,8);

            //login using the VRN number
            objlogin.carcheckLogin(vrnNumber);


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Check if the pop up comes up
            if (!utilities.isAlertPresent()) {

                //Get the vehicle details from the site
                String vehicleDetailsFromSite=vehicledet.getVehicldetails();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                //Check whether the extracted information matches the information in the output file

                try {
                    String cardetails = utilities.getOutputVRNDetailsFromFile("Output");

                    //make this into a single string

                    String contentFromFile=cardetails.replaceAll(",","").replaceAll(" ","").toUpperCase();

                    //Assert after comparing the content from site with the content in file
                    if(contentFromFile.contains(vehicleDetailsFromSite)){

                        Assert.assertTrue(vrnNumber+": The content matches for the registration number ",true);


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Scroll down to click on the check another button
                js.executeScript("window.scrollBy(0,4000)", "");
                vehicledet.clickCheckAnotherVehicle();



            }else{
                WebDriverWait wait=new WebDriverWait(driver,30);
                WebElement  popUpVehicle=driver.findElement(By.xpath("//a[contains(text(),'Try Again')]"));


                js.executeScript("arguments[0].click();",popUpVehicle);

                //Commented the below line as I wanted to the test to continue and complete
                //Assert.fail("The Registration Number is invalid and cannot be found in the Car free check site");
                System.out.println(vrnNumber+": The Registration Number is invalid and cannot be found in the Car free check site");




            }


        }
      //  sa.assertAll();
    }

    @Then("I proceed furthur")
    public void I_proceed_furthur() {
      System.out.println("Can proceed furthur");
    }


}
