pipeline {
    agent any
     tools {
        maven 'Maven'
    }
    stages {
        stage('build') {
            steps {
                echo 'Building..'
                sh 'mvn -f ./server-side/Task-manager/pom.xml install'
            }
        }
    }
}
