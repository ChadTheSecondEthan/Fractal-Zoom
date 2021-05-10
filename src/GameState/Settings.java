package GameState;

import Generation.ColorMode;
import Generation.Colorer;
import UI.Button;
import UI.Buttons;
import UI.UI;

public class Settings extends GameState {
	
	private Colorer colorer;
	private Button updateButton;

	public Settings(GameStateManager scm) {
		super(scm);
	}

	@Override
	public void init() {
		
		colorer = Colorer.getInstance();
		colorer.getColorMode().display();
		
		updateButton = new Button(colorer.getColorMode().toString());
		UI.centerComponentBounds(updateButton, 100, 200, 75);
		updateButton.setOnClickListener(() -> {
			ColorMode mode = colorer.getColorMode();
			mode.removeDisplay();
			colorer.setColorIndex((colorer.getColorIndex() + 1) % ColorMode.NUM_MODES);
			mode = colorer.getColorMode();
			mode.display();
			updateButton.setText(mode.toString());
		});
		add(updateButton);
		
		add(Buttons.getBackButton(gsm::back));
	}

}
