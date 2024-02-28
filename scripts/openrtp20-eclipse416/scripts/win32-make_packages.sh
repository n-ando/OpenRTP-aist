#!/bin/bash

export ECLIPSE_VERSION="R-4.16-202006040540"
export DOWNLOAD_SITE="file://${HOME}/public_html/pub/eclipse/packages/"
export REPOSITORY="file://${HOME}/public_html/pub/eclipse/projects/2020-06,http://download.eclipse.org/releases/2020-06"
export LANGPACK_FILE_NAME="BabelLanguagePack-eclipse-ja_4.16.0.v20201010073410.zip"

export ECLIPSE_HOME=${HOME}/eclipse
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=/usr/lib/jvm/java-8-openjdk-amd64/bin:${PATH}
export OS=WIN32
export WORK_DIR=./work/win32
export BIT=64

sh make_packages

