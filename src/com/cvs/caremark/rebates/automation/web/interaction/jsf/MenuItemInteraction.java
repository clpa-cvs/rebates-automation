package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Multimap;

public abstract class MenuItemInteraction extends Interaction {

	public static String MENU_DELIMITER = "\\|";
	protected List<String> menus;
	public static String BY_TABLE_TYPE_XPATH = "XPATH_TABLE";

	public void populateMenus(String menuItems) {
		menus = Arrays.asList(menuItems.split(MENU_DELIMITER));
	}

	@Override
	@Test
	public abstract void invokeInteraction();

	@Override
	public abstract Multimap<String, String> getParamMap();

}
