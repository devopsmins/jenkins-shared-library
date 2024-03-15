def call() {
    ansiColor ('xterm') {
    node ('workstation' ) {
        common.codeCheckout()
        stage ('Terrafrom Apply') {
            sh 'terraform init'
            sh 'terraform apply -auto-approve '
        }
    }
    }
}