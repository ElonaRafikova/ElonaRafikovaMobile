package scenarios.hw2.webTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import scenarios.hw2.Hooks;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(groups = "web")
public class SimpleWebTests extends Hooks {

    protected SimpleWebTests() {
        super();
    }

    private static final String expectedMainText = "The global coordination of the DNS Root, " +
            "IP addressing, and other Internet protocol resources is performed as" +
            " the Internet Assigned Numbers Authority (IANA) functions. Learn more.";

    private static List<String> expectedTitles = Arrays.asList("Domain Names", "Number Resources", "Protocol Assignments");

    @Test(description = "Open website, check logo, text under it and 3 panel")
    public void webTest() throws Exception {

        //1 Open site
        driver().get(getSUT());
        driverWait().until(ExpectedConditions.urlToBe(getSUT() + "/"));

        //2 Check that there is logo
        WebElement logo = driver().findElement(By.cssSelector("h1"));
        assertTrue(logo.isDisplayed());

        //3 Check text under logo
        WebElement intro = driver().findElement(By.id("intro"));
        assertEquals(intro.getText(), expectedMainText);

        //4 Check that there are 3 panel
        List<WebElement> panels = driver().findElements(By.className("home-panel"));
        assertEquals(panels.size(), 3);

        //5 Check panels titles
        List<WebElement> titlesPanels = driver().findElements(By.cssSelector(".home-panel>h2"));
        for (int i = 0; i < titlesPanels.size(); i++) {
            assertEquals(titlesPanels.get(i).getText(), expectedTitles.get(i));
        }
    }

}
