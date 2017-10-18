package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SelectRebatesTableMenuItem extends MenuItemInteraction {

	private String tableName;
	private Integer row;

	@Override
	@Test
	public void invokeInteraction() {
		
	}
	
	@Test
	@Parameters({"tableName", "row", "menuItems"})
	public void setParams(String tableName, Integer row, String menuItems) {
		this.tableName = tableName;
		this.row = row;
		populateMenus(menuItems);
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();

		if (type.equals(WebElementFindHelper.XPATH)) {
			Iterator<String> qi = menus.iterator();
			List<String> l = new ArrayList<>();

			while (qi.hasNext()) {
				String table = "//span[contains(text(), '" + tableName + "']";
				String hoverElement = "td[contains(text(), '" + qi.next() + "')]";
				
				l.add(table + "/../following-sibling" + hoverElement);
			}

			m.putAll(WebElementFindHelper.XPATH, l);
		} else {
			m.putAll(type, menus);
		}

		return m;
	}

}
