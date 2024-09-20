pipeline {
    agent none

    stages{
        stage('Compile et tests in Docker') {
            // agent{
            //     docker {
            //         image 'openjdk:17-alpine'
            //         args '-v $HOME/.m2:/root/.m2'
            //     } 
            // }
            agent{
                kubernetes{
                    inheritFrom "jdk17-agent"
                } 
            } 
            steps {
                echo 'Unit test et packaging'
                // sh "./mvnw -Dmaven.test.failure.ignore=true clean package"
                sh "./mvnw -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }  
                success {
                    archiveArtifacts '**/target/*.jar'
                    dir ('application/target'){
                        stash includes: '*.jar', name: 'app'
                    } 
                    
                }
            }
        }

        // stage ('push vers docker hub'){
        //     agent any
        //     steps{
        //         echo "push to docker"
        //         unstash 'app'
        //         script{
        //             def dockerImage = docker.build('lxlechat/multi-module',".")
        //             docker.withRegistry('https://registry.hub.docker.com',"dockerpwd"){
        //                 dockerImage.push "$BRANCH_NAME"
        //             }
                    
        //         } 
        //     } 
        // } 

     }
}
