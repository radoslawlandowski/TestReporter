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
                git 'https://github.com/radoslawlandowski/TestReporter.git'
            }
        }
        stage('Install') {
            steps {
                sh 'mvn install'
            }
        }
    }
}