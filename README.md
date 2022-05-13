# crypto-data-store


#### Follow Latest Build Updates at: https://twitter.com/TheMarcyMuppet 

## Project setup
This is primarily a kotlin jvm project, a beautiful condiut from meaningless data to glorious information. 
Its not rocket science.  

It's just like java except the syntax is intelligent.  

"val" declared variables are immutable
"var" delcared variables can be updated

Everything is strongly typed, so be concious of the fact that the type needs to be known at compile time.  The compiler is fantastic at inferring types.  
You usually only have to specify types for method parameters.  
Sometimes I specify types anyway to help write something complicated like a wierd object transformation.  

Webflux reactive stream help simply realtime processessing pipelines.  
Ultimately I want to swap quartz with kafka and try to understand the best way to scale compute instances.   

### Compiles and hot-reloads for development

It's a gradle project, so if your like me and exist as a shill for jetbrains software, just shell out the $150 a year and import the project.
But if your cheap, so just add java to your path, download gradle and add that to your path.  Then run "gradle build" 

### Compiles and minifies for production

Docker scripts are included for infra setup.

-Dspring.profiles.active=dev
-Djvm.password=Sunny Side Technologies: ${jvm.password}

There is now a docker file dev/uat/prod builds! But the container will randomly close the Gemini socket connection.
(auto reconnect given kafka topic health? these connections ultimately need to be monitored in real time)

### Lints and fixes files

refactor your your shitty code

In Relative Order of Task Priority
===================================
1. finish Linear model baseline
2. finish Neural model baseline
3. add K-fold cross validation to test baseline
4. add scheduled test result publishing at intervals. Include meta satistics to track change performance 
5. create public dashboard for scheduled notebook run outputs

-- Less Important --
1. add realtime system health to dashboard
2. fix Docker websocket issue
3. automate entire CI/CD pipeline 

Notebook models will stay private unless I get help with this repo
