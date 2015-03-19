# luke5
Luke for Lucene version 5

A mavenised version of Luke which works with version 5, and also with all version 4 indexes.

## History

Originally "forked" from https://code.google.com/p/luke/.

I originally mavenised the code so I could more easily (for me) keep up-to-date with the latest Lucene version (see https://groups.google.com/d/msg/luke-discuss/MNT_teDxVno/p8AB1Ibjb_EJ).

This has lead to a couple of other forks of luke:
* https://github.com/tarzanek/luke
* https://github.com/DmitryKey/luke

The code functionality is not being maintained, I merely intend to get the code working with the latest Lucene version and try not to break anything important. So far I think most everything still works, however nothing is rigorously tested. So best not use it on the only copy of your last five years worth of data.

There is some hope that there will be an "official" applications to take the place of Luke (https://issues.apache.org/jira/browse/LUCENE-2562) but this is not here yet.

It's also worth noting that there is a Command Line tool for Lucene, https://github.com/javasoze/clue, which is also working with Lucene 5.

## Building

Requires JDK 1.7+ and Maven 3.2.3+

## Running

If all goes well Maven will generate a jar with all the necessary dependencies in the target directory called "luke-5.0.0-jar-with-dependencies.jar". To run the code you will need to increase the PermSize available to the JVM (i.e. -XX:MaxPermSize=512m). For large indexes it's also worth giving it a bit of heap to play with (i.e. -Xmx2G). So to get things going...

java -Xmx2G -XX:MaxPermSize=512m -jar luke-5.0.0-jar-with-dependencies.jar -index path_to_lucene_index
