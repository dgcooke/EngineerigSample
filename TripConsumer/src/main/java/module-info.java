module com.smartphone.engineeringsample.tripconsumer {
	requires java.logging;
	//requires lombok;
	exports com.smartphone.engineeringsample.tripconsumer;
	exports com.smartphone.engineeringsample.tripconsumer.transaction;
	opens files;
}
