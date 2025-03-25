# Deploy apollo to the test server
set -e
./gradlew clean build
scp bukkit/build/libs/apollo-bukkit-1.1.7.jar ubuntu@147.135.8.94:/home/ubuntu/apollo/plugins/
scp bukkit-example/build/libs/apollo-bukkit-example-1.1.7.jar ubuntu@147.135.8.94:/home/ubuntu/apollo/plugins/
scp bukkit/build/libs/apollo-bukkit-1.1.7.jar ubuntu@147.135.8.94:/home/ubuntu/lctest/plugins/
scp bukkit-example/build/libs/apollo-bukkit-example-1.1.7.jar ubuntu@147.135.8.94:/home/ubuntu/lctest/plugins/
