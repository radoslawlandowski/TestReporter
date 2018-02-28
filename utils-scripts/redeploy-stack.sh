#!/bin/bash

docker stack rm tr && docker stack deploy -c docker-compose.yml tr