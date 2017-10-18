package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SetLoginInputValue extends Interaction {

	private String fieldname;
	private String fieldvalue;

	@Override
	@Test
	public void invokeInteraction() {
		List<WebElement> list = getWebElement();
		WebElement fieldnameE = list.get(0);
		fieldnameE.clear();
		fieldnameE.sendKeys(fieldvalue);
	}

	@Test
	@Parameters({ "fieldname", "fieldvalue" })
	public void setParams(String fieldname, String fieldvalue) {
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();

		if (type.equals(WebElementFindHelper.XPATH)) {
			m.put(type, "//b[contains(text(), '" + fieldname
					+ "')]/../../following-sibling::td/input");
		} else if (type.equals(WebElementFindHelper.MY_FACES_BY_LABEL)) {
			m.put(type, "//label[contains(text(), '" + fieldname
					+ "')]/../following-sibling::td/descendant::input");
		} else if (type.equals(WebElementFindHelper.BY_LABEL_FOR)) {
			m.put(type, "//label[@for='" + fieldname + "']/../following-sibling::td/descendant::input");
			setType(WebElementFindHelper.XPATH);
		} else {
			m.put(type, fieldname);
		}

		return m;
	}

}
