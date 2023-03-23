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

    stage('pwd'){
      steps{
        sh 'pwd'
        sh 'ls -al'
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
