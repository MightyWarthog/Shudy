# Building Shudy

## Dependencies

- [Mayflower 2.1](lib/mayflower2.1.jar)
    - [LWJGL 2](http://legacy.lwjgl.org/)
- [ini4j](http://ini4j.sourceforge.net/)

## Directory Structure

The directory structure should look as follows:
```
    .
    ├── assets
    │   └── img
    │       ├── actors
    │       ├── buttons
    │       └── worlds
    ├── lib
    │   ├── ini4j-0.5.4.jar
    │   ├── lwjgl
    │   │   └── natives
    │   │       ├── linux
    │   │       │   ├── libjinput-linux.so
    │   │       │   ├── libjinput-linux64.so
    │   │       │   ├── liblwjgl.so
    │   │       │   ├── liblwjgl64.so
    │   │       │   ├── libopenal.so
    │   │       │   └── libopenal64.so
    │   │       └── win
    │   │           ├── OpenAL32.dll
    │   │           ├── OpenAL64.dll
    │   │           ├── jinput-dx8.dll
    │   │           ├── jinput-dx8_64.dll
    │   │           ├── jinput-raw.dll
    │   │           ├── jinput-raw_64.dll
    │   │           ├── lwjgl.dll
    │   │           └── lwjgl64.dll
    │   └── mayflower2.1.jar
    └── src
        ├── actors
        ├── engine
        ├── util
        └── worlds
```

Download the libraries listed above and put the `.jar`s into `lib/`, then extract the LWJGL natives like shown above.

### NOTE: If you are importing the project into Eclipse on Windows, make sure to set Mayflower's natives to `lib/lwjgl/win`!

### TODO
