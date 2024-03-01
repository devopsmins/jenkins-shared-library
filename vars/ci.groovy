def call(){
    node ('workstation' ) {
        stage( 'Code Checkout' ) {}
        stage( 'Compile' ) {}

        if(BRANCH_NAME == "main")
        {
            stage( 'Build' ) {}
        } else if(BRANCH_NAME ==~ "PR.*") {

            stage( 'Test Case' ) {}
            stage( 'Integrtaion Test Case' ) {}
        } else if(TAG_NAME ==~ ".*") {

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