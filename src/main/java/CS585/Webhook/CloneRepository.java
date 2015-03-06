package CS585.Webhook;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;



/**
 * Simple snippet which shows how to clone a repository from a remote source
 *
 * @author Roshan Rathod
 */
public class CloneRepository {

    

    public CloneRepository(String URL) throws IOException, InvalidRemoteException, TransportException, GitAPIException {
        // prepare a new folder for the cloned repository
        File localPath = new File("C:\\GitHub Repo Clone\\");
        localPath.delete();

        // then clone
        System.out.println("Cloning from " + URL + " to " + localPath);
        Git result = Git.cloneRepository()
                .setURI(URL)
                .setDirectory(localPath)
                .call();

        try {
	        // Note: the call() returns an opened repository already which needs to be closed to avoid file handle leaks!
	        System.out.println("Having repository: " + result.getRepository().getDirectory());
        } finally {
        	result.close();
        }
    }
}