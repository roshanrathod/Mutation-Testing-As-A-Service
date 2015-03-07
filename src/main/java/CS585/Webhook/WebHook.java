package CS585.Webhook;


import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * @author Roshan Rathod
 * 
 * Ngrok server -  starts a server and exposes the url http://localhost:8080 to Internet and provides a URL
 * 
 * Spring boot application - Starts a server on http://localhost:8080
 * 
 *
 */
@SpringBootApplication
public class WebHook {

	public static void main(String[] args) throws IOException, InterruptedException {

		//start ngrok server to expose http://localhost:8080 to Internet
		NgrokServer.run();



		SpringApplication.run(WebHook.class, args);
	}


}