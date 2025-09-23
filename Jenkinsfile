pipeline {
    agent any

    tools {
        maven 'Maven3.9.11'
        jdk 'JDK20'
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
