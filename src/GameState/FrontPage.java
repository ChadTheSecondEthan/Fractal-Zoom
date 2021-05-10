package GameState;

import UI.Anchor;
import UI.Button;
import UI.Text.Text;

public class FrontPage extends GameState {

	public FrontPage(GameStateManager gsm) { 
		super(gsm); 
	}
	
	@Override
	public void init() {
		
		Button playButton = new Button("Play");
		playButton.setAnchor(Anchor.CENTER);
		playButton.setBounds(-100, -100, 200, 100);
		playButton.setOnClickListener(() -> gsm.setState(GameStateManager.SELECT_PAGE));
		add(playButton);
		
		Button exitButton = new Button("Exit");
		exitButton.setAnchor(Anchor.CENTER);
		exitButton.setBounds(-100, 100, 200, 100);
		exitButton.setOnClickListener(() -> System.exit(0));
		add(exitButton);
		
		Text text = new Text("Fractal Zoomer");
		text.setAnchor(Anchor.TOP_CENTER);
		text.setY(100);
		add(text);
	}

}
