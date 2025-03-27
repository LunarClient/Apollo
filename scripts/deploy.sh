#!/bin/bash
set -eo pipefail

VERSION="1.1.8-SNAPSHOT"
REMOTE_USER="ubuntu"
REMOTE_HOST="147.135.8.94"

usage() {
  echo "Usage: $0 <server> [api|json|proto]"
  echo "Available servers: apollo, test"
  echo "Optional module (for bukkit servers only): api (default), json, proto"
  exit 1
}

validate_server() {
  case "$1" in
    apollo|test) ;;
    *)
      echo "Unknown server: $1"
      usage
      ;;
  esac
}

validate_module() {
  case "$1" in
    api|json|proto) ;;
    *)
      echo "Unknown module: $1"
      usage
      ;;
  esac
}

deploy_files() {
  local server="$1"
  shift
  local -a files=("${@:1:$#-1}")
  local destination="${!#}"

  if [ -n "$MODULE" ]; then
    echo "Deploying to $server server (module: $MODULE)..."
  else
    echo "Deploying to $server server..."
  fi

  for file in "${files[@]}"; do
    if [ ! -f "$file" ]; then
      echo "Error: File $file not found!"
      exit 1
    fi
    scp "$file" "$REMOTE_USER@$REMOTE_HOST:$destination"
  done
}


# Main script execution
[ $# -lt 1 ] && usage

SERVER="$1"

# Only assign an example module if server is apollo or test
if [[ "$SERVER" == "apollo" || "$SERVER" == "test" ]]; then
  MODULE="${2:-api}"  # Default to "api" if not provided
  validate_module "$MODULE"
else
  MODULE=""
fi

validate_server "$SERVER"

echo "Building project..."
./gradlew clean build

declare -a files_to_copy
declare destination_path

case "$SERVER" in
  apollo)
    files_to_copy=(
      "bukkit/build/libs/apollo-bukkit-${VERSION}.jar"
      "bukkit-example-${MODULE}/build/libs/apollo-bukkit-example-${MODULE}-${VERSION}.jar"
    )
    destination_path="/home/ubuntu/apollo/plugins/"
    ;;
  test)
    files_to_copy=(
      "bukkit/build/libs/apollo-bukkit-${VERSION}.jar"
      "bukkit-example-${MODULE}/build/libs/apollo-bukkit-example-${MODULE}-${VERSION}.jar"
    )
    destination_path="/home/ubuntu/lctest/plugins/"
    ;;
esac

deploy_files "$SERVER" "${files_to_copy[@]}" "$destination_path"

echo "Deployment to $SERVER completed successfully."
