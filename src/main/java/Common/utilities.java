package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cucumberTest.Hooks.driver;

public class utilities {


    public static String getInputVRNDetailsFromFile(String FolderName) throws IOException {

        Pattern pattern = Pattern.compile("[a-z]{2}[0-9]{2}\\s?[a-z]{3}", Pattern.CASE_INSENSITIVE);

        File[] files = new File("src//Files//"+FolderName).listFiles((dir, name) -> name.endsWith(".txt"));

        Set<String> inputVRNs = new HashSet<>();

        for (File f : files) {
            String fileContent = new String(Files.readAllBytes(Paths.get(f.getAbsolutePath())), StandardCharsets.UTF_8);
            Matcher matcher = pattern.matcher(fileContent);

            while (matcher.find()) {
                String vrn = matcher.group().toUpperCase().replaceAll("\\s", "");
                inputVRNs.add(vrn);
            }
        }

        //System.out.println(inputVRNs);
        return String.valueOf(inputVRNs);

    }

    public static boolean  isAlertPresent()
    {
        try
        {
            driver.findElement(By.xpath("//a[contains(text(),'Try Again')]"));
            return true;
        }   // try
        catch (NoSuchElementException Ex)
        {
            return false;
        }   // catch
    }   // isAlertPresent()

    public static String getOutputVRNDetailsFromFile(String FolderName) throws IOException {

        //Pattern pattern = Pattern.compile("[a-z]{2}[0-9]{2}\\s?[a-z]{3}", Pattern.CASE_INSENSITIVE);

        File[] files = new File("src//Files//" + FolderName).listFiles((dir, name) -> name.endsWith(".txt"));

        //Set<String> carOutputDetails = new HashSet<>();

        String fileContent = null;
        for (File f : files) {
            fileContent = new String(Files.readAllBytes(Paths.get(f.getAbsolutePath())), StandardCharsets.UTF_8);
            //Matcher matcher = pattern.matcher(fileContent);

//            while (matcher.find()) {
//                String vrn = matcher.group().toUpperCase().replaceAll("\\s", "");
//                inputVRNs.add(vrn);
//            }
        }

        //System.out.println(fileContent);
        return String.valueOf(fileContent);

    }

}
