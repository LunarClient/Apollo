# Deploy apollo to the test server
set -e
./gradlew build -Pmc='v1_18'
scp bukkit/common/build/libs/apollo-bukkit-common.jar ubuntu@build.moonsworth.com:/home/ubuntu/apollo/plugins/
scp bukkit/v1_18/build/libs/apollo-bukkit-v1_18.jar ubuntu@build.moonsworth.com:/home/ubuntu/apollo/mods/
