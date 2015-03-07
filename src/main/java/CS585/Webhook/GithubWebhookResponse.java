package CS585.Webhook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author Roshan Rathod
 * 
 * Contains classes with attributes equivalent to the JSON format i.e the HTTP POST sent by github
 * 
 * Defines the fields needed from the POST and implements getters and setter to extract the data from the POST. 
 *
 */

public class GithubWebhookResponse {


	/*
	 * Get the entire HTTP POST and store in payload 
	 *
	 */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class WebHookRequest {

		private String payload;
	}



	/*
	 * Defines the attributes which are needed to be extracted from the payload Object
	 *
	 */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Payload {

		private Repository repository;
		private Head_commit head_commit;


		public Object getHead_commit(){
			return head_commit;
		}

		public Object getRepository() {
			return repository;
		}
	}


	/*
	 * Defines attribute needed from Head_Commit object
	 *
	 */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Head_commit {

		private Committer committer;

		public Object getCommitter(){
			return committer;
		}
	}


	/*
	 * Defines attributes needed from Committer object
	 * Gets committers email
	 *
	 */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Committer{
		private String name;
		private String email;

		public String getEmail(){
			return email;
		}

		public String getName(){
			return name;
		}

	}


	/*
	 * Defines attribute needed from Repository object
	 * Gets Repo URL and appends .git for cloning purposes
	 *
	 */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Repository {

		private String url;

		public String getURL(){
			return url + ".git";
		}
	}

}




