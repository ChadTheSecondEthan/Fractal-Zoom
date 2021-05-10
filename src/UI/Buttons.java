package UI;

public class Buttons {
	
	public static Button getSettingsButton(OnClickListener ocl) {
		try {
			Button settingsButton = new Button("Settings");
			settingsButton.setAnchor(Anchor.TOP_RIGHT);
			settingsButton.setBounds(-110, 10, 100, 50);
			settingsButton.setOnClickListener(ocl);
			return settingsButton; 
		}
		
		catch (Exception e) {
			return null;
		}
	}

	public static Button getBackButton(OnClickListener ocl) {
		Button backButton = new Button("Back");
		backButton.setAnchor(Anchor.TOP_LEFT);
		backButton.setBounds(10, 10, 100, 50);
		backButton.setOnClickListener(ocl);
		return backButton; 
	}
	
	public static Button getInstructionsButton(OnClickListener ocl) {
		Button insButton = new Button("Instructions");
		insButton.setAnchor(Anchor.TOP_CENTER);
		insButton.setBounds(-75, 10, 150, 50);
		insButton.setOnClickListener(ocl);
		return insButton;
	}

}
