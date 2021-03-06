package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SelectRadioButton extends Interaction {

	private String bvalue;
	private String bname;

	@Override
	@Test
	public void invokeInteraction() {
		WebElement we = getWebElement().iterator().next();
		we.click();
	}

	@Test
	@Parameters({ "bname", "bvalue" })
	public void setParams(String bname, String bvalue) {
		this.bname = bname;
		this.bvalue = bvalue;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();

		if (type.equals(WebElementFindHelper.XPATH)) {
			m.put(WebElementFindHelper.XPATH, "//input[@name= '" + bname
					+ "' and @value='" + bvalue + "']");
		} else {
			m.put(type, bname);
		}

		return m;
	}

}
