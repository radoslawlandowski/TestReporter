#!/bin/bash
export API_IMAGE_NAME="radoslawlandowski/tr-api:0.1.0"
export DB_IMAGE_NAME="radoslawlandowski/tr-db:0.1.0"
export FR_IMAGE_NAME="radoslawlandowski/tr-fr:0.1.0"

docker pull $API_IMAGE_NAME
docker pull $DB_IMAGE_NAME
docker pull $FR_IMAGE_NAME

docker stack rm tr && sleep 5 && docker stack deploy -c docker-compose.yml tr --with-registry-auth