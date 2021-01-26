.PHONY: build
build:
	mvn package

.PHONY: examples
examples:
	mvn exec:java -q -Dexec.mainClass=org.cicirello.examples.jpt.AverageDistance
	mvn exec:java -q -Dexec.mainClass=org.cicirello.examples.jpt.TableOfDistances
	mvn exec:java -q -Dexec.mainClass=org.cicirello.examples.jpt.PermutationExamples
	mvn exec:java -q -Dexec.mainClass=org.cicirello.examples.jpt.SimpleDistanceExamples
	mvn exec:java -q -Dexec.mainClass=org.cicirello.examples.jpt.SequenceDistanceExamples
	mvn exec:java -q -Dexec.mainClass=org.cicirello.examples.jpt.PermutationHashCodes
	mvn exec:java -q -Dexec.mainClass=org.cicirello.examples.jpt.RandomIndexerTimes

.PHONY: arxiv2019
arxiv2019:
	mvn exec:java -q -Dexec.mainClass=org.cicirello.replication.arxiv2019may.CompareKendallTauSequenceDistAlgsDoubles
	mvn exec:java -q -Dexec.mainClass=org.cicirello.replication.arxiv2019may.CompareKendallTauSequenceDistAlgsInts
	mvn exec:java -q -Dexec.mainClass=org.cicirello.replication.arxiv2019may.CompareKendallTauSequenceDistAlgsStrings
	mvn exec:java -q -Dexec.mainClass=org.cicirello.replication.arxiv2019may.CompareKendallTauSequenceDistAlgsStringArray

.PHONY: bict2019
bict2019:
	mvn exec:java -q -Dexec.mainClass=org.cicirello.replication.bict2019.BICT2019

.PHONY: flairs2013
flairs2013:
	mvn exec:java -q -Dexec.mainClass=org.cicirello.replication.flairs2013.Flairs2013

.PHONY: tevc2016
tevc2016:
	mvn exec:java -q -Dexec.mainClass=org.cicirello.replication.ieeetevc2016.FDC

.PHONY: clean
clean:
	mvn clean	
	