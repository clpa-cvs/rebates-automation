package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ValidateTextVisible extends Interaction {

	private String text;

	@Override
	@Test
	public void invokeInteraction() {
		List<WebElement> we = getWebElement();
		assertNotNull(we.iterator().next());
	}

	@Test
	@Parameters({ "text" })
	public void setParams(String text) {
		this.text = text;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();
		setType(WebElementFindHelper.XPATH);
		m.put(type, "//*[contains(text(), '" + text + "')]");

		return m;
	}

}
