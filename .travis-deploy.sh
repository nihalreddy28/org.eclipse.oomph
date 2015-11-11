#!/bin/bash

function error_exit
{
  echo -e "\e[01;31m$1\e[00m" 1>&2
  exit 1
}

if [ "$TRAVIS_PULL_REQUEST" == "false" ] 
then

   if [[ "$TRAVIS_BRANCH" == "master" || "$TRAVIS_BRANCH" == "peterkir" ]]
   then
      echo -e "Starting to deploy to gh-pages - updating $TRAVIS_BRANCH\n"
    
      # create and cd into temporary deployment work directory
      mkdir deployment-work
      cd deployment-work
      
      # setup git and clone from gh-pages branch
      git config --global user.email "travis-deployer@klib.io"
      git config --global user.name "Travis Deployer"
      git clone --quiet --branch=gh-pages https://pekirsc:${GH_TOKEN}@github.com/peterkir/org.eclipse.oomph.git . > /dev/null 2>&1 || error_exit "Error cloning gh-pages"
    
      RESULTDIR=./$TRAVIS_BRANCH
      mkdir -p $RESULTDIR
    
      # clean the repository directory, then copy the build result into it
      git rm -rf $RESULTDIR/repo/*
      git rm -rf $RESULTDIR/products/*
      
      ls -laR $HOME/.m2/repository > $RESULTDIR/mavenRepo.txt
      
      mkdir -p $RESULTDIR/repo/installer
      cp -rf ../products/org.eclipse.oomph.setup.installer.product/target/repository/* $RESULTDIR/repo/installer

      mkdir -p $RESULTDIR/repo/oomph
      cp -rf ../sites/org.eclipse.oomph.site/target/repository/* $RESULTDIR/repo/oomph

      mkdir $RESULTDIR/products
      cp ../products/org.eclipse.oomph.setup.installer.product/target/products/*.zip $RESULTDIR/products
      
      # add, commit and push files
      git add -f .
      git commit -m "[ci skip] Deploy Travis build #$TRAVIS_BUILD_NUMBER to gh-pages"
      git push -fq origin gh-pages > /dev/null 2>&1 || error_exit "Error uploading the build result to gh-pages"
    
      # go back to the directory where we started
      cd ..
      
      echo -e "Done with deployment to gh-pages\n"

  fi
fi
