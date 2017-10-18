package com.cvs.caremark.rebates.automation.web.interaction.jsf;


import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.cvs.caremark.rebates.automation.main.BrowserUnsupportedException;
import com.cvs.caremark.rebates.automation.main.DriverFactory;
import com.cvs.caremark.rebates.automation.main.TimeUnitEnum;
import com.google.common.base.Function;

public abstract class TestAction {

	private static WebDriver driver = null;
	private String _browser, _driverLocation;

	public static WebDriver getDriver() {
		return driver;
	}

	public void setBrowser(String _browser) {
		this._browser = _browser;
	}

	public void setDriverLocation(String _driverLocation) {
		this._driverLocation = _driverLocation;
	}

	public void initDriver() {
		if (driver == null) {
			try {
				driver = DriverFactory.create(_browser, _driverLocation);
			} catch (BrowserUnsupportedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized WebElement getWebElement(Long duration, Long pollDuration, String timeUnit) {
		WebElement lwe = null;
		
		if (pollDuration != null && duration != null && timeUnit != null) {
			
			
			try {
				FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)

						.withTimeout(duration,
								TimeUnitEnum.getTimeunitbyName(timeUnit))

						.pollingEvery(pollDuration,
								TimeUnitEnum.getTimeunitbyName(timeUnit))
						.ignoring(NoSuchElementException.class)
						.ignoring(StaleElementReferenceException.class)
						.ignoring(WebDriverException.class)
						.ignoring(InvalidSelectorException.class);
				
				Thread.sleep(TimeUnitEnum.getTimeunitbyName(timeUnit).toMillis(pollDuration));
				lwe = fluentWait.until(new Function<WebDriver, WebElement>() {
					
					 public WebElement apply(WebDriver driver) {
						 return nextInQueue(driver);
					 }
		         });
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} else {
			lwe = nextInQueue(driver);
		}
		
		return lwe;
		
	}
	
	public abstract WebElement getInteractionWebElement(WebDriver driver);
	public abstract void initQueue();
	public abstract WebElement nextInQueue(WebDriver driver);
}
