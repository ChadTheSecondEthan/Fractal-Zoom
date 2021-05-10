package UI.Text;

public abstract class Document {
	
	public interface DocumentListener {
		void onTextChanged();
	}
	
	protected TextField textField;
	protected DocumentListener documentListener;
	
	public Document(TextField tf) {
		textField = tf;
		documentListener = null;
	}
	
	public void insertChar(char c) {
		textField.append(c);
		if (documentListener != null)
			documentListener.onTextChanged();
	}
	
	public void charRemoved() {
		if (documentListener != null)
			documentListener.onTextChanged();
	}
	
	public void setDocumentListener(DocumentListener dl) {
		documentListener = dl;
	}
}
