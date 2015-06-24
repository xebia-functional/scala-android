#!/bin/bash 
set -ev
sbt ++$TRAVIS_SCALA_VERSION clean
sbt ++$TRAVIS_SCALA_VERSION android:package
sbt ++$TRAVIS_SCALA_VERSION android:package
