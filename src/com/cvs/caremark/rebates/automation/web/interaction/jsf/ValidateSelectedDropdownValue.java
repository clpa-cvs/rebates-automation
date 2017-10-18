package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ValidateSelectedDropdownValue extends Interaction {

	private String selected;
	private String label;

	@Override
	@Test
	public void invokeInteraction() {
		Select s = new Select(getWebElement().get(0));

		assertEquals(s.getFirstSelectedOption().getText(), selected);

	}

	@Test
	@Parameters({ "label", "selected" })
	public void setParams(String label, String selected) {
		this.label = label;
		this.selected = selected;
	}

	@Override
	public Multimap<String, String> getParamMap() {

		m = ArrayListMultimap.create();

		if (type.equals(WebElementFindHelper.XPATH)) {
			m.put(WebElementFindHelper.XPATH, "//label[contains(text(), '"
					+ label + "')]/../../descendant::select");
		} else {
			m.put(type, label);
		}

		return m;
	}
}
