#!/usr/bin/env bash
# Compile

find . -name "*.java" > sources.txt
javac @sources.txt --class-path "lib/*" -d out