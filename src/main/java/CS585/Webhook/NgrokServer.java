package CS585.Webhook;

import java.io.File;
import java.io.IOException;

/**
 * @author Roshan Rathod
 * 
 * Opens up command prompt and runs command ngrok 8080 to stat ngrok server and expose localhost:8080 to Internet
 * Gives a URL
 * Add the 'outputURL.com/payload' in your github repository settings --> webhook
 *
 */

public class NgrokServer {


	public static void run() throws IOException{

		String[] command = {"CMD","/C","start", "ngrok","8080"};
		ProcessBuilder probuilder = new ProcessBuilder(command);
		
		/** Download ngrok and unzip the folder into C: drive, so its in location c:\\ngrok
		*   OR
		*   Set the path below to where you have unzipped ngrok on your machine, it should contain ngrok.exe
		*/
		probuilder.directory(new File("c:\\ngrok"));
		
		//run the command
		Process process = probuilder.start();

		try {
			//wait for it to exit
			int exitValue = process.waitFor();
			System.out.println("\n\nExit Value is " + exitValue);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}

