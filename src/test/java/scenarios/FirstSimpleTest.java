package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstSimpleTest extends DriverSetup {

    @BeforeClass
    /**
     * Prepare driver to run test(s)
     */
    public void setUp() throws Exception {
        //prepareAndroidNative();
        prepareAndroidWeb();
    }

    /**
     * This simple test just click on button 'Add contact'
     */
    //@Test
    public void SimplestTest() {
       /* String app_package_name = "com.example.android.contactmanager:id/";
        By add_btn = By.id(app_package_name + "addContactButton");*/

        driver.findElement(By.className("android.widget.Button")).click();
        //driver.findElementByXPath("//android.widget.Button[@content-desc='Add Contact']").click();
        //driver.findElement(add_btn).click();

        System.out.println("Simplest Appium test done");
    }

    @Test(description = "Open website")
    public void webTest() throws InterruptedException {
        driver.get("http://iana.org");
        Thread.sleep(5000);
        System.out.println("Site opening done");
    }

    @AfterClass
    /**
     * Close driver on all tests completion
     */
    public void tearDown() throws Exception {
        driver.quit();
    }
}
