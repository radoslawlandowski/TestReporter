0. Install Vagrant
1. Setup the VM by running in console: "vagrant up"
2. Connect to the VM by: "vagrant ssh" (now you are remotely connected to the VM)
3. Run "cd /vagrant" ('/vagrant' is the folder that is synchronized with the repository folder on your local machine)
4. run "mvn install"
5. run "sudo java -jar target/testreporter-1.0.jar server config.yml"
6. application is hosted at port defined in config.yml file

The Postman's Web API calls collection is stored in TestReporter.postman_collection.json. Import it to your Postman to check possible requests.