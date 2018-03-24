#!/bin/bash
docker stack rm tr && sleep 5 && docker stack deploy -c docker-compose.yml tr