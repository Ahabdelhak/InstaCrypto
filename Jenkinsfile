#! groovy

pipeline {
  agent any
  stages {
    stage('Setup') {
      steps {
        echo "Setup"
        // Install bundler in order to use fastlane
        sh "gem install bundler"
        // set the local path for bundles in vendor/bundle
        sh "bundle config set --local path 'vendor/bundle'"
        // install bundles if they're not installed
        sh "bundle check || bundle install --jobs=4 --retry=3"
      }
    }
    stage('Build') {
      steps {
        echo "Building"
        sh "bundle exec fastlane build"
      }
    }
    stage('Release to on 3rd party') {
      when {
        anyOf {
          branch "develop";
          branch "feature/*";
        }
      }
      steps {
        echo "Releasing to 3rd party"
        // Add your 3rd party fastlane command
        // sh "bundle exec fastlane release_third_party"
      }
    }
    stage('Release to Play Store ') {
      when {
        branch "master"
        // by using when we are checking for the branch and running oly if the branch matches
      }
      steps {
        echo "$STAGE_RELEASE_DESCRIPTION"
        sh "bundle exec fastlane release"
      }
    }
  }
}