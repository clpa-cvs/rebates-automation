package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ValidateButtonDisabled extends Interaction {
	private String buttonText;

	@Override
	@Test
	public void invokeInteraction() {
		WebElement button = getWebElement(duration, pollDuration, timeUnit);
		assertNotNull(button.getAttribute("disabled"));
	}

	@Test
	@Parameters({ "buttonText" })
	public void setParams(String buttonText) {
		this.buttonText = buttonText;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();
		
		if (type.equals(WebElementFindHelper.XPATH)) {
		m.put(WebElementFindHelper.XPATH, "//input[@name='action' and @value='"
				+ buttonText + "']");
		} else {
			m.put(type, buttonText);
		}
		return m;
	}
}
