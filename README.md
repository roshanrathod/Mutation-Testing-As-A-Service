# Github-Webhook

Uses **Github's Webhook** feature to receive HTTP POST and Run Mutation tests and send an email to the committer who made the changes.

#### Requirements 
> Windows OS (Since it contains runs test and server on command line)
> [NGROK](https://ngrok.com)
> [Maven](http://maven.apache.org/) 


This project uses:

1. Windows command prompt.
2. NGROK Server
3. [SpringBoot](http://spring.io/) 
4. [JGIT](https://eclipse.org/jgit/) 
5. [Maven](http://maven.apache.org/)
6. [PITest](http://pitest.org/)


```

For the program to run *without making any changes*:

[Download ngrok](https://ngrok.com/download) and unzip it into you C: drive, **C:\ngrok**. This folder will contain an exe which start a server when you run this program.

ngrok exposes localhost URL to Internet


[Download and install maven](http://maven.apache.org/download.cgi)

```


Start the program from webhook.java.
- It starts ngrok server on localhost:8080 and gives out an URL.
- Copy URL and [Add a webhook in your github repo](https://developer.github.com/webhooks/creating/) with the [copiedurl.com/payload]()





