# Beam Module

## Overview
The beams module will allow you to create, edit and customize beacon beams.
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

## Integration
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
Fringilla urna porttitor rhoncus dolor purus non enim praesent elementum. Egestas sed tempus urna et. 
Suspendisse potenti nullam ac tortor. 
Lectus magna fringilla urna porttitor rhoncus.

```java
@Override
public void stopHostingWorld(RpcController controller, StopHostingWorldRequest request, RpcCallback<StopHostingWorldResponse> done) {
    GameUser user = ((SimpleRpcController) controller).requireGameUser();

    // Make sure they are actually hosting
    if (!hostedWorlds.containsKey(user.getUuid())) {
    // TODO
    return;
    }

    // Remove from state
    hostedWorlds.remove(user.getUuid());

    // And respond to the client
    done.run(StopHostingWorldResponse.getDefaultInstance());
```

### Client Response
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
Est velit egestas dui id ornare arcu odio ut.

```
git status
git add
git commit
```

## Public Use Cases
