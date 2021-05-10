package UI;

public class MouseListener {
	
	protected boolean mouseEntered;
	protected OnClickListener ocl;
	
	public MouseListener() {
		mouseEntered = false;
		ocl = null;
	}
	
	public void onMouseEnter() {}
	public void onMouseExit() {}
	public void onMousePress() {}
	public void onMouseRelease() {}
	
	public void setOnClickListener(OnClickListener ocl) {
		this.ocl = ocl;
	}
	
	public void setMouseEntered(boolean mouseEntered) {
		this.mouseEntered = mouseEntered;
	}
	
	public OnClickListener getOnClickListener() {
		return ocl;
	}
	
	public void onClick() {
		ocl.onClick();
	}
	
	public boolean mouseEntered() { return mouseEntered; }

}
