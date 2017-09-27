#!/bin/bash

echo "### Install Java : Start ###"

sudo apt-get update
sudo apt-get install openjdk-8-jdk -y

java -version

echo "### Install Java : Finish ###"
