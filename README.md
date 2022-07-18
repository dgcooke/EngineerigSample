EngineeringSample
mvn package
cp TripSimulator/target/TripSimulator-1.0.jar modules/
cp TripConsumer/target/TripConsumer-1.0.jar modules/
java --module-path modules --module com.smartphone.engineeringsample.tripsimulator


Assumptions
End result can be one of COMPLETED, INCOMPLETE or CANCELLED
