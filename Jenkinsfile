pipeline {
    agent any
    stages {
        stage("0-Prepare") {
            steps {
                sh 'cd /root/java_core_2023_jan'
                git url: 'git@github.com:isicju/java_core_2023_jan.git'
            }
        }
        stage("1-Test") {
            agent { docker "maven:3-alpine"}
            steps {
                sh 'mvn clean test'
            }
        }
        stage("2-Build") {
            agent { docker "maven:3-alpine"}
            steps {
                sh 'mvn clean -DskipTests package'
            }
        }
    }
}