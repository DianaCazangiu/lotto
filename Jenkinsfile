pipeline {
    agent any

    tools {
        maven 'C:/Users/diana/Documents/apache-maven-3.9.11-bin/apache-maven-3.9.11/bin'
        jdk 'C:/Program Files/Java/jdk-20/bin'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat "mvn clean test"
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target',
                    reportFiles: 'extent-report.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }
}
