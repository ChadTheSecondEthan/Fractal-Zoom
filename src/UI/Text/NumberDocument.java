package UI.Text;

public class NumberDocument extends Document {
	
	private int limit;
	private boolean allowDecimals;
	private boolean allowNegatives;

	public NumberDocument(TextField tf) {
		this(tf, true, true);
	}

	public NumberDocument(TextField tf, boolean allowDecimals, boolean allowNegatives) {
		super(tf);
		limit = Integer.MAX_VALUE;
		this.allowDecimals = allowDecimals;
		this.allowNegatives = allowNegatives;
	}
	
	public void setLimit(int limit) { this.limit = limit; }

	@Override
	public void insertChar(char c) {
		if ((Character.isDigit(c) || (allowDecimals && c == '.') || (allowNegatives && c == '-')) && textField.getText().length() < limit) 
			super.insertChar(c);
	}

}
