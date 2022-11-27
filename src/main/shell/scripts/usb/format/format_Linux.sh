#!/bin/bash

while [[ $# -gt 0 ]]; do
  case $1 in
        	
    -e|--erase)
      	if EMPTY="TRUE"
      	then
      	    if WRITEMODE="TRUE"
      	    then
      		sudo badblocks -w -s -o error.log $2
      	    fi
      	    if WRITEMODE="FALSE"
      	    then
      	    sudo badblocks -s -o error.log $2 
      	    fi
      	fi
      	if EMPTY="FALSE"
      	then
      	    f3write $2
      	    f3read $2
      	fi    
      shift # past argument
      shift # past value
      ;;
    -*|--*)
      echo "Unknown option $1"
      exit 1
      ;;
    *)
      POSITIONAL_ARGS+=("$1") # save positional arg
      shift # past argument
      ;;
  esac
done

set -- "${POSITIONAL_ARGS[@]}" # restore positional parameters
