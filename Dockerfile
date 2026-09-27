FROM tomcat:10.1-jdk25-temurin

COPY target/online-library.war /usr/local/tomcat/webapps/online-library.war

EXPOSE 8080

CMD ["catalina.sh", "run"]