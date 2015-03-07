package CS585.Webhook;

import java.io.File;
import java.io.IOException;



/**
 * @author Roshan Rathod
 * 
 * Opens command prompt and runs maven commands (assuming maven is already installed on the machine) 
 * 
 * Builds the project
 * 
 * Runs Pitest tool - mutation coverage
 *
 */

public class MutationTests {
	
	public static void run(File Directory, String email) throws IOException, InterruptedException{
		
		String[] mvnPackage = {"CMD","/C","start","mvn","package"};
		String[] mvnMutation = {"CMD","/C","start", "mvn","org.pitest:pitest-maven:mutationCoverage"};
		
		//Directory is where the repository is cloned locally. Command prompt starts with the directory path
		
		ProcessBuilder runTests = new ProcessBuilder(mvnMutation);
		runTests.directory(Directory);
		
		ProcessBuilder build = new ProcessBuilder(mvnPackage);
		build.directory(Directory);
		
		//run mvn package to build package and generate class files
		Process processBuild = build.start();
		
		//Wait for build to complete
		Thread.sleep(10000);
		
		//Run mutation coverage command once project build is complete
		Process process = runTests.start();
		
		//Wait for process to complete
		Thread.sleep(10000);
		
		//Destroy the process to proceed further and send email
		process.destroy();
		
		//Proceed to sending email to the committer
		String testsFolder = Directory.toString();
		SendEmail.To(email, testsFolder);
		 
		

		try {
			
			//wait for process to exit
			int exitValue = process.waitFor();
			
			System.out.println("\n\nExit Value is " + exitValue);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
