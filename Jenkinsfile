pipeline {
    agent any

    environment {
        EC2_USER = 'ec2-user'
        EC2_HOST = '13.202.247.251'
        JAR_NAME = 'authservice-0.0.1-SNAPSHOT.jar'
        REMOTE_APP_DIR = '/home/ec2-user/app'
        DB_URL = 'jdbc:postgresql://localhost:5432/your_db'
        DB_USER = 'your_db_user'
        DB_PASSWORD = 'your_db_password'
        APP_PORT = '8080'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo '🏗️ Building the Spring Boot project...'
                sh './gradlew clean build'
            }
        }

        stage('Deploy to EC2') {
            steps {
                echo '🚀 Deploying to EC2...'

                // Stop any existing instance of the app
                sh """
                    ssh -i ${EC2_KEY} -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_HOST} \\
                    'pkill -f "${JAR_NAME}" || true'
                """

                // Copy the new JAR
                sh """
                    scp -i ${EC2_KEY} -o StrictHostKeyChecking=no build/libs/${JAR_NAME} \\
                    ${EC2_USER}@${EC2_HOST}:${REMOTE_APP_DIR}/
                """

                // Start the app with DB configs as environment variables
                sh """
                    ssh -i ${EC2_KEY} -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_HOST} << 'EOF'
                        export SPRING_DATASOURCE_URL=${DB_URL}
                        export SPRING_DATASOURCE_USERNAME=${DB_USER}
                        export SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD}
                        nohup java -jar ${REMOTE_APP_DIR}/${JAR_NAME} --server.port=${APP_PORT} > ${REMOTE_APP_DIR}/app.log 2>&1 &
                    EOF
                """
            }
        }
    }

    post {
        success {
            echo '✅ Spring Boot app deployed and connected to PostgreSQL!'
        }
        failure {
            echo '❌ Deployment failed. Check logs on EC2.'
        }
    }
}
