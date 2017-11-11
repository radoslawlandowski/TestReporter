#!/bin/bash

echo "### Setup Storage Directory : Start ###"

sudo mkdir /TestReporter

echo "### Setup Storage Directory : Finish ###"

echo "### Install PostgreSQL: Start ###"

sudo apt-get install postgresql postgresql-contrib -y

export LANGUAGE=en_US.UTF-8
export LANG=en_US.UTF-8
export LC_ALL=en_US.UTF-8
sudo locale-gen en_US.UTF-8

sudo -u postgres psql -c " CREATE USER admin WITH PASSWORD 'admin';"

echo "### Install PostgreSQL: Finish ###"
