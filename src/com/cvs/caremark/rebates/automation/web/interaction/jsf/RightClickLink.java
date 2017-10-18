package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class RightClickLink extends Interaction {

	private String link;

	@Override
	@Test
	public void invokeInteraction() {
		List<WebElement> we = getWebElement();
		Actions action = new Actions(getDriver());
		action.contextClick(we.iterator().next()).build().perform();
	}

	@Test
	@Parameters({ "link" })
	public void setParams(String link) {
		this.link = link;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();
		
		if (type.equals(WebElementFindHelper.BY_LINK_TEXT)) {
			setType(WebElementFindHelper.BY_LINK_TEXT);
		}

		m.put(type, link);

		return m;
	}
}
