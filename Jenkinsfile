pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                echo 'Building..'
                maven('Maven') {
                    sh 'mvn clean & mvn build'
                }
            }
        }
    }
}
