#!/bin/bash

cd api

mvn package

apiContainerId=$(docker ps --filter "name=tr_api" --format "{{.ID}}")
docker restart $apiContainerId