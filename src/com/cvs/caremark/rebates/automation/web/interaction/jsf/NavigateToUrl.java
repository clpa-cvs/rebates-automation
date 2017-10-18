package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.Multimap;

public class NavigateToUrl extends Interaction {

	private String url;

	@Override
	@Test
	public void invokeInteraction() {
		
		try {
			getDriver().get(url);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Parameters({ "url"})
	public void setParams(String url) {
		this.url = url;
	}

	@Override
	public WebElement getInteractionWebElement(WebDriver driver) {
		return null;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		return null;
	}
	

}
