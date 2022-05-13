# crypto-data-store


#### Follow Latest Build Updates at: https://twitter.com/TheMarcyMuppet 

## Project setup
Okay tom, this is kotlin jvm project, its not rocket science.  It's just like java except the syntax doesn't make me want to kill myself.  

"val" declared variables are immutable
"var" delcared variables can be updated

Everything is strongly typed, so be concious of the fact that the type needs to be known at compile time.  The compiler is fantastic at inferring types.  You usually only have to specify types for method parameters.  Sometimes I specify types anyway to help write something complicated like a wierd object transformation.  

Don't fuck with the reactive streams, I'll explain why I think this is the best for now.  Ultimately I want to swap quartz with kafka and horizontally scale the stream processing.   


### Compiles and hot-reloads for development

It's a gradle project, so if your like me and exist as a shill for jetbrains software, just shell out the $150 a year and import the project.
But your a cheap bastard, so just add java to your path, download gradle and add that to your path and run "gradle build" 

### Compiles and minifies for production

You need to run the docker script to setup dev mongodb in order the run this app, an example jar run args are like this:

-Dspring.profiles.active=dev
-Djvm.password=Sunny Side Technologies: ${jvm.password}

There is now a docker file! But the container will randomly close the Gemini socket connection (auto reconnect given kafka topic health?)

### Lints and fixes files

refactor your your shitty code

In Relative Order of Task Priority
===================================
finish Linear model baseline
finish Neural model baseline
add K-fold cross validation to test baseline
add scheduled test result publishing at intervals
create public dashboard for scheduled notebook run outputs

add realtime system health to dashboard
fix Docker websocket issue
Automate entire CI/CD pipeline 
