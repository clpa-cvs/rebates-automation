package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.Multimap;

public class LogoutClientReg extends Interaction {

	
	@Override
	@Test
	public void invokeInteraction() {
		WebElement we = WebElementFindHelper.getWebElementByXpath(getDriver(), "//span[contains(text(), 'Logout')]/..");
		we.click();
	}

	@Override
	public Multimap<String, String> getParamMap() {
		return null;
	}

}
