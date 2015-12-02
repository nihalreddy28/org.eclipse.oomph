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
      git config --global user.email "nihalreddy28@gmail.com"
      git config --global user.name "Travis Deployer"
      git clone --quiet --branch=gh-pages https://nihalreddy28: b9df869b7e259c2575f5f7d558cc0c381cdc3f53@github.com/nihalreddy28/org.eclipse.oomph.git . > /dev/null 2>&1 || error_exit "Error cloning gh-pages"
    
	  BINTRAY_URL=https://bintray.com/artifact/download/peterkir/generic/$TRAVIS_BRANCH/$TRAVIS_BUILD_NUMBER/
	
	  echo -e "updating index.html with build details"
	  DATE=`date +%Y%m%d-%H%M%S`
	  sed -i -e 's|<h1>customized oomph build - last updated on .*\?<\/h1>|<h1>customized oomph build - last updated on '$DATE'</h1>|g' index.html
	  sed -i -e 's|<h2>Branch '$TRAVIS_BRANCH' - TravisCI build <a href="https://travis-ci.org/nihalreddy28/org.eclipse.oomph/builds/.*\?">#.*\?</a> - build on .*\?<\/h2>|<h2>Branch '$TRAVIS_BRANCH' - TravisCI build <a href="https://travis-ci.org/peterkir/org.eclipse.oomph/builds/'$TRAVIS_BUILD_ID'">#'$TRAVIS_BUILD_NUMBER'</a> - build on '$DATE'</h2>|g' index.html
	
      # add, commit and push files
      git add -f .
      git commit -m "[ci skip] Deploy Travis build #$TRAVIS_BUILD_NUMBER for branch $TRAVIS_BRANCH to gh-pages"
      git push -fq origin gh-pages > /dev/null 2>&1 || error_exit "Error uploading the build result to gh-pages"
    
      echo -e "Done with deployment to gh-pages\n"

  fi
fi
