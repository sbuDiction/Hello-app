#!/bin/bash

#       .------------------------- constant part!
RED='\033[0;31m'
WH='\033[1;35m'
NC='\033[0m'
YL='\033[1;33m'
BL='\033[0;34m'

read -r -p "Enter your commit message: " commit_message

function git_check() {

  echo -e "${RED}[information]${NC}: ${YL}checking for changes made in this directory\n${NC}"

  git status
}

function git_add() {

  echo -e "${RED}[information]${NC}: ${YL}adding all changes to github\n${NC}"

  git add -A

}

function git_commit() {

  echo -e "${RED}[information]${NC}: ${YL}Now committing all changes to github.\n${NC}"

  read -r -p "Which branch are you committing?: " branch_name
  git commit -m "${commit_message}"

  git push origin "${branch_name}"

}

function main() {
  git_check
  git_add
  git_commit

}
main
