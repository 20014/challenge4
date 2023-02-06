pipeline {
    agent any
    tools { 
        maven 'Maven 3.8.7' 
        jdk 'jdk17' 
    }
     stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    echo "JAVA_HOME = ${JAVA_HOME}"
                ''' 
            }
        }
        stage('Check out') {
            steps {
              checkout([$class: 'GitSCM', 
                branches: [[name: '*/main']],
                doGenerateSubmoduleConfigurations: false,
                extensions: [[$class: 'CleanCheckout']],
                submoduleCfg: [], 
                userRemoteConfigs: [[url: 'https://github.com/20014/challenge4.git']]])
              sh "ls -ltr"
          }
        }

        stage ('Build') {
            steps {
                sh 'mvn package' 
            }
            post {
                success {
                    echo 'Build Sucess'  
                }
            }
        }
    }
}

