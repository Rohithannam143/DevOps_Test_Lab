pipeline {
  agent any
  environment {
    APP_NAME = 'student-feedback-portal'
    TOMCAT_WEBAPPS = '/opt/tomcat/webapps'
    APP_URL = 'http://localhost:8080/student-feedback-portal'
  }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Build') { steps { sh 'mvn -B clean compile' } }
    stage('Unit Test') { steps { sh 'mvn -B test' } }
    stage('Package WAR') { steps { sh 'mvn -B package' } }
    stage('Archive Artifact') { steps { archiveArtifacts artifacts: 'target/*.war', fingerprint: true } }
    stage('Deploy to Tomcat') {
      steps {
        sh '''set -e
          WAR="target/${APP_NAME}.war"
          test -f "$WAR"
          mkdir -p "$TOMCAT_WEBAPPS/rollback"
if [ -f "$TOMCAT_WEBAPPS/${APP_NAME}.war" ]; then cp "$TOMCAT_WEBAPPS/${APP_NAME}.war" "$TOMCAT_WEBAPPS/rollback/${APP_NAME}.war.prev"; fi
cp "$WAR" "$TOMCAT_WEBAPPS/${APP_NAME}.war"
          sleep 5
        '''
      }
    }
    stage('Health Check') {
      steps { sh 'curl --fail --silent --show-error "$APP_URL/health" | grep -q "UP"' }
    }
  }
  post {
    success { echo 'FeedbackHub deployment verified.' }
    failure {
      sh '''
        if [ -f "$TOMCAT_WEBAPPS/rollback/${APP_NAME}.war.prev" ]; then
          cp "$TOMCAT_WEBAPPS/rollback/${APP_NAME}.war.prev" "$TOMCAT_WEBAPPS/${APP_NAME}.war"
        fi
      '''
      echo 'Pipeline failed; previous WAR was restored when available.'
    }
    always { echo 'CI/CD stages: checkout -> compile -> test -> package -> archive -> deploy -> health.' }
  }
}
