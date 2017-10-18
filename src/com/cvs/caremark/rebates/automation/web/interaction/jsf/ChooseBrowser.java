package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.Multimap;

public class ChooseBrowser extends Interaction {
	private static String _browser, _driverLocation;

	
	@Test
	public void invokeInteraction() {
		try {
			setDriverLocation(_driverLocation);
			setBrowser(_browser);
			initDriver();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Parameters({ "browser", "driverlocation" })
	public void setParams(String browser, String driverLocation) {
		_browser = browser;
		_driverLocation = driverLocation;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		return null;
	}

}
