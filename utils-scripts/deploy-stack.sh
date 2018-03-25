#!/bin/bash

# --------- CONFIGURATION --------- 
stackEnv="prod"
stackName="tr-$stackEnv"

export API_IMAGE_NAME="radoslawlandowski/tr-api:0.1.0"
export DB_IMAGE_NAME="radoslawlandowski/tr-db:0.1.0"
export FR_IMAGE_NAME="radoslawlandowski/tr-fr:0.1.0"
# --------- /CONFIGURATION --------- 


# --------- UTIL FUNCTIONS --------- 
echoTimed() {
  currentDate=$(date +"%T")
  echo "$currentDate: $1"
}
# --------- /UTIL FUNCTIONS --------- 

# --------- MAIN SCRIPT --------- 

echoTimed "Main script execution started!"

docker pull $API_IMAGE_NAME
docker pull $DB_IMAGE_NAME
docker pull $FR_IMAGE_NAME

docker stack deploy -c docker-compose.$stackEnv.yml $stackName

echoTimed "The stack: $stackName has been deployed. Initiating healthchecks...\n"

statusCode=""
healthcheckEndpoint="127.0.0.11:8083/api/test-groups"
while true; do
    echoTimed "Checking if service responds with code "200" at $healthcheckEndpoint ..."
    response=$(curl --write-out %{http_code} --silent --output /dev/null $healthcheckEndpoint)
    echoTimed "Response code: $response"
    if [ "$response" -eq "200" ]
    then
        break
    fi
    echoTimed "Non 200 response was obtained, sleeping 3 seconds...\n\n"
    sleep 3
done

echoTimed "\n***************************************
* Application $stackName started and working! *
***************************************"

# --------- /MAIN SCRIPT --------- 
