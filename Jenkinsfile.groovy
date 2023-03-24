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
        sh "echo build"
        sh "cd ${env.WORKSPACE}/Backend && chmod +x ./gradlew && ./gradlew build"
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

    stage('Remove Docker container') {
      steps {
        script {
          try {
            docker.image("my-springboot-image:${env.BUILD_NUMBER}").container('springboot').stop()
            docker.image("my-springboot-image:${env.BUILD_NUMBER}").container('springboot').remove(force: true)
          } catch (err) {
            echo "Failed to remove the container"
          }
        }
      }
    } 
    
    stage('Run Docker container') {
      steps {
        script {
          docker.image("my-springboot-image:${env.BUILD_NUMBER}").run("--network persona-network --name springboot -p 8080:8080")
        }
      }
    }
  }
}
