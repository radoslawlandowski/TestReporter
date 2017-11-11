#!/bin/bash

echo "### Install PostgreSQL: Start ###"

sudo apt-get install postgresql postgresql-contrib -y

export LANGUAGE=en_US.UTF-8
export LANG=en_US.UTF-8
export LC_ALL=en_US.UTF-8
sudo locale-gen en_US.UTF-8

sudo -u postgres psql -c "CREATE DATABASE testreporter"
sudo -u postgres psql -c "ALTER USER postgres PASSWORD 'postgres';"

echo "### Install PostgreSQL: Finish ###"
