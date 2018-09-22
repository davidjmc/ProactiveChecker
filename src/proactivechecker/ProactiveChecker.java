package proactivechecker;

import java.util.Timer;
import java.util.TimerTask;

import proactivechecker.scout.Observer;

public class ProactiveChecker {
	
	private String modelFileName;
	private String propertiesFileName; 
	
	public static void main(String[] args) {
		
		ProactiveChecker proactiveChecker = new ProactiveChecker();
		
		proactiveChecker.modelFileName = null;
		proactiveChecker.propertiesFileName = null;
		
		proactiveChecker.initialize();
		
		proactiveChecker.execute();
		
	}

	private void execute() {
		// TODO Auto-generated method stub

	}

	private void initialize() {
		// TODO Auto-generated method stub
		
	}

}
