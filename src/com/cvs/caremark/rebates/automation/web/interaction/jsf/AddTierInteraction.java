package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cvs.caremark.rebates.automation.ui_components.ClientRegTier;
import com.cvs.caremark.rebates.automation.web.interaction.strategies.TierFile;
import com.cvs.caremark.rebates.automation.web.interaction.strategies.TierFileFactory;
import com.cvs.caremark.rebates.automation.web.util.FileUtil;
import com.cvs.caremark.rebates.automation.web.util.WebElementFindHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class AddTierInteraction extends Interaction {

	private File file;
	private static String[] ids = new String[] { "searchForm:poolTyp",
			"searchForm:addTier" };
	private static int ADD_TIER_INDEX = 1;

	@Override
	@Test
	public void invokeInteraction() {
		WebElementFindHelper.explicitWaitFor(3000);

		String poolType = new Select(getWebElement().iterator().next())
				.getFirstSelectedOption().getText();

		TierFile tf = TierFileFactory.create(file);
		Map<String, List<ClientRegTier>> map = tf.iterator();

		List<ClientRegTier> l = map.get(poolType);

		/*
		 * Add tier and set each column
		 */
		for (int j = 0; j < l.size(); j++) {
			ClientRegTier tier = l.get(j);
			tier.setTier(getDriver(), j + 1);

			if (j + 1 < l.size()) {
				WebElementFindHelper.getWebElementById(getDriver(),
						ids[ADD_TIER_INDEX], 5000, 1).click();
				WebElementFindHelper.explicitWaitFor(3000);
			}
		}

	}

	@Test
	@Parameters({ "tiersFilePath" })
	public void setParams(String tiersFilePath) {
		File f = FileUtil.validateFile(tiersFilePath);

		if (f == null)
			return;

		this.file = f;
	}

	@Override
	public Multimap<String, String> getParamMap() {
		m = ArrayListMultimap.create();
		m.putAll(type, Arrays.asList(ids));
		return m;
	}

}
