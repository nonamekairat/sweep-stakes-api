FROM openjdk:17
ADD target/SweepStakes.jar SweepStakes.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/SweepStakes.jar"]