pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                cleanWs()
            }
        }
        stage('Checkout') {
            steps {
                git branch: "${env.BRANCH_NAME}", url: 'https://github.com/radoslawlandowski/TestReporter.git'
            }
        }
        stage('Install') {
            steps {
                sh 'mvn install'
            }
        }
    }
}