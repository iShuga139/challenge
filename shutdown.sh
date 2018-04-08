#!/bin/bash

if [ -f ./rt-statistic/.pids.data ]; then
  echo ==================
  echo SHUTDOWN PROCESS 1
  echo ==================

  while read line; do
    kill -15 $line    
  done < ./rt-statistic/.pids.data

  rm ./rt-statistic/.pids.data
fi

if [ -f ./transactions/.pids.data ]; then
  echo ==================
  echo SHUTDOWN PROCESS 2
  echo ==================

  while read line; do
    kill -15 $line    
  done < ./transactions/.pids.data

  rm ./transactions/.pids.data
fi
