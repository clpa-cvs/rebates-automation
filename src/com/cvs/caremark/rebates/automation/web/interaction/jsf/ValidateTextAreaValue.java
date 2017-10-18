package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ValidateTextAreaValue extends Interaction {

	private String label;
	private String text;

	@Override
	@Test
	public void invokeInteraction() {
		WebElement we = getWebElement().get(0);
		assertEquals(text, we.getText());
	}

	@Test
	@Parameters({ "label", "text" })
	public void setParams(String label, String text) {
		this.label = label;
		this.text = text;
	}

	@Override
	public Multimap<String, String> getParamMap() {

		m = ArrayListMultimap.create();

		if (type.equals(WebElementFindHelper.XPATH)) {
			m.put(WebElementFindHelper.XPATH, "//label[contains(text(), '"
					+ label
					+ "')]/../following-sibling::td/descendant::textarea");

		} else {
			m.put(type, label);
		}

		return m;
	}

}
