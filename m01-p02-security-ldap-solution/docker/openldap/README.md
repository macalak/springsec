# Spinup OpenLDAP server locally
You can use Docker to run the OpenLDAP server. If you use Windows, install the [DockerToolbox](https://download.docker.com/win/stable/DockerToolbox.exe) first.
* to start OpenLDAP run following command from this directory:
  ```
  docker-compose up -d
  ```   
* to stop OpenLDAP MQ run:
  ```
  docker-compose stop
  ```
  
## Connect to OpenLDAP Server

http://192.168.99.100

----
* https://github.com/osixia/docker-openldap