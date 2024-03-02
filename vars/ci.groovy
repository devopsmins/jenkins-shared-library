def call(){
    node ('workstation' ) {
        stage( 'Code Checkout' ) {}
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