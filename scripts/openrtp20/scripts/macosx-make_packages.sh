!/bin/bash

export ECLIPSE_VERSION="R-4.11-201903070500"
export DOWNLOAD_SITE="file://${HOME}/public_html/pub/eclipse/packages/"
export REPOSITORY="file://${HOME}/public_html/pub/eclipse/projects/2019-03,http://download.eclipse.org/releases/2019-03"
export LANGPACK_FILE_NAME="BabelLanguagePack-eclipse-ja_4.10.0.v20190126060001.zip"

export ECLIPSE_HOME=${HOME}/eclipse
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=/usr/lib/jvm/java-8-openjdk-amd64/bin:${PATH}
export OS=MACOS
export WORK_DIR=./work/macosx
export BIT=64

sh make_packages

