pipeline {
  agent any

  stages {

    stage('init'){
      steps{
        sh "echo init"
      }
    }

    stage('Build docker image') {
      steps {
        script {
          docker.build("sudo docker build -t springboot:latest ./BackEnd")
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
