pipeline {
    agent {
        label 'ubuntu-node-2'
    }
    stages {
        stage ('code pull') {
            steps {
                 git credentialsId: 'Github-cred', url: 'https://github.com/Agademanish/student-ui.git'
            }
        }
        stage ('code build') {
            steps {
                 sh 'sudo apt-get install maven -y'
                 sh 'mvn clean package'
            }
        }
        stage ('deploy code') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'ed3dd95e-3b84-4e38-ae8b-764e3f347774', path: '', url: 'http://54.166.25.158:8080/manager/html')], contextPath: '/', war: '**/*.war'
            }
        }
    }
}