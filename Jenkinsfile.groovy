pipeline {
  agent any

  stages {

    stage('init'){
      steps{
        sh "echo init"
      }
    }

    stage('Gradle Build'){
      steps{
        sh 'cd ${env.WORKSPACE}/Backend'
        sh 'ls -al'
        sh 'chmod +x gradlew'
        sh './gradlew build'
      }
    }

    stage('Build docker image') {
      steps {
        script {
              def backendDir = "${env.WORKSPACE}/Backend"
              def dockerfile = "${backendDir}/Dockerfile"
              docker.build("my-springboot-image:${env.BUILD_NUMBER}", "-f ${dockerfile} ${backendDir}")
        }
      }
    }

    stage('Deploy with docker-compose') {
      steps {
        script {
          sh "docker-compose -f docker-compose.yml up -d"
        }
      }
    }
  }
}
