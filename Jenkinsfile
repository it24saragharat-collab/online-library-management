pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code from GitHub...'
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                echo 'Building Online Library Management System...'
                bat 'mvn clean package'
            }
        }

        stage('Selenium Test') {
            steps {
                echo 'Running Selenium automated tests...'
                bat 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                bat 'docker build -t online-library-management .'
            }
        }

    }
}