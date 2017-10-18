package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class RightClickAndHover extends Interaction {

	private String divId;
	private String tdText;
	private String rightClick;

	@Override
	@Test
	public void invokeInteraction() {

	}

	@Parameters({ "rightClick", "divId", "tdText" })
	public void setParams(String rightClick, String divId, String tdText) {
		this.divId = divId;
		this.tdText = tdText;
		this.rightClick = rightClick;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();

		List<String> l = new ArrayList<String>();
		
		l.add("//a[contains(text(), '" + rightClick + "']");
		l.add("/");
		
		return m;
	}
}
