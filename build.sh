#!/bin/zsh

command -v javac java >/dev/null 2>&1 || { echo "This command requires the Java Compiler to be installed. Continue?[Y/n]"; exit 1; }

echo "Are you going to Run this on your computer[Y/n]?"
read -r
if REPLY="Y" || REPLY="y"
then
   ./buildDependencies
fi
mkdir build/
mkdir -p build/classes/

mkdir -p build/JAR/
javac -cp "libs/*" -d build/classes/src/main/java \
src/main/java/waveUSB/Main.java \
src/main/java/waveUSB/Constants.java \
src/main/java/waveUSB/Definitions.java \
src/main/java/waveUSB/core/Archives.java \
src/main/java/waveUSB/core/Console.java \
src/main/java/waveUSB/core/USB.java \
src/main/java/waveUSB/core/UsbFormats.java \
src/main/java/waveUSB/core/SoftwareInfo.java \
src/main/java/waveUSB/core/Notification.java \
src/main/java/waveUSB/core/GUI/Output.java

echo "Build a JAR File?[Y/n]"
read -r REP
if REP="Y" || REP="y"
then
  mkdir -p classes/src/main/java/
  mkdir -p classes/src/main/java/resources
  mkdir -p classes/src/main/shell
  mkdir -p classes/src/main/java/waveUSB/core
  mkdir -p build/classes/src/main/java/resources

  cp -r src/main/java/resources/images build/classes/src/main/java/resources
  cp -r src/main/java/resources/audio build/classes/src/main/java/resources
  cp -r src/main/java/resources/EULA build/classes/src/main/java/resources
  cp -r src/main/java/resources/utilities build/classes/src/main/java/resources
  cp -r src/main/shell build/classes/src/main/
  cp -r "src/main/META-INF" build/classes/src/main/
  cd build/classes/
  jar cmvf src/main/META-INF/MANIFEST.MF WaveUSB.jar src/main/java/waveUSB/Main.class \
  src/main/java/waveUSB/Constants.class \
  src/main/java/waveUSB/Definitions.class \
  src/main/java/waveUSB/core/Archives.class \
  src/main/java/waveUSB/core/Console.class \
  src/main/java/waveUSB/core/USB.class \
  src/main/java/waveUSB/core/UsbFormats.class \
  src/main/java/waveUSB/core/SoftwareInfo.class \
  src/main/java/waveUSB/core/Notification.class \
  src/main/java/waveUSB/core/GUI/Output.class
fi

if ! REP="Y" || ! REP="y"
then
  exit 0;
fi