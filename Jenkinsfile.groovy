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
        sh 'echo "BUILD_USER: $(whoami)"'
      }
    }

    stage('Build docker image') {
      steps {
        script {
          docker.build("docker build -t springboot:latest ./Backend")
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
