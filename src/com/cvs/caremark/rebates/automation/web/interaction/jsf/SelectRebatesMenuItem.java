package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SelectRebatesMenuItem extends MenuItemInteraction {

	private String mainMenuItem;
	private String tableId;
	private Integer rowNumber;

	@Override
	@Test
	public void invokeInteraction() {

		try {
			Thread.sleep(5000);
			
			List<WebElement> els = getWebElement();
			WebDriver webdriver = getDriver();
			System.err.println(els);

			Actions action = new Actions(webdriver);
			Iterator<WebElement> elsIt = els.iterator();

			while (elsIt.hasNext()) {
				WebElement next = elsIt.next();
				
				action.moveToElement(next);
				Thread.sleep(1500);
			}

			action.click().build().perform();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Parameters({ "menuItems", "tableId", "rowNumber" })
	public void setParams(String menuItems, @Optional String tableId, @Optional Integer rowNumber) {
		populateMenus(menuItems);
		
		this.tableId = tableId;
		this.rowNumber = rowNumber;
	}

	public WebElement findMenuElement(List<WebElement> wes) {
		for (WebElement webElement : wes) {
			if (webElement.getText().toLowerCase()
					.equals(mainMenuItem.toLowerCase())) {
				return webElement;
			}
		}

		return null;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();

		if (type.equals(WebElementFindHelper.XPATH)) {
			Iterator<String> qi = menus.iterator();
			List<String> l = new ArrayList<>();
			l.add("(//span[contains(text(), '" + qi.next() + "')])");

			while (qi.hasNext()) {
				l.add("//td[contains(text(), '" + qi.next() + "')]");
			}

			m.putAll(WebElementFindHelper.XPATH, l);
		} else if (type.equals(BY_TABLE_TYPE_XPATH)) {
			String tableXpath = "//table[@id='" + tableId + "']";
			
			Iterator<String> qi = menus.iterator();
			List<String> l = new ArrayList<>();
			
			while (qi.hasNext()) {
				String body = "tbody/tr[" + rowNumber + "]";
				l.add(tableXpath + "/" + body + "/descendant::td[contains(text(), '" + qi.next() + "')]");
				System.err.println("body -> " + body);
			}
			
			setType(WebElementFindHelper.XPATH);
			m.putAll(WebElementFindHelper.XPATH, l);
			
		} else {
			m.putAll(type, menus);
		}

		return m;
	}

}
