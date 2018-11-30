package scenarios.hw2.nativeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import scenarios.hw2.Hooks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Test(groups = "native")
public class SimpleNativeTests extends Hooks {

    private static final String app_package_name = "com.example.android.contactmanager:id/";

    protected SimpleNativeTests() {
        super();
    }

    @Test(description = "Check titles and fields in Add Contact Page")
    public void simplestTest() throws Exception {

        //1 Click Add Contact button
        WebElement addContactButton = driver().findElement(By.id(app_package_name + "addContactButton"));
        assertEquals(addContactButton.getText(), "Add Contact");
        addContactButton.click();

        //2 Find 4 titles
        List<WebElement> actualTitles = new ArrayList<>();
        actualTitles.add(driver().findElement(By.id("Target Account")));
        actualTitles.add(driver().findElement(By.id("Contact Name")));
        actualTitles.add(driver().findElement(By.id("Contact Phone")));
        actualTitles.add(driver().findElement(By.id("Contact Email")));

        //3 Check all titles is displayed
        actualTitles.forEach(WebElement::isDisplayed);

        //4 Check that titles has expected text
        List<String> expectedTitles = Arrays.asList("Target Account", "Contact Name",
                "Contact Phone", "Contact Email");
        assertEquals(expectedTitles.size(), actualTitles.size());
        for (int i = 0; i < expectedTitles.size(); i++) {
            assertEquals(actualTitles.get(i).getText(), expectedTitles.get(i));
        }
        //5 Check 4 fields is displayed
        List<WebElement> fields = new ArrayList<>();
        fields.add(driver().findElement(By.id(app_package_name + "accountSpinner")));
        fields.add(driver().findElement(By.id(app_package_name + "contactNameEditText")));
        fields.add(driver().findElement(By.id(app_package_name + "contactPhoneEditText")));
        fields.add(driver().findElement(By.id(app_package_name + "contactEmailEditText")));
        fields.forEach(WebElement::isDisplayed);

    }
}
