package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.ui_components.ClientRegRuleMatrix;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ClickRuleMatrix extends Interaction {

	@Override
	@Test
	public void invokeInteraction() {
//		ClientRegRuleMatrix m = new ClientRegRuleMatrix(getDriver());
	}
	
	@Parameters({"pairs"})
	@Test
	public void setParams(String pairs) {
		String[] p = pairs.split("\\|");
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();
		return m;
	}

	
	
	
}
