pipeline {
  agent any

  stages {

    stage('init'){
      steps{
        sh "echo init"
      }
    }

    stage('whoami'){
      steps{
        sh 'echo "BUILD_USER: ${BUILD_USER}"'
      }
    }

    stage('Build docker image') {
      steps {
        script {
          sudo docker.build("sudo docker build -t springboot:latest ./BackEnd")
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
