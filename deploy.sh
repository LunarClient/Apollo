# Deploy apollo to the test server
set -e
./gradlew :apollo-bukkit:shadowJar
scp bukkit/build/libs/apollo-bukkit-all.jar ubuntu@build.moonsworth.com:/home/ubuntu/apollo/plugins/

./gradlew :apollo-bukkit:jar_v1_18
scp bukkit/build/libs/apollo-bukkit-v1_18.jar ubuntu@build.moonsworth.com:/home/ubuntu/apollo/mods/
