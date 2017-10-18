package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class LeftClickButton extends Interaction {

	private String clickElement;

	@Override
	@Test
	public void invokeInteraction() {
		List<WebElement> button = getWebElement();
		button.iterator().next().click();
	}

	@Test
	@Parameters({ "clickElement" })
	public void setParams(String clickElement) {
		this.clickElement = clickElement;

	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();

		if (type.equals(WebElementFindHelper.XPATH)) {
			m.put(WebElementFindHelper.XPATH, "//input[contains(@value, '"
					+ clickElement + "')]");
		} else {
			m.put(type, clickElement);
		}
		return m;
	}

}