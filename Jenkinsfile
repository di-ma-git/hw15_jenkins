pipeline {
    agent {
        dockerfile {
            filename 'Dockerfile.test.maven'
            dir 'testconfig'
            args '-v $HOME/.m2:/root/.m2'
//             label 'test'
        }
    }
    stages {
        stage("1-Test") {
            steps {
                sh 'mvn clean test'
            }
        }
        stage("2-Build") {
            steps {
                sh 'mvn clean -DskipTests package'
            }
        }
    }
    post {
        success {
            echo 'Wow!'
            sh 'cp /var/lib/docker/volumes/jenkins_home/_data/workspace/${JOB_BASE_NAME}/target/demo-0.0.1-SNAPSHOT.jar $HOME/app/app.jar'
            sh 'printenv'
        }
    }
}