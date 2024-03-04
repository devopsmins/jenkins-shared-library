def call(){
    node ('workstation' ) {
        if(env.TAG_NAME ==~ ".*") {
            env.branchname == env.TAG_NAME
        } else {
            env.branchname == env.BRANCH_NAME
        }
        stage( 'Code Checkout' ) {
            git branch: 'main', url: 'https://github.com/expenseapp-v1/expense-backend.git'
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