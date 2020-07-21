# ten-pin-bowling

Ten pin bowling game.  

Input for execution is done with a file. A sample valid file can be found in `src/test/resources/test_input.txt` path of this project.  

Steps to execute:  

1. run `mvn clean package`  
2. `cd target`  
3. `java -jar ten-pin-bowling-1.0-SNAPSHOT.jar /path/to/input/file` (Check if version in jar file name matches the version you packaged. Also, change the input file path to a valid file)  

This code was compiled packaged and tested with Apache Maven 3.6.0 along with Java 11.0.2 (vendor: Oracle Corporation) and Java 1.8.0_121 (vendor: Java HotSpot(TM))  

Example of output, using as input the test_input.txt file contained in the project:  
```
========== PLAYER: Jeff  
Frame		1		2		3		4		5		6		7		8		9		10			   
Pinfalls		X	7	/	9	0		X	0	8	8	/	F	6		X		X	X	8	1	  
Score		20		39		48		66		74		84		90		120		148		167			  
========== PLAYER: John
Frame		1		2		3		4		5		6		7		8		9		10			
Pinfalls	3	/	6	3		X	8	1		X		X	9	0	7	/	4	4	X	9	0	
Score		16		25		44		53		82		101		110		124		132		151			
```

Steps to run tests:  

1. run `mvn clean test`  


