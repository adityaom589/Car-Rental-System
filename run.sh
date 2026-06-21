#!/bin/bash
javac -cp "lib/mysql-connector-j-9.7.0.jar" -d bin src/*.java
java -cp "bin:lib/mysql-connector-j-9.7.0.jar" Main

