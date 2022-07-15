module com.smartphone.engineeringsample.tripconsumer {
	requires java.logging;
	exports com.smartphone.engineeringsample.tripconsumer;
	exports com.smartphone.engineeringsample.tripconsumer.transaction;
	exports com.smartphone.engineeringsample.tripconsumer.billing;
	exports com.smartphone.engineeringsample.tripconsumer.trip;
	opens files;


}
