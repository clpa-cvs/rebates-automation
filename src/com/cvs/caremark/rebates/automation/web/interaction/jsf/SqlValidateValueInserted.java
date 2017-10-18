package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.Multimap;

public class SqlValidateValueInserted extends SqlInteraction {

	private Long count;

	@Override
	@Test
	public void invokeInteraction() {
		try {
			ResultSet s = executeUserQuery();
			Thread.sleep(5000);

			if (!s.next())
				fail("Result set empty");

			assertEquals((long) s.getLong(0), count + 1);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Parameters({ "count" })
	public void setParams(Long count) {
		this.count = count;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		return null;
	}

}
