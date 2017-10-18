package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SelectCheckboxButton extends Interaction {
	private String cname;
	private String cvalue;
	public static String CHECKED = "checked", UNCHECKED = "unchecked";

	@Override
	@Test
	public void invokeInteraction() {
		WebElement we = getWebElement().iterator().next();

		if (cvalue.equalsIgnoreCase(CHECKED)) {
			if (!we.isSelected()) {
				we.click();
			}
		} else {
			if (we.isSelected())
				we.click();
		}
	}

	@Test
	@Parameters({ "cname", "cvalue" })
	public void setParams(String cname, String cvalue) {
		this.cname = cname;
		this.cvalue = cvalue;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();
		if ("MY_FACES_BY_LABEL".equals(type)) {
			m.put(WebElementFindHelper.XPATH, "//label[contains(text(), '"
					+ cname + "')]/../../child::td/input");
		} else {
			m.put(type, cname);
		}
		return m;
	}
}
