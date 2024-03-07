node {
    ansiColor('xterm') {
    // some block
    }
    
    timestamps {
    // some block
    }
    stage('1 - Clean Report'){

        try{
            sh 'sudo rm -r /var/www/html/Suntory/Suntory-Prod-1/*'
        }catch (err){
			echo "No file to delete"
		}

    }

    stage('2 - Get Code'){
        git credentialsId: 'BitBucketServer', url: 'https://bitbucket.org/SAI-IT/suntory-web/src/master/'

    }

    stage('3 - Clean Maven'){
        sh 'mvn clean'
    }

    stage('4 - Run Test'){
		sh 'mvn verify -DBROWSER=$BROWSER -DENVIRONMENT=$ENVIRONMENT -Dcucumber.options="--tags \'$TAGSNAME\'"'

	}

    stage('5 - Generate Cucumber Report'){
        cucumber buildStatus: 'null', customCssFiles: '', customJsFiles: '', fileIncludePattern: '**/cucumber.json', sortingMethod: 'ALPHABETICAL'
    }

    stage('6 - Publish Cucumber Report'){
       sh 'sudo cp -r /var/lib/jenkins/jobs/Suntory/jobs/Suntory-Prod-Web-1/workspace/target/Cucumber-Report/cucumber-html-reports/ /var/www/html/Suntory/Suntory-Prod-1/'
    }
}