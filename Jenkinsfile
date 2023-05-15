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
//         stage("1-Test") {
//             steps {
//                 sh 'mvn clean test'
//             }
//         }
        stage("2-Build") {
            steps {
                sh 'mvn clean -DskipTests package'
                sh 'sshpass -p $ACCESS_KEY_PSW scp $JOB_BASE_NAME/target/demo-0.0.1-SNAPSHOT.jar $ACCESS_KEY_USR@185.106.92.133:/home/jenkins/app/app.jar'

            }
        }
    }
    post {

        success {

            echo 'Wow!'
//             sh 'cp /var/lib/docker/volumes/jenkins_home/_data/workspace/${JOB_BASE_NAME}/target/demo-0.0.1-SNAPSHOT.jar $HOME/app/app.jar'


            sh 'printenv'
        }
    }
}