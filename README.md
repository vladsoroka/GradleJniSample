# Java&Native HelloWorld Gradle Project 

Based on [new Gradle C++ plugins](https://docs.gradle.org/current/userguide/building_cpp_projects.html) which is recommended to use over [the older Native plugins](https://docs.gradle.org/current/userguide/native_software.html)

Note, there is currently no "C application" plugin, so this build uses the "C++ application" plugin and then reconfigures it to build C instead.

## Build & Run

* Use [IDEA](https://www.jetbrains.com/idea/), open and run `HelloTest`
* Command line
    ```
    ./gradlew test 
    ```