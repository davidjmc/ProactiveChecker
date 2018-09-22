package proactivechecker.log;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AppLog {
	
	public static void main(String[] args) {
	
		AppLog.run();
		
	}

	private static void run() {
		System.out.println("Observer initialized!");
		
		Timer timer = null;
		if(timer == null) {
			timer = new Timer();
			TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					Random random = new Random();
					double r = random.nextDouble();
					System.out.println("Result: " + r);
					
				}
			};
			timer.schedule(task, 0, 2*1000);
		}		
	}

}
