pipeline {
  agent any
  stages {
    stage('Hello!') {
      steps {
        echo 'Hello!'
      }
    }
    stage('Git Checkout') {
      steps {
        git(url: 'https://github.com/radoslawlandowski/TestReporter', branch: 'master')
      }
    }
  }
}