FROM java:8
ADD dapo-authserver-0.0.1-SNAPSHOT.jar app.jar
VOLUME /tmp
VOLUME /target
RUN bash -c 'touch /app.jar'
EXPOSE 18082 18182
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=18182 -jar /app.jar