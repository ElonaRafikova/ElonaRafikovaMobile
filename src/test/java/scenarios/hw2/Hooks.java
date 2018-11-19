package scenarios.hw2;

import enums.PropertiesFile;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.Driver;

@Test
public class Hooks extends Driver {

    protected Hooks() {
        super();
    }

    @BeforeSuite(description = "Prepare driver to run native test(s)", groups = "native")
    @Parameters("properties")
    public void setUpNative(String properties) throws Exception {
        PropertiesFile propertiesFile = PropertiesFile.find(properties);
        setPropertiesFile(propertiesFile);
        prepareDriver();
        System.out.println("Driver prepared");

    }

    @BeforeSuite(description = "Prepare driver to run web test(s)", groups = "web")
    @Parameters("properties")
    public void setUpWeb(String properties) throws Exception {
        PropertiesFile propertiesFile = PropertiesFile.find(properties);
        setPropertiesFile(propertiesFile);
        prepareDriver();
        System.out.println("Driver prepared");

    }

    @AfterSuite(description = "Close driver on all tests completion", groups = {"native", "web"})
    public void tearDown() throws Exception {
        driver().quit();
        System.out.println("Driver closed");

    }

}
