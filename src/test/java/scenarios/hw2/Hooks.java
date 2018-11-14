package scenarios.hw2;

import enums.PropertiesFile;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import setup.Driver;

import java.io.IOException;

@Test
public class Hooks extends Driver {
    /**
     * Required variables will be initialized by inherited constructor
     *
     * @throws IOException
     */
    protected Hooks() throws IOException {
        super();
    }

    @BeforeSuite(description = "Prepare driver to run test(s)", groups = "native")
    public void setUpNative() throws Exception {
        prepareDriver(PropertiesFile.NATIVE);
        System.out.println("Driver prepared");

    }

    @BeforeSuite(description = "Prepare driver to run test(s)", groups = "webTests")
    public void setUpWeb() throws Exception {
        prepareDriver(PropertiesFile.WEB);
        System.out.println("Driver prepared");

    }

    @AfterSuite(description = "Close driver on all tests completion", groups = {"native", "webTests"})
    public void tearDown() throws Exception {
        driver().quit();
        System.out.println("Driver closed");
    }

}
