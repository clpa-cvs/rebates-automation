package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import org.testng.annotations.Test;

import com.google.common.collect.Multimap;

public class Teardown extends Interaction {

	@Override
	@Test
	public void invokeInteraction() {
		getDriver().quit();
	}

	@Override
	public Multimap<String, String> getParamMap() {
		return null;
	}

}
