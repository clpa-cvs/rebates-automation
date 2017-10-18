package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SetTextAreaValue extends Interaction {

	private String textAreaLabel;
	private String value;

	@Override
	@Test
	public void invokeInteraction() {
		WebElement area = getWebElement().iterator().next();
		area.sendKeys(value);
	}

	@Test
	@Parameters({ "textAreaLabel", "value" })
	public void setParams(String textAreaLabel, String value) {
		this.textAreaLabel = textAreaLabel;
		this.value = value;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();

		if (type.equals(WebElementFindHelper.XPATH)) {
			m.put(WebElementFindHelper.XPATH, "//label[contains(text(), '"
					+ textAreaLabel
					+ "')]/../following-sibling::td/descendant::textarea");
		} else {
			m.put(type, textAreaLabel);
		}

		return m;
	}

}
