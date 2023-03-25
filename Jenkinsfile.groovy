pipeline {
  agent {
    docker {
      image 'node:18.14.2-alpine'
      args '-p 3000:3000'
    }
  }

  stages {

    stage('init'){
      steps{
        sh "echo init"
      }
    }

    stage('Npm Build') {
      steps {
        sh '''
          cd ${env.WORKSPACE}/Frontend/persona
          mkdir -p .npm
          chown -R 113:119 .npm
          npm install
          npm run build
        '''
      }
    }

    stage('Gradle Build'){
      steps{
        sh "echo build"
        sh "cd ${env.WORKSPACE}/Backend && chmod +x ./gradlew && ./gradlew build"
      }
    }

    stage('Next.JS Image Build') {
      steps {
        script {
          def frondendDir = "${env.WORKSPACE}/Frontend/persona"
          def dockerfile = "${frontendDir}/Dockerfile"
          docker.build("persona-front-image:${env.BUILD_NUMBER})",   "-f ${dockerfile} ${frontendDir}")
        }
      }
    }

    stage('Springboot Image Build') {
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
            docker.container('springboot').stop()
            docker.container('springboot').remove(force: true)
            docker.container('frontend').stop()
            docker.container('frontend').remove(force: true)
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
          docker.image("persona-front-image:${env.BUILD_NUMBER}").run("--name frontend -p 3000:3000")
        }
      }
    }
  }
}
