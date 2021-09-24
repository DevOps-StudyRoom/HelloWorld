   pipeline {

    environment {
            registry = "skehdxhd/devopstest"
            registryCredential = 'dockerhub'
    }
    agent any
    stages {
        stage('branch check') {
            steps {
                git branch: "main",
                    url: 'https://github.com/DevOps-StudyRoom/helloworld.git'
            }
        }
        stage('gradle build') {
            steps {
                echo 'java -version' //
                sh 'chmod +x gradlew'
                sh '''#!/bin/bash
                     ./gradlew clean build --stacktrace
                     '''
            }
        }
        stage('build image') {
            steps {
                sh 'docker build -t core.hanium-devops.site/devops/helloworld:${BUILD_NUMBER} ./'
            }
        }
        stage('image push harbor') {
            steps {
                withDockerRegistry(credentialsId: 'harbor', url: 'https://core.hanium-devops.site') {
                    sh 'docker push core.hanium-devops.site/devops/helloworld:${BUILD_NUMBER}'
                }
            }
        }
        stage('infra Check') {
            steps {
                step([$class: 'WsCleanup'])
                git branch: "main",
                    credentialsId: 'infra',
                    url: 'git@github.com:DevOps-StudyRoom/Devops-infra.git'
            }
        }
        stage('CD Check') {
            steps {
                sshagent(credentials : ['infra']) {
                    sh '''#!/bin/bash
                        ls -al
                        cd helloworld
                        BEFORE=$(cat helloworld.yml | grep 'devops/helloworld:' | sed -e 's/^ *//g' -e 's/ *$//g')
                        sed -i "s@$BEFORE@image: core.hanium-devops.site/devops/helloworld:${BUILD_NUMBER}@g" helloworld.yml
                        git config user.name Reines98
                        git config user.email test@test.com
                        git add .
                        git commit -m "CICD pipline helloworld:${BUILD_NUMBER}"
                        git push origin main
                    '''
                }
            }
        }
    }
}