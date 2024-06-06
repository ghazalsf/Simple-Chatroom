# Chatroom

## Overview
This is a really simple console-based chatroom implemented using Java, with concepts of threads, socket programming, and client-server architecture.

Initially, the server runs and listens to requests from clients. Clients connect to the server using an IP address (localhost in this application) and a port number (you can choose any port number, such as 1234). The server then creates a new thread for each incoming request. Upon connecting to the server, each client is assigned a unique name. You can define the desired name for each user based on their individual preferences.

In this chat room, every client can send messages. Each client's message is sent to the server, which then displays it to all connected clients.

## Setup
To download and unzip the project, follow these steps:

1. Download the project.
2. Extract the contents of the archive to a directory of your choice.

Once you have extracted the project, navigate to the `src` directory using your terminal or command prompt. You can do this by using the `cd` command followed by the path to the `src` directory.

For example, if the project directory is named `chatroom_project` and is located on your desktop, and the `src` directory is inside it, you can use the following commands:

```bash
cd ~/Desktop/chatroom_project/src
```

Now, you should be in the `src` directory of the project.

Next, run the server using the following command:

```bash
javac Server.java
java Server
```

After the server is running, you can run the client. Similarly, execute the client's main class. You can run it using the following command:

```bash
javac Client.java
java Client
```

Following these steps should allow you to run the server and client components of the project. Note that you can run "client.java" as many clients as you want.