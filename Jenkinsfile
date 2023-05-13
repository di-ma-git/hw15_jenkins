pipeline {
    agent any
    stages {
//         stage("0-Prepare") {
//             steps {
//                 sh 'cd /root/java_core_2023_jan'
//                 git url: 'git@github.com:isicju/java_core_2023_jan.git'
//             }
//         }
        stage("1-Test") {
            agent { docker {image 'maven:3.8.1-openjdk-17-slim'}}
            steps {
                sh 'apt-get install libaio1'
                sh 'mvn clean test'
            }
        }
        stage("2-Build") {
            agent { docker {image 'maven:3-alpine'}}
            steps {
                sh 'mvn clean -DskipTests package'
            }
        }
    }
}