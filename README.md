# GroupOsu
Make sure to build path to the JL1.01 jar in the folder called External Jars. Right click the prohect and then click build path. If you don't do that the project won't work. You'll click Add Jars when you get to that menu part and select JL1.01 jar.

For the Scoring Database to work, you need to right click on the project and get configure. Then click configure gradle path.

Right click on the project. Click on Maven and click "add dependency".

Group: com.amazonaws

Artifact type: aws-java-sdk (do for both)

Version type: 1.11.256


Repeat. Right click on Project. Click on Maven and click "add dependency".

Group: com.amazonaws

Artifact type: samples (do for both)

Version type: 1.0.0

You also need to recreate the table if you wish to run it when the database isn't up. We will try to get a 24/7 server though.
Make sure to create the table in US East 1, which is US East (N. Virginia). The table should be called "RhythmGameScores".
LevelName will be the partition key and is a string. Score is the sort key and is an int/number. You need to append another one called Name which is a string. Then create the table and add one item to test it. Afterwards make sure your AWS credentials are connected to Eclipse.


After you click the space bar, it will take some time to load Song Selection UI. Please be patient!

