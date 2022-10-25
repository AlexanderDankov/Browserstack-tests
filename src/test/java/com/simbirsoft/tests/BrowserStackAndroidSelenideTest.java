package com.simbirsoft.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

@Tag("selenide_android")
public class BrowserStackAndroidSelenideTest extends TestBase {

    @Test
    @DisplayName("Search results test")
    void searchBrowserstackTest() {
        step("Type search", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("BrowserStack");

        });

        step("Verify content found", () -> {
            $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_container"))
                    .shouldHave(CollectionCondition.sizeGreaterThan(0));
        });
    }

    @Test
    @DisplayName("Authorization error message test")
    void checkAuthorizationValidationMessage() {
        step("Open authorization form", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/menu_overflow_button")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/explore_overflow_account_name")).click();
        });

        step("Submit incorrect credentials", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/login_username_text"))
                    .setValue("incorrectUserName");
            $(MobileBy.id("org.wikipedia.alpha:id/login_password_input")).click();
            $(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/TextInputLayout[2]/android.widget.FrameLayout/android.widget.EditText"))
                    .setValue("incorrectUserPassword");
            $(MobileBy.id("org.wikipedia.alpha:id/login_button"))
                    .shouldBe(Condition.enabled);
            $(MobileBy.id("org.wikipedia.alpha:id/login_button")).click();

        });

        step("Check authorization error message", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/snackbar_text"))
                    .shouldBe(Condition.visible);
            $(MobileBy.id("org.wikipedia.alpha:id/snackbar_text"))
                    .shouldHave(Condition.text("Incorrect username or password entered. Please try again."));
        });
    }
}
