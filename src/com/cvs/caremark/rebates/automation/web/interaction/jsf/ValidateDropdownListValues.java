package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


public class ValidateDropdownListValues extends Interaction {

	private TreeMap<String, String> valMap;

	@Override
	@Test
	public void invokeInteraction() {

		Select s = new Select(getWebElement(duration, pollDuration, timeUnit));

		List<WebElement> options = s.getOptions();
		Iterator<Entry<String, String>> it = valMap.entrySet().iterator();
		int k = 0;

		// assert that all option values in parameter list exist in dropdown
		while (it.hasNext()) {
			Entry<String, String> ce = it.next();

			if (ce.getKey().charAt(0) > 'o')
				break;

			if (ce.getKey().indexOf("opt") >= 0) {
				assertEquals(options.get(k).getText(), ce.getValue());
				++k;
			}
		}
	}

	@BeforeClass
	@Test
	public void setValues(ITestContext context) {
		valMap = new TreeMap<String, String>(context.getCurrentXmlTest()
				.getLocalParameters());
	}

	@Override
	public Multimap<String, String> getParamMap() {
		String target = valMap.get("label");
		m = ArrayListMultimap.create();

		if (type.equals(WebElementFindHelper.XPATH)) {
			m.put(WebElementFindHelper.XPATH, "//label[contains(text(), '"
					+ target + "')]/../../descendant::select");
		} else {
			m.put(type, target);
		}
		return m;
	}

}
