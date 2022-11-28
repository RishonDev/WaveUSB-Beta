#!/bin/zsh

while [[ $# -gt 0 ]]; do
  case $1 in
    -f|--format)
      diskutil formatDisk "$2"
      shift # past argument
      shift # past value
      ;;
    -fd|--formatDisk)
    diskutil formatVolume "$2"
    ;;

  esac
done