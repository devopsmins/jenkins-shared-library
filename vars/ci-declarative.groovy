def call(){
pipeline {
    agent {
        node { label 'workstation' }
    }

    stages {

        stage( 'Compile' ) {
            expression { BRANCH_NAME == "main" }
            steps {
                echo 'Compile'
            }
        }

        stage( 'Test Case' ) {
            steps {
                echo 'Test Case'
            }
        }

        stage( 'Build' ) {
            steps {
                echo 'Build'
            }
        }

        stage( 'Release App' ) {
            steps {
                echo 'Release'
            }
        }

    }
}
}
