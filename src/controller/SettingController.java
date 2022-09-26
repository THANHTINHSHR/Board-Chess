package controller;

import model.SettingModel;

public class SettingController {
	private SettingModel setting;

	public SettingController(SettingModel setting) {
		super();
		this.setting = setting;
	}

	public void saveSetting(String p1N, String p2N, int p1C, int p2C, boolean isP1First) {
		setting.setP1Name(p1N);
		setting.setP2Name(p2N);
		setting.setP1Color(p1C);
		setting.setP2Color(p2C);
		setting.setP1GoFirst(isP1First);
		
		setting.updateObservers();
	}

	public void setDefaultSetting() {
		setting.setDefault();
		setting.updateObservers();
	}

	public SettingModel getSetting() {
		return setting;
	}
}
