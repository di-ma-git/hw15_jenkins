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
//         stage("1-Test") {
//             steps {
//                 sh 'mvn clean test'
//             }
//         }
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