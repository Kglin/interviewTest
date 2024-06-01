# deploy docker
docker build -t interviewTest .

# deploy Kubernetes
kubectl apply -f service.yaml

#check
kubectl get deployments
kubectl get pods
kubectl get services