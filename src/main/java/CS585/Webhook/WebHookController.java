package CS585.Webhook;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import CS585.Webhook.GithubWebhookResponse.Committer;
import CS585.Webhook.GithubWebhookResponse.Head_commit;
import CS585.Webhook.GithubWebhookResponse.Payload;
import CS585.Webhook.GithubWebhookResponse.Repository;
import CS585.Webhook.GithubWebhookResponse.WebHookRequest;


/**
 * @author Roshan Rathod
 * 
 * Webhook controller monitors http://localhost:8080/payload for incoming HTTP POST sent by github
 *
 */


@Controller
@RequestMapping("/payload")
public class WebHookController {

	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody 
	public  WebHookRequest getPayload(@RequestBody Payload payload) throws InvalidRemoteException, TransportException, IOException, GitAPIException, InterruptedException {

		//Get the repositry URL to clone the repository
		Repository repo = (Repository) payload.getRepository();
		String cloneURL = repo.getURL();
		
		//Get the email of the committer to send test results
		Head_commit commit = (Head_commit) payload.getHead_commit();
		Committer committer = (Committer) commit.getCommitter();
		String email = committer.getEmail();

		//Clone the Github Repository
		CloneRepository.run(cloneURL,email);

		return new WebHookRequest();
	}

}