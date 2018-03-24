pipeline {
  agent any
  environment {
      API_IMAGE_NAME = "tr-api:${env.BUILD_TAG}"
      DB_IMAGE_NAME = "tr-db:${env.BUILD_TAG}"
      FRONT_IMAGE_NAME = "tr-fr:${env.BUILD_TAG}"
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
        git(url: 'https://github.com/radoslawlandowski/TestReporter', branch: 'master')
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
  }
}