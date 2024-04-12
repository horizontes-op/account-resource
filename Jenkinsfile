pipeline {
    agent any
    stages {
        stage('Build Account') {
            steps {
                build job: 'account push', wait: true
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn clean package'
            }
        }      
        stage('Build Image') {
            steps {
                script {
                    docker.build("fernandowi55/account:latest", "-f Dockerfile .")
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credential') {
                        docker.image("fernandowi55/account:latest").push()
                    }
                }
            }
        }
    }
}