# luke5
Luke for Lucene version 5

A mavenised version of Luke which works with version 5, and should also be compatible with version 4 indexes,
"forked" from https://code.google.com/p/luke/.

The code functionality is not being maintained, the changes merely get the thing working with the latest version.

#Building

Requires JDK 1.7+ and Maven 3.2.3+

#Running

If all goes well Maven will generate a jar with all the necessary dependencies in the target directory called "luke-5.0.0-jar-with-dependencies.jar". To run the code you will need to increase the PermSize available to the JVM (i.e. -XX:MaxPermSize=512m). For large indexes it's also worth giving it a bit of heap to play with (i.e. -Xmx2G). So to get things going...

java -Xmx2G -XX:MaxPermSize=512m -jar luke-5.0.0-jar-with-dependencies.jar -index path_to_lucene_index
