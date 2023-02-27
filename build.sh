#!/bin/zsh
CURRENT_DIR=""
command -v javac java >/dev/null 2>&1 || { echo "This command requires the Java Compiler to be installed. Continue?[Y/n]"; exit 1; }
while [[ $# -gt 0 ]]; do
  case $1 in

    -r|--run)
      CURRENT_DIR=$(pwd)
      cd build/classes/src/main/java/
      java
      shift # past argument
      ;;
    -rj | --runJar)
    ;;
    -h | --help)
    ;;
    -rb |--rebuild)

    ;;
    -c| --cleanBuild)
    ;;

    *)
      POSITIONAL_ARGS+=("$1") # save positional arg
      shift # past argument
      ;;

  esac
done

set -- "${POSITIONAL_ARGS[@]}" # restore positional parameters
read -p "Are you going to Run this on your computer[Y/n]? " -n 1 -r
echo    # (optional) move to a new line
if [[ ! $REPLY =~ ^[Yy]$ ]]
then
    ./buildDependencies
fi

mkdir build/
mkdir -p build/classes/
read -p "Proceed with the build?[Y/n] " -n 1 -r
echo    # (optional) move to a new line
mkdir -p build/JAR/
javac -cp "libs/*" -d build/classes/src/main/java \
    src/main/java/waveUSB/Main.java \
    src/main/java/waveUSB/Constants.java \
    src/main/java/waveUSB/Definitions.java \
    src/main/java/waveUSB/core/Archives.java \
    src/main/java/waveUSB/core/Console.java \
    src/main/java/waveUSB/core/USB.java \
    src/main/java/waveUSB/core/usbFormats.java \
    src/main/java/waveUSB/core/SoftwareInfo.java \
    src/main/java/waveUSB/core/Notification.java \
    src/main/java/waveUSB/core/GUI/Output.java


read -p "Make a JAR file[Y/n]?: " -n 1 -r
echo    # (optional) move to a new line
if [[ ! $REPLY =~ ^[Yy]$ ]]
then
    [[ "$0" = "$BASH_SOURCE" ]] && exit 1 || return 1 # handle exits from shell or function but don't exit interactive shell
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
      src/main/java/waveUSB/core/usbFormats.class \
      src/main/java/waveUSB/core/SoftwareInfo.class \
      src/main/java/waveUSB/core/Notification.class \
      src/main/java/waveUSB/core/GUI/Output.class

      cd ..
      mv classes/WaveUSB.jar JAR/
    else exit 0;
fi
