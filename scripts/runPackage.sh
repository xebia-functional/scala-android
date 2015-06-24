#!/bin/bash 
set -ev
sbt ";clean; android:package; android:package"
