pipeline {
    // Define the agent where the pipeline will run. 'any' means it can run on any available agent.
    agent any
environment {
    imagename="mypipelineimage"
    contaniername="mypipelinecontainer"
}
    // Define the stages of the pipeline.
    stages {
        stage('Checkout') {
            steps {
                // Print a message to the console log.
                echo 'Fetching code from GitHub'

                // Use the Git plugin to fetch code from the specified GitHub repository and branch.
                git branch: 'main', url: 'https://github.com/batty1327/jenkins_docker.git'
            }
        }

        stage('Build') {
            steps {
                // Print a message to the console log.
                echo 'Building the Docker image'

                // Build a Docker image with the specified tag.
                sh "docker build . -t ${imagename}"

                // List Docker images to display the result of the build.
                sh "docker images"
            }
        }

        stage('Test') {
            steps {
                // Print a message to the console log.
                echo 'Testing'

                // Stop and remove a Docker container named 'mycontainer' if it exists.
                sh 'docker stop ${containername} || true'               # || means if either one is true then command is success
                sh 'docker rm ${containername} || true'                 # ||: This is the logical OR operator in shell scripting
                                                                   # true: true is a shell command that always exits with a success status (exit code 0). In this case, it serves as a placeholder for a successful operation. If the docker rm command fails (for example, if 'mycontainer' doesn't exist), it will still exit successfully because of true.

                // Run a Docker container named 'mycontainer' from the built image, exposing port 80.
                sh 'docker run -d -p80:80 --name ${containername} ${imagename}'

                // Execute a command inside the running Docker container.
                sh 'docker exec ${containername} ls'
            }
        }

        stage('Deploy') {
            steps {
                // Print a message to the console log.
                echo 'Deploying on another server'

                // Log in to Docker Hub using a Docker Hub access token.
                sh 'docker login -u batty1327 -p <access token of dockerhub>

                // Tag the Docker image with a new name.
                sh 'docker tag mypipelineimg  batty1327/nginx_jenkins'

                // Push the Docker image to Docker Hub.
                sh 'docker push batty1327/nginx_jenkins'

                // Use SSH to connect to another server and perform Docker-related operations.
                sh '''
                ssh -i fcserver.pem -o StrictHostKeyChecking=no ubuntu@<IP of live server> << EOF
                sudo docker login -u batty1327 -p <access token of dockerhub>
                sudo docker stop mycontainer || true
                sudo docker rm mycontainer || true
                sudo docker pull batty1327/nginx_jenkins
                sudo docker run -d -p80:80 --name mycontainer batty1327/nginx_jenkins
                EOF
                '''
            }
        }
    }
}
