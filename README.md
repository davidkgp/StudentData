# Command to run container
docker run -d -p 8091:<hostport> -t davidkgp/student-data:1.0-SNAPSHOT

# To access docker application
## Using boot2docker 
Check the ip when boot2docker starts
To see the app, you must visit the IP address in DOCKER_HOST instead of localhost. 
In this case, http://DOCKER_HOST:hostport/school/students, the public facing IP of the VM.

## Using docker toolbox or docker cli
In this case, http://localhost:hostport/school/students, the public facing IP of the VM.
