# Fog

### Description
This directory is dedicated to an examination project in the Datology education. It concerns a company, who specializes, among other things, in Carport construction. The assigned task is, to create functionality for generation of a carport with a varying set of elements, such as sheds if the customer wants one, and create illustrations for said carport. It also contains functionality for an employee to interact with the transaction request.

Part of the assignment was to use Scrum to prepare the students for projects where it is required to work as a team.

### Import notes

**To test the admin status use the following account:** 

username = admin  

password = admin

As part of the assignment a Javadoc must be provided for the program. It can be found in the project's github pages:

https://mutestock.github.io/Fog/

An example of the deployed .war file can be accessed here:

http://165.227.148.141:8080/FogProject


### Guide

You can host the website yourself by defining and locating the target .war folder, executing the program in your Java IDE and  deploying it onto your server. This is, however, very simplified and you should refer to other sites for more detailed information regarding deployment of .war files if you run into issues. 
The following digital components are required in order to succesfully recreate it:

* [Maven](https://maven.apache.org/) - Dependencies. Necessary.
* [Tomcat](https://tomcat.apache.org/) - Java Servlet, JavaServer Pages, Java Expression Language and Java WebSocket technologies

Maven is preinstalled in the Netbeans IDE, which is the IDE that was used in the creation of this project. Other IDEs may not.

You'll also need to create your own DBConnector, since the final DBConnector was set to ignore.

### Acknowledgements

The github page is currently a redirection to the Javadoc. Ultimately, it was never created for anything else. One could argue, that it should function as a hub for the project with graphics. 
