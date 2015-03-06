package CS585.Webhook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class GithubResponse {
	

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class WebHookRequest {

		private String payload;

	}

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

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Head_commit {

		private Committer committer;

		public Object getCommitter(){
			return committer;
		}


	}

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



}
