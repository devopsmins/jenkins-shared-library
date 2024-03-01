def call(){
    node ('workstation' ) {
      sh 'env'
        if(BRANCH_NAME == "main")
        {
            stage( 'Code Checkout' ) {}
            stage( 'Compile' ) {}
            stage( 'Build' ) {}
        }
        //stage( 'Compile' ) {}

        //stage( 'Test Case' ) {}

        //stage( 'Build' ) {}

        //stage( 'Release App' ) {}

    }

}