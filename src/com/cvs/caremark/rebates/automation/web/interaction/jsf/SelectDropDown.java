package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SelectDropDown extends Interaction {

	private String dropDownSelectElement, dropDownValue;


	@Override
	@Test
	public void invokeInteraction() {
		System.out.println(getWebElement().iterator().next());
		Select we = new Select(getWebElement().iterator().next());
		we.selectByVisibleText("" + dropDownValue + "");
	}

	@Test
	@Parameters({ "dropDownSelectElement", "dropDownValue"})
	public void setParams(String dropDownSelectElement, String dropDownValue ) {
		this.dropDownSelectElement = dropDownSelectElement;
		this.dropDownValue = dropDownValue;
	}


	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();
		if (type.equals(WebElementFindHelper.MY_FACES_BY_LABEL)) {
			m.put(WebElementFindHelper.MY_FACES_BY_LABEL, "//label[contains(text(), '" + dropDownSelectElement +
					"')]/../following-sibling::td/select");
		} else if (type.equals(WebElementFindHelper.XPATH)) {
			m.put(WebElementFindHelper.XPATH, "//b[contains(text(), '" + dropDownSelectElement +
					"')]/../../../following-sibling::tr/td/select");
		} else if (type.equals(WebElementFindHelper.MY_FACES_BY_ID)) {
			m.put(WebElementFindHelper.MY_FACES_BY_ID, dropDownValue);
		} else {
			m.put(type, dropDownSelectElement);
		}
		
		return m;
	}

}