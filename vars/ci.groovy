def call(){
    node ('workstation' ) {

        sh "find . | sed -e '1d' |xargs rm -rf"

        if(env.TAG_NAME ==~ ".*") {
            env.branch_name == "refs/tags/${env.TAG_NAME}"
        } else {
            if(env.BRANCH_NAME ==~ "PR-.*" ) {
                env.branch_name == "${env.CHANGE_BRANCH}"
            } else {
                env.branch_name == "${env.BRANCH_NAME}"
            }
        }
        stage( 'Code Checkout' ) {
            git branch: "${env.branch_name}", url: 'https://github.com/devopsmins/expense-backend.git'
           //checkout scmGit(
                    //branches: [[name: "${branch_name}"]],
                    //userRemoteConfigs: [[url: "https://github.com/devopsmins/expense-backend"]]
          // )
            sh 'cat Jenkinsfile'
        }


        stage( 'Compile' ) {}

        if(env.BRANCH_NAME == "main")
        {
            stage( 'Build' ) {}
        } else if(env.BRANCH_NAME ==~ "PR.*") {

            stage( 'Test Case' ) {}
            stage( 'Integrtaion Test Case' ) {}
        } else if(env.TAG_NAME ==~ ".*") {

            stage( 'Build' ) {}
            stage( 'Release App' ) {}
        }
        else {
            stage( 'Test Case' ) {}
        }
        //stage( 'Compile' ) {}

        //stage( 'Test Case' ) {}

        //stage( 'Build' ) {}

        //stage( 'Release App' ) {}

    }

}