package com.simbirsoft.drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.simbirsoft.config.CredentialConfig;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;

public class BrowserStackMobileDriver implements WebDriverProvider {

    public static CredentialConfig credentials = ConfigFactory.create(CredentialConfig.class);

//    public static URL getBrowserStackUrl() {
//        try {
//            return new URL(format("http://%s/wd/hub", credentials.remote_url()));
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static URL getBrowserStackUrl() {
        try {
            return new URL("http://hub-cloud.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
        // Set your access credentials
        desiredCapabilities.setCapability("browserstack.user", credentials.remote_user());
        desiredCapabilities.setCapability("browserstack.key", credentials.remote_key());

        // Set URL of the application under test
        desiredCapabilities.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");

        // Specify device and os_version for testing
        desiredCapabilities.setCapability("device", "Google Pixel 3");
        desiredCapabilities.setCapability("os_version", "9.0");

        // Set other BrowserStack capabilities
        desiredCapabilities.setCapability("project", "Wiki tests Project");
        desiredCapabilities.setCapability("build", "Java Android");
        desiredCapabilities.setCapability("name", "Wiki Test");

        return new AndroidDriver(getBrowserStackUrl(), desiredCapabilities);
    }
}
