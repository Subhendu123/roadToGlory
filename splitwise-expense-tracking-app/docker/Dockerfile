FROM tomcat:jdk17-temurin
ADD splitwise-app.war /usr/local/tomcat/webapps/
# EXPOSE 8082 - don't uncomment it
# EXPOSE 8085
CMD ["catalina.sh", "run"]
# docker cmd to run it:
# docker run -p 8082:8080 subhalvcatcher/splitwise-app:3.0

# 8080 is the default port of this image after running it in a container