
Vagrant.configure("2") do |config|

    config.vm.define "webapi" do |webapi|
        webapi.vm.box = "ubuntu/xenial64"
        webapi.vm.hostname = "webapi"
        webapi.vm.network "forwarded_port", guest: 1111, host: 1111
        webapi.vm.provision :shell, path: "./vagrant-scripts/webapi/install-webapi.sh"
    end

  config.vm.provision :shell, path: "./vagrant-scripts/install-java.sh"
  config.vm.provision :shell, path: "./vagrant-scripts/install-maven.sh"

end