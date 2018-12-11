@echo off
set TOMCAT_HOME=C:\Work\apache-tomcat-7.0.90
set CATALINA_HOME=C:\Work\apache-tomcat-7.0.90
set JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9797
%CATALINA_HOME%\bin\catalina.bat run -config conf/myapp.xml