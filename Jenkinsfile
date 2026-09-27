pipeline {

    agent any

    tools {
        jdk 'JDK25'
        maven 'Maven-3.9.16'
    }

    options {
        skipDefaultCheckout(true)
    }

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
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Start Web App') {
            steps {
                echo 'Starting Online Library web application...'

                bat 'start "JettyServer" /B cmd /c "mvn jetty:run -Djetty.http.port=8081 > jetty.log 2>&1"'

                bat 'powershell -NoProfile -Command "$deadline=(Get-Date).AddSeconds(60); while((Get-Date) -lt $deadline) { try { Invoke-WebRequest -UseBasicParsing http://localhost:8081/ -TimeoutSec 2 | Out-Null; exit 0 } catch { Start-Sleep -Seconds 2 } }; Get-Content jetty.log; exit 1"'
            }
        }

        stage('Selenium Test') {
            steps {
                echo 'Running Selenium automated tests...'
                bat 'mvn test'
            }
        }

        stage('Stop Web App') {
            steps {
                echo 'Stopping web application...'

                bat '''
                    for /f "tokens=5" %%P in ('netstat -ano ^| findstr ":8081" ^| findstr "LISTENING"') do taskkill /PID %%P /F >NUL 2>&1
                    exit /B 0
                '''
            }
        }

        stage('Docker Build') {
    steps {
        echo 'Building Docker image...'

        bat '''
            set "PATH=C:\\Users\\LOQ\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin;%PATH%"

            docker build -t online-library-management .
            if errorlevel 1 exit /B 1
        '''
    }
}

stage('Docker Push') {
    steps {
        echo 'Pushing Docker image to Docker Hub...'

        bat '''
            set "PATH=C:\\Users\\LOQ\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin;%PATH%"

            docker buildx build --push -t saragharat0613/online-library-management:latest .

            if errorlevel 1 exit /B 1
        '''
    }
}                 
    }

    post {
        always {
            bat '''
                for /f "tokens=5" %%P in ('netstat -ano ^| findstr ":8081" ^| findstr "LISTENING"') do taskkill /PID %%P /F >NUL 2>&1
                exit /B 0
            '''
        }
    }
}