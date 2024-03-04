# Deploy apollo to the test server
set -e
./gradlew clean build
scp bukkit/build/libs/apollo-bukkit-1.1.1-SNAPSHOT.jar ubuntu@build.moonsworth.com:/home/ubuntu/apollo/plugins/
scp bukkit-example/build/libs/apollo-bukkit-example-1.1.1-SNAPSHOT.jar ubuntu@build.moonsworth.com:/home/ubuntu/apollo/plugins/
