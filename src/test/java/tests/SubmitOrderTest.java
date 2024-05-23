package tests;

import TestComponent.BaseTest;
import deus.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Indonesia");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistory(){
        ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {

//        return new Object[][] {{"anshika@gmail.com", "Iamking@000")}, {"shetty@gmail.com", "Iamking@000"}};

//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("email", "anshika@gmail.com");
//        map.put("password", "Iamking@000");
//        map.put("product", "ZARA COAT 3");
//
//        HashMap<String, String> map1 = new HashMap<String, String>();
//        map1.put("email", "shetty@gmail.com");
//        map1.put("password", "Iamking@000");
//        map1.put("product", "ADIDAS ORIGINAL");
//
        List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\Igor\\Selenium\\SeleniumFrameworkDesign\\src\\test\\java\\data\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}, {data.get(2)}};
    }


}
