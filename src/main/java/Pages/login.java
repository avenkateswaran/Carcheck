package Pages;

import cucumberTest.Hooks;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class login extends Hooks {

    //Element Declaration

    @FindBy(id="vrm-input")
    private WebElement txtRegistrationId;

    @FindBy(xpath="//form/button[text()='Free Car Check']")
    private WebElement btnFreeCarCheck;



    public void launchURL(String url){

        driver.get(url);
    }
    public void carcheckLogin(String regId){

        txtRegistrationId.clear();
        txtRegistrationId.sendKeys(regId);
        btnFreeCarCheck.click();

    }
}
