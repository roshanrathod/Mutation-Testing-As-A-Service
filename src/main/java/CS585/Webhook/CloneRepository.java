package CS585.Webhook;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;



/**
 * @author Roshan Rathod
 * 
 * 
 * Clones a Github Repository 
 * Uses JGIT library
 * 
 */
public class CloneRepository {

    public static File Repository_directory ; 

    public static void run(String URL, String email) throws IOException, InvalidRemoteException, TransportException, GitAPIException, InterruptedException {
        
    	// prepare a new folder for cloning the repository
        File localPath = File.createTempFile("GitRepository", "");
        localPath.delete();

        // Clone the repository in the temporary directory
        System.out.println("Cloning from " + URL + " to " + localPath);
        Git result = Git.cloneRepository()
                .setURI(URL)
                .setDirectory(localPath)
                .call();

        try {
	      
        	
        	//Get the repository directory name (parent directory)
        	Repository_directory = result.getRepository().getDirectory().getParentFile();
        	
        	//Run Mutation Tests using PITEST maven commands on the directory
        	MutationTests.run(Repository_directory,email);
        	
	        System.out.println("Repository at: " + Repository_directory);
        } finally {
        	//Close the opened github repository to avoid file handle leaks
        	result.close();
        }
    }
}