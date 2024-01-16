# Deploy apollo to the test server
set -e
./gradlew clean build
scp bukkit/build/libs/apollo-bukkit-1.0.8-evnt-SNAPSHOT.jar ubuntu@build.moonsworth.com:/home/ubuntu/apollo-evnt/plugins/
scp bukkit-example/build/libs/apollo-bukkit-example-1.0.8-evnt-SNAPSHOT.jar ubuntu@build.moonsworth.com:/home/ubuntu/apollo-evnt/plugins/
