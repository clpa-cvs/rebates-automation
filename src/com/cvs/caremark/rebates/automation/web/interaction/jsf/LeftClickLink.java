package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class LeftClickLink extends Interaction {

	private String spanId;
	private String tableDivId;
	private Integer elementNumber;

	@Override
	@Test
	public void invokeInteraction() {
		List<WebElement> we = getWebElement();
		WebElement el = we.iterator().next();

		el.click();
	}

	@Test
	@Parameters({ "spanId", "tableDivId", "elementNumber"})
	public void setParams(String spanId, @Optional String tableDivId, @Optional Integer elementNumber) {
		this.spanId = spanId;
		this.tableDivId = tableDivId;
		this.elementNumber = elementNumber;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();

		if (type.equals(WebElementFindHelper.BY_SPAN_LINK_ID)) {
			m.put(WebElementFindHelper.XPATH, "//span[@id='" + spanId + "']/..");
			setType(WebElementFindHelper.XPATH);
		} else if (type.equals(WebElementFindHelper.BY_TD_TEXT)) {
			m.put(WebElementFindHelper.XPATH, "//div[@id='" + tableDivId +  "'" + "]/table/tbody/tr/td[contains(text(), '"
					+ spanId + "')]");
			setType(WebElementFindHelper.XPATH);
		} else if (type.equals(WebElementFindHelper.IMG)) {
			String expr = "//span[@id='" + spanId + "']/table[" + elementNumber + "]/tbody/tr/td[2]/img";
			setType(WebElementFindHelper.XPATH);
			m.put(WebElementFindHelper.XPATH, expr);
		} else if (type.equals(WebElementFindHelper.LINK_TEXT)) {
			setType(WebElementFindHelper.LINK_TEXT);
			m.put(WebElementFindHelper.LINK_TEXT, spanId);
		}  else {
			m.put(type, spanId);
		}

		return m;
	}
}
