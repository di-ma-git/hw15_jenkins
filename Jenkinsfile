pipeline {
    agent {
        dockerfile {
            filename 'Dockerfile.test.config'
            dir 'testconfig'
            label 'test'
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
        }
    }
}