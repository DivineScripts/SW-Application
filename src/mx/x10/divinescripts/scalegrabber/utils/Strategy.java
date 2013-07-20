package mx.x10.divinescripts.scalegrabber.utils;

public interface Strategy {

	public boolean isValid();
	
	public void execute();
	
	public String getState();
}
