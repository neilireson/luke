# luke

Luke for Lucene

A mavenised version of Luke which works with latest version (6), and also with some previous index versions.

## History

Originally "forked" from https://code.google.com/p/luke/.

I mavenised the code so I could more easily (for me) keep up-to-date with the latest Lucene version (see https://groups.google.com/d/msg/luke-discuss/MNT_teDxVno/p8AB1Ibjb_EJ).

This has lead to a couple of other forks of luke:
* https://github.com/tarzanek/luke (not mavenised)
* https://github.com/DmitryKey/luke

The code functionality is not being maintained, I merely intend to get the code working with the latest Lucene version and try not to break anything important. So far I think most everything still works, however nothing is rigorously tested. So best not use it on the only copy of your last five years worth of data.

There is some hope that there will be an "official" application to take the place of Luke (https://issues.apache.org/jira/browse/LUCENE-2562) but this is not here yet.

It's also worth noting that there is a Command Line tool for Lucene, https://github.com/javasoze/clue, which is also working with Lucene 6.

## Building

Requires JDK 1.8+ and Maven 3.2.3+

## Running

If all goes well Maven will generate a jar with all the necessary dependencies in the target directory called "luke-6.0.0-jar-with-dependencies.jar". Note that the version number will change depending on the Lucene version with which the code is compiled. 

java -Xmx2G -jar luke-6.0.0-jar-with-dependencies.jar -index path_to_lucene_index
