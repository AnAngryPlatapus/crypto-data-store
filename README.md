# crypto-data-store


#### Follow Latest Build Updates at: https://twitter.com/TheMarcyMuppet 

## Welcome
This is primarily a kotlin jvm project, a beautiful condiut from meaningless data to glorious information. 
Its not rocket science.
Its real-time crypto data, streamed right into a brain of your choosing. 

## Architecture

Dependency management and kotlin compiler are handled by gradle "*.gralde.kts"

Spring Boot is used for: 
  async realtime processing (Flux and Mono interfaces)
  database orm abstractions (ReactiveMongoRepository interface)
  properties and dependency injection

Backend Mongo Models are defined in kotlin data classes.

Quartz jobs for websocket connections are spun up on startup, the current logic isn't working.  
  1. The connections sometimes close and we should re-subscribe. 
  2. Also need to find out the root cause of the connection being closed

  requirements for a better job manager: 
    * needs handy methods for gathering job metadata 
    * should have an out of the box extendable ui for seeing status
 
 Docker manages the environments. Some scripts for the engine and databases are in the project. CI/CD is incomplete. 
  * need to bootstrap a new environment on port and remove existing with a single command
  * before uat is deployed in a hosted fashion the following modules need to be complete
    * front end webapp container, displays all relavent statistics in realtime, should probably bootstrap off of an existing job monitoring solution
        (kafka or spark ui maybe, open source, written in vue is preferable)
  
 private upon request: python notebooks that run predictive models
 
 ##TODO: The Rocket Surgery Operating Room
  1. Be able to customize the reported statistics
  2. Build liquidity workflows
  3. Achieve this with a typed DSL and natural lanagauge processing tools
    -- Early ideas are a an embedded kotlin jupyter notebook with open gpt code generation contexts
  
# Skip to dev builds if you have kotlin familiarity
It's just like java except the syntax is intelligent.  

"val" declared variables are immutable
"var" delcared variables can be updated

Everything is strongly typed, so be concious of the fact that the type needs to be known at compile time.  The compiler is fantastic at inferring types.  
You usually only have to specify types for method parameters.  
Sometimes I specify types anyway to help write something complicated like a wierd object transformation.  


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
