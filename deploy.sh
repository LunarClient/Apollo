# Deploy apollo to the test server
set -e
./gradlew clean build
scp bukkit/plugin/build/libs/apollo-bukkit-plugin-0.1.0-SNAPSHOT.jar ubuntu@build.moonsworth.com:/home/ubuntu/apollo/plugins/
