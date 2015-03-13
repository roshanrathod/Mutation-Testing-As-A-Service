# Mutation Testing As A Service(MTaaS) for Github Repositories

Uses **Github's Webhook** feature to receive HTTP POST and Run Mutation tests and send an email to the committer who made the changes.


--------------------------------

#### Requirements 
- Windows OS (Since it contains runs tests and server on command prompt)
- [NGROK](https://ngrok.com)
- [Maven](http://maven.apache.org/) 


--------------------------------
This project uses:


1. [NGROK](https://ngrok.com)
2. [SpringBoot](http://spring.io/) 
3. [JGIT](https://eclipse.org/jgit/) 
4. [Maven](http://maven.apache.org/)
5. [PITest](http://pitest.org/)
6. Windows command prompt.


--------------------------------

For the program to run *without making any changes*:

[Download ngrok](https://ngrok.com/download) and unzip it into you C: drive, **C:\ngrok**. This folder will contain an exe which start a server when you run this program.

ngrok exposes localhost URL to Internet


[Download and install maven](http://maven.apache.org/download.cgi)


--------------------------------
Start the program from webhook.java.

- It starts ngrok server on localhost:8080 and gives out an URL.
- Copy the URL and [Add a webhook in your github repo](https://developer.github.com/webhooks/creating/). Set the payload URL as [copiedurl/payload](). It would be something like http://4uebebsdwd.ngrok.com/payload
- Push something in the repository(Commit)
- Github will send HTTP POST onto the url, you've set.
- Springboot running on localhost:8080/payload will fetch the post and extract Github Repository URL and Email.
- JGIT will Clone the Repository in your TEMP folder.
- Once cloned, you will see new cmd prompt window.
- Maven will first build the project and then run mutation tests on the project.(By default it runs test on the root of the package, so all classes will have a mutation test report which is created under target folder called pit-reports. For further reading, [refer this](http://pitest.org/quickstart/maven/))
- The test reports folder is compressed to a ZIP archive using [zeroturnaround/zt-zip](https://github.com/zeroturnaround/zt-zip)(Awesome Library!)
- An email will be sent with the ZIP as an attachment to the committer using [Java Mail API](https://java.net/projects/javamail/pages/Home)


--------------------------------

NOTES / Possible Errors:

- This will not work without proper maven installation.
- If ngrok is not in c: drive, modify the location in ngrokserver.java
- Springboot, javax and JGIT dependenices are in pom.xml
- Mutation Tests will ONLY work with MAVEN projects, since it runs maven commands.
- JGIT seems to be really slow at times, so please be patient when it clones the repository(it might look program is not working, but it is)
- Ignore warning or errors in ngrok/github webhook response/eclipe or whichever IDE you use. It works regardless of the http response or errors in IDE which are due to the http responses
- Make sure NO OTHER PROGRAM IS RUNNING ON OR USING http://localhost:8080. In that case SpringBoot wont work and you shall get Tomcat initialization error.
- Also, make sure you stop the program from IDE stop button AS WELL AS IDE's output/console windows stop button before restating or re-running it. You shall get Tomcat Initialization error. If it continues, close the IDE and start again, it should work fine.

To use this code / make any contributions / for any further questions or help please email me at roshan.rathod@gmail.com



