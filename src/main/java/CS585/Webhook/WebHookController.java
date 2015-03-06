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

import CS585.Webhook.GithubResponse.Committer;
import CS585.Webhook.GithubResponse.Head_commit;
import CS585.Webhook.GithubResponse.Payload;
import CS585.Webhook.GithubResponse.Repository;
import CS585.Webhook.GithubResponse.WebHookRequest;


@Controller
@RequestMapping("/payload")
public class WebHookController {

	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody 
	public  WebHookRequest getPayload(@RequestBody Payload payload) throws InvalidRemoteException, TransportException, IOException, GitAPIException {


		Repository repo = (Repository) payload.getRepository();
		String url = repo.getURL();

		new CloneRepository(url);

		Head_commit commit = (Head_commit) payload.getHead_commit();
		Committer committer = (Committer) commit.getCommitter();
		String email = committer.getEmail();


		return new WebHookRequest();
	}

}