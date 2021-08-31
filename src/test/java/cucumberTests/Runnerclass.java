package cucumberTests;

//import cucumber.api

import cucumberTest.Steps;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features",glue = {"cucumberTest"},publish = true)
public class Runnerclass {

}
