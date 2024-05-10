pipeline {
    agent any
    stages {
        stage('account interface') {
            steps {
                build job: 'account interface', wait: true
            }
        }
        stage('account interface') { 
            steps {
                sh 'mvn clean package'
            }
        }      
        stage('build image account') {
            steps {
                script {
                    account = docker.build("fernandowi55/account:${env.BUILD_ID}", "-f Dockerfile .")
                }
            }
        }
        stage('push image account') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credential') {
                        account.push("latest")
                        account.push("${env.BUILD_ID}")
                       
                    }
                }
            }
        }

        
    }
}