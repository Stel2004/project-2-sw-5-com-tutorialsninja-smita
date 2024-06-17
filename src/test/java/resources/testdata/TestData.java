package resources.testdata;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "desktopData")
    public Object[][] getBuildComData() {
        Object[][] data = new Object[][]{
                {"HP LP3065", "1", "Success: You have added HP LP3065 to your shopping cart!", "HP LP3065", "Product 21", "£74.73"},
                {"Sony VAIO", "1", "Success: You have added Sony VAIO to your shopping cart!", "Sony VAIO", "Product 19", "£736.23"},
        };
        return data;
    }
}
