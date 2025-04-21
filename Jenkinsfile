pipeline {
    agent any

     environment {
         DB_URL = 'jdbc:postgresql://localhost:5432/postgres'
         DB_USERNAME = 'postgres'
         DB_PASSWORD = 'postgres'
     }


    stages {
        stage('Clone Code') {
            steps {
                git branch: 'main', url: 'https://github.com/iamadityaraj04/authservice.git'
            }
        }

         stage('Build') {
            steps {
                echo 'Running Build...'
                timeout(time: 15, unit: 'MINUTES') {
                    sh './gradlew build -x test'
                }
            }

        }

//         stage('Run Test') {
//             steps {
//                 echo "Test Running..."
//                 sh './gradlew test'
//             }
//         }

        stage('Deploy') {
            steps {
                echo 'Deploying...'
//                 sh '''
//                     mkdir -p $DEPLOY_DIR
//                     cp build/libs/*.jar $DEPLOY_DIR/$JAR_NAME
//
//                     # Kill existing service (optional)
//                     pkill -f $JAR_NAME || true
//
//                     # Run in background
//                     nohup java -jar $DEPLOY_DIR/$JAR_NAME > $DEPLOY_DIR/app.log 2>&1 &
//                 '''
            }
        }
    }
}
