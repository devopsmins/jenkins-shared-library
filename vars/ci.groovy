def AWS_SSM_PARAM(param_name) {
    def OUTPUT = sh ( script: " aws ssm get-parameter --name ${param_name}  --with-decryption --query 'Parameter.Value' --output text", returnStdout: true).trim()
    return(OUTPUT)
}
def call(){
    node ('workstation' ) {
        common.codeCheckout()

        if(app_type == "nodejs") {
            stage( 'Download Dependencies' ) {}
            sh 'npm install'
        }



        if(env.JOB_BASE_NAME ==~ "PR.*") {
            sh 'echo PR'
            stage( 'Test Case' ) {}
            stage( 'Code quality' ) {
             env.SONAR_TOKEN = AWS_SSM_PARAM('sonar.token')
                wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: "${SONAR_TOKEN}", var: 'SONAR_TOKEN']]]) {
                sh 'sonar-scanner -Dsonar.host.url=http://172.31.3.189:9000 -Dsonar.login=${SONAR_TOKEN} -Dsonar.projectKey=${repo_name} -Dsonar.qualitygate.wait=true -Dsonar.exclusions=node_modules/**'
            }
            }
        }else if(env.BRANCH_NAME == "main") {
            sh 'echo main'
            stage( 'Build' ) {}
        } else if(env.TAG_NAME ==~ ".*") {
            sh 'echo TAG'

            stage( 'Build' ) {
                sh 'zip -r ${repo_name}-${TAG_NAME}.zip *'
            }
            stage( 'Release App' ) {
                env.ARTIFACTORY_PASSWORD = AWS_SSM_PARAM('artifactory.password')
                wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: "${ARTIFACTORY_PASSWORD}", var: 'PASSWORD']]]) {
                sh 'curl -sSf -u "admin:${ARTIFACTORY_PASSWORD}" -X PUT  -T ${repo_name}-${TAG_NAME}.zip http://artifactory.devopsmins.online:8081/artifactory/${repo_name}/${repo_name}-${TAG_NAME}.zip'
            }
        }
        } else {
            sh 'echo branch'
            stage( 'Test Case' ) {}
            //sh 'npm test'
        }


    }

}