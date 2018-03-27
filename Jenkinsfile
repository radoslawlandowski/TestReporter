pipeline {
  agent any
  
  parameters {
      booleanParam(name: 'cleanAfterwards', defaultValue: true, description: 'Clean images and stack after succesful stack deployment')
  }
  
  environment {
      API_IMAGE_NAME = "tr-api:${env.BUILD_TAG}"
      DB_IMAGE_NAME = "tr-db:${env.BUILD_TAG}"
      FRONT_IMAGE_NAME = "tr-fr:${env.BUILD_TAG}"
      STACK_NAME = "prod"
  }               
  stages {
    stage('Prepare') {
      steps {
       ansiColor('xterm') {
            echo '====================================================='
            sh 'printenv'
            echo '====================================================='
        }
      }
    }
    stage('Git Checkout') {
      steps {
        git(url: 'https://github.com/radoslawlandowski/TestReporter', branch: "${env.GIT_BRANCH}")
      }
    }
    stage('Build images') {
      steps {
          parallel api: {
            dir('./api') {
               ansiColor('xterm') {
                   sh "docker build -t ${env.API_IMAGE_NAME} ."
               }
            }        
          }, db: {
            dir('./db') {
               ansiColor('xterm') {
                   sh "docker build -t ${env.DB_IMAGE_NAME} ."
               }
            }             
          }, front: {
            dir('./frontend') {
               ansiColor('xterm') {
                   sh "docker build -t ${env.FRONT_IMAGE_NAME} ."
               }
            }             
          }, failFast: true
      }
    }
    stage('Deploy stack') {
      steps {
        sh "sh utils-scripts/deploy-stack.sh \"${env.STACK_NAME}\" \"${env.API_IMAGE_NAME}\" \"${env.DB_IMAGE_NAME}\" \"${env.FRONT_IMAGE_NAME}\""
      }
    }
    stage('Clean up') {
        when {
            expression { params.cleanAfterwards == true }
        }
      steps {
        sh "docker stack rm tr-${env.STACK_NAME}"
        sleep 10
        sh "docker images -a | grep '${env.BUILD_TAG}' | awk '{print \$3}' | xargs docker rmi -f"
      }
    }
  }
}