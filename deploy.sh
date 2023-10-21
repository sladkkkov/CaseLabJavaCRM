echo "Stop container"
docker stop case-lab-java-crm
docker rm case-lab-java-crm
docker image rm {dockerhub username}/{dockerhub repo name}
echo "Pull image"
docker pull {dockerhub username}/{dockerhub repo name}
echo "Start frontend container"
docker run -p 80:80 --name frontend -d {dockerhub username}/{dockerhub repo name}
echo "Finish deploying!"