export CATALINA_HOME=/home/ranvir/apache-tomcat-7.0.82/
export TOMCAT_HOME=/home/ranvir/apache-tomcat-7.0.82/
export JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
$CATALINA_HOME/bin/catalina.sh run -config conf/myapp.xml
