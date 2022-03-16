# crypto-data-store

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

I want to add two steps at the end of the gradle build one to commit to the remote dev branch, the other to deploy to docker.  
If the docker deploy step fails, the commit should be rolled back from remote.  My garbage mishmash of docker scripts are in the deploy folder.  

You need to run the docker script to setup dev mongodb in order the run this app, an example jar run args are like this:

-Dspring.profiles.active=dev
-Djvm.password=Sunny Side Technologies: ${jvm.password}

### Lints and fixes files

First fix deploy script
Second refactor your your shitty code
Add Trade data models/deserializers/publisers/listeners/jobs
Add nightly gradle cache clean script for workspace maintentence
Draft up Orderbook state maintence algorithm test and performance metrics (1st test in CI/CD pipeline)
