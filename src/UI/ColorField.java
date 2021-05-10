package UI;

import java.awt.Color;
import java.awt.Graphics;

import GameState.GameState;
import Generation.ColorChangedListener;
import UI.Text.NumberDocument;
import UI.Text.TextField;

public class ColorField extends UIElement {
	
	private final String[] texts = { "Red", "Green", "Blue" };
	private final Color[] colors = { Color.red, Color.green, Color.blue };
	
	private ColorChangedListener ccl;
	private TextField[] textFields;
	private Color previewColor;
	
	private int yDist;

	public ColorField(Color startColor, boolean displayPreview) {
		super();
		
		textFields = new TextField[3];
		
		String[] startingStrings = {
				"" + startColor.getRed(), "" + startColor.getGreen(), "" + startColor.getBlue() };
		for (int i = 0; i < 3; i++) {
			
			TextField textField = new TextField(startingStrings[i]);
			NumberDocument document = new NumberDocument(textField, false, false);
			document.setLimit(3);
			document.setDocumentListener(this::updatePreviewColor);
			textField.setDocument(document);
			
			textFields[i] = textField;
		}
		
		previewColor = Color.black;
		
		try {
			previewColor = checkInputs();
		} catch (Exception e) {}
		
		yDist = 75;
	}
	
	@Override
	public void onAdd() {
		GameState.curState.add(textFields[0], textFields[1], textFields[2]);
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		for (int i = 0; i < 3; i++)
			textFields[i].setVisible(visible);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		for (int i = 0; i < textFields.length; i++) 
			textFields[i].setBounds(x, y + yDist * i, 100, 30);
	}
	
	private void updatePreviewColor() {
		try {
			previewColor = checkInputs();
		} catch (Exception e) {}
	}
	
	public void addListener(ColorChangedListener ccl) {
		this.ccl = ccl;
	}
	
	public Color checkInputs() throws Exception {
		
		boolean shouldThrowException = false;
		
		int[] colors = new int[3];
		for (int i = 0; i < 3; i++) {
			colors[i] = Integer.parseInt(textFields[i].getText());
			if (colors[i] > 255 || colors[i] < 0) {
				textFields[i].setColor(Color.red);
				shouldThrowException = true;
			} else
				textFields[i].setColor(Color.black);
		}
		
		if (shouldThrowException) throw new Exception();
		
		Color color = new Color(colors[0], colors[1], colors[2]);
		if (ccl != null)
			ccl.colorChanged(color);
		
		return color;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setFont(Resources.defaultFont);
		
		for (int i = 0; i < texts.length; i++) {
			g.setColor(colors[i]);
			g.drawString(texts[i], getX(), getY() - 5 + yDist * i);
		}
		
		g.setColor(previewColor);
		g.fillRect(getX() + 120, getY() + 75, 100, 100);
	}
}
