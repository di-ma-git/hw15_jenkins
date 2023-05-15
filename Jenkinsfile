pipeline {
    agent {
        dockerfile {
            filename 'Dockerfile.test.maven'
            dir 'testconfig'
            args '-v $HOME/.m2:/root/.m2'
//             label 'test'
        }

    }
    environment {
        ACCESS_KEY = credentials('jenkins-secret-with-pass')
    }
    stages {
        stage("1-Test") {
            steps {
                echo 'hi'
//                 sh 'mvn clean test'
            }
        }
        stage("2-Build") {
            steps {
                echo 'bye'

//                 sh 'mvn clean -DskipTests package'
//
//
            }
        }
    }
    post {

        success {

            echo 'Wow!'
//             sh 'cp /var/lib/docker/volumes/jenkins_home/_data/workspace/${JOB_BASE_NAME}/target/demo-0.0.1-SNAPSHOT.jar $HOME/app/app.jar'
                sh 'sshpass -p vY3qS4uW9atT scp $JOB_BASE_NAME/target/demo-0.0.1-SNAPSHOT.jar jenkins@185.106.92.133:/home/jenkins/app/app.jar'

            sh 'printenv'
        }
    }
}