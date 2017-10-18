package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public abstract class Interaction extends TestAction {

	protected Long duration;
	protected Long pollDuration;
	protected Integer numTries;
	protected String timeUnit;
	private Long periodWaitTimeInMilliseconds;
	private Integer maxNumberOfTries;
	protected String type;
	protected Multimap<String, String> m;
	protected Queue<String> q;
	
	@Test
	@Parameters({"waitDuration", "pollDuration", "waitTimeUnit", "periodWaitTimeInMilliseconds", "maxNumberOfTries", "type", "logout"})
	public void setOptionalParams( @Optional Long waitDuration, @Optional Long pollDuration, @Optional String waitTimeUnit, 
			@Optional Long periodWaitTimeInMilliseconds, @Optional Integer maxNumberOfTries, @Optional String type, @Optional String logout) {
		setUtilParams(periodWaitTimeInMilliseconds, maxNumberOfTries);
		setFluentWaitParams(waitDuration, pollDuration, waitTimeUnit);
		
		if (logout != null) {
			new LogoutClientReg().invokeInteraction();
		}
		
		if (type == null)
			setType(WebElementFindHelper.DEFAULT_TYPE);
		else {
			setType(type);
		}
	}
	
	@Test
	public  abstract void invokeInteraction();

	protected void setDuration(Long duration) {
		this.duration = duration;
	}

	public void setNumTries(Integer numTries) {
		this.numTries = numTries;
	}
	
	protected void setFluentWaitParams(Long waitDuration, Long pollDuration, String waitTimeUnit) {
		this.duration = waitDuration;
		this.pollDuration = pollDuration;
		this.timeUnit = waitTimeUnit;
	}

	public Long getDuration() {
		return duration;
	}
	
	public Integer getNumTries() {
		return numTries;
	}
	
	protected void setUtilParams(Long periodWaitTimeInMilliseconds,
			Integer maxNumberOfTries) {
		this.periodWaitTimeInMilliseconds = periodWaitTimeInMilliseconds;
		this.maxNumberOfTries = maxNumberOfTries;
	}
	
	public Long getPeriodWaitTimeInMilliseconds() {
		return periodWaitTimeInMilliseconds;
	}
	
	public Integer getMaxNumberOfTries() {
		return maxNumberOfTries;
	}
	
	@Override
	public synchronized WebElement getInteractionWebElement(WebDriver driver) {
		return WebElementFindHelper.getWebElementByType(driver, type, getParamMap(), periodWaitTimeInMilliseconds, maxNumberOfTries);
	}
	
	protected synchronized List<WebElement> getWebElement() {
		initQueue();
		List<WebElement> wes = new ArrayList<WebElement>();
		
		while (!q.isEmpty()) {
			WebElement we = getWebElement(duration, pollDuration, timeUnit);
			wes.add(we);
		}
		
		return wes;
	}
	

	public abstract Multimap<String, String> getParamMap();
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public synchronized void initQueue() {
		q = new LinkedList<String>(getParamMap().get(type));
	}

	@Override
	public synchronized WebElement nextInQueue(WebDriver driver) {
		Multimap<String, String> mm = ArrayListMultimap.create();
		mm.put(type, q.poll());
		return WebElementFindHelper.getWebElementByType(driver, type, mm, periodWaitTimeInMilliseconds, maxNumberOfTries);
	}
}
