#!/bin/bash


# --------- UTIL FUNCTIONS --------- 
echoTimed() {
  currentDate=$(date +"%T")
  echo "$currentDate: $1"
}
# --------- /UTIL FUNCTIONS --------- 


# --------- INITIALIZATION --------- 

echoTimed "Make sure you are authenticated into docker via 'docker login' command and you have port :8083 free to use!"

echoTimed "User input params: 
stackEnv: $1 
apiImageName: $2 
dbImageName: $3
frImageName: $4\n"

stackEnv=$1
apiImageName=$2
dbImageName=$3
frImageName=$4
# --------- /INITIALIZATION --------- 


# --------- CONFIGURATION --------- 
healthcheckEndpoint="127.0.0.1:8083/api/test-groups"

stackEnv="${stackEnv:-prod}"
fullStackName="tr-$stackEnv"

apiImageName="${apiImageName:-radoslawlandowski/tr-api:0.1.1}"
dbImageName="${dbImageName:-radoslawlandowski/tr-db:0.1.1}"
frImageName="${frImageName:-radoslawlandowski/tr-fr:0.1.1}"

dockerComposeFile="docker-compose.$stackEnv.yml"

if [ ! -f $dockerComposeFile ]; then
    echoTimed "File $(pwd)/$dockerComposeFile not found!"
    exit 1
fi

echoTimed "\nExporting environment variables..."
export API_IMAGE_NAME=$apiImageName
export DB_IMAGE_NAME=$dbImageName
export FR_IMAGE_NAME=$frImageName

echoTimed "Final runtime params: 
stackEnv: $stackEnv 
apiImageName: $apiImageName
dbImageName: $dbImageName
frImageName: $frImageName
dockerComposeFile: $dockerComposeFile
fullStackName: $fullStackName
"

# --------- /CONFIGURATION --------- 


# --------- MAIN SCRIPT --------- 

echoTimed "Main script execution started!"
echoTimed "Deploying might take a few minutes as images might be downloaded from hub..."

docker swarm init
docker stack deploy -c $dockerComposeFile $fullStackName

echoTimed "The stack: $fullStackName has been deployed. Starting healthchecks...\n"

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
* Application $fullStackName started and working! *
***************************************"

# --------- /MAIN SCRIPT --------- 
