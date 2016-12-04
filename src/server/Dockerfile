FROM openjdk
EXPOSE 8080
ADD ./dist/app.jar /srv/app.jar
ADD ./dist/jackson-core-2.8.3.jar /srv/jackson-core-2.8.3.jar
ADD ./dist/jackson-annotations-2.8.3.jar /srv/jackson-annotations-2.8.3.jar
ADD ./dist/jackson-databind-2.8.3.jar /srv/jackson-databind-2.8.3.jar
ADD ./dist/restlet.jar /srv/restlet.jar
ADD ./dist/restlet-json.jar /srv/restlet-json.jar
ADD ./dist/json.jar /srv/json.jar
CMD java -cp /srv/jackson-annotations-2.8.3.jar:/srv/jackson-databind-2.8.3.jar:/srv/jackson-core-2.8.3.jar:/srv/restlet.jar:/srv/restlet-json.jar:/srv/json.jar:/srv/app.jar com/deadlock/server/DeadLockServer
