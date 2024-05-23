package tests;

import TestComponent.BaseTest;
import TestComponent.Retry;
import deus.pageobjects.CartPage;
import deus.pageobjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {
    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {
        landingPage.loginApplication("anshika@gmail.com", "Iamking");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);

//        String productName = "ZARA COAT 3";
//        ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
//        List<WebElement> products = productCatalogue.getProductList();
//        productCatalogue.addProductToCart(productName);
//        CartPage cartPage = productCatalogue.goToCartPage();
//        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
//        Assert.assertFalse(match);

    }
}
