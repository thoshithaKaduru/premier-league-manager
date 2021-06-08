## To Note: 
sbt.bat was renamed to sbt.txt so CMD can access it
PremierLeagueManagerTest.java was renamed to PremierLeagueManagerTest.txt when not used for testing to avoid play from compiling it

## Technology Versions Used in this Project

* [Node js: 14.15.3](https://nodejs.org)
* [Angular CLI: 11.0.5](https://cli.angular.io/)
* [Play Framework: 2.8.0](https://www.playframework.com/documentation/2.8.x/Home)



## Running Application

Navigate into folder w1714902 > app folder they open gitbash and type 
```
$ javac CommandLineApp.java
# after it compiles
$ java CommandLineApp  # this will invoke the command line application
```

Then w1714902 folder will open by the application
open git bash and type
```
$ sbt run   # this will invoke backend and GUI
```

## Running Tests
Navigate into folder w1714902 > app folder they open gitbash and type

Jar files used for Testing
* [JUnit v4.08.1](mvnrepository.com/artifact/junit/junit/4.8.1) (junit-4.8.1.jar) 
* [Hamcrest v2.2](mvnrepository.com/artifact/org.hamcrest/hamcrest/2.2) (hamcrest-2.2.jar) is used


**NOTE: Before Running Tests... Make sure that you delete saveClubs.ser and saveMatches.ser**
**And if you are running individual tests. Delete the files after each test**
The two files will be located in /app/ directory


use the following two commands to run tests (make sure you type it correctly)

```
# to compile the PremierLeagueManagerTest.jave
$ javac -cp "junit-4.8.1.jar; hamcrest-2.2.jar;." PremierLeagueManagerTest.java

# to run the PremierLeagueMangerTest.class file
$ java -cp "junit-4.8.1.jar; hamcrest-2.2.jar;." org.junit.runner.JUnitCore PremierLeagueManagerTest 
```

## Directory Layout (Only Relavant Directories and Files)


```
├── /app/                                 # The backend source (controllers, models, services)
│     └── /controllers/                   # Backend controllers
│           └── FrontendController.scala  # Asset controller wrapper serving frontend assets and artifacts
├── /conf/                                # Configurations files and other non-compiled resources (on classpath)
│     ├── application.conf                # Play application configuratiion file.
│     ├── logback.xml                     # Logging configuration
│     └── routes                          # Routes definition file
├── /logs/                                # Log directory
│     └── application.log                 # Application log file
├── /project/                             # Contains project build configuration and plugins
│     ├── FrontendCommands.scala          # Frontend build command mapping configuration
│     ├── FrontendRunHook.scala           # Forntend build PlayRunHook (trigger frontend serve on sbt run)
│     ├── build.properties                # Marker for sbt project
│     └── plugins.sbt                     # SBT plugins declaration
├── /public/                              # Frontend build artifacts will be copied to this directory
├── /target/                              # Play project build artifact directory
│     ├── /universal/                     # Application packaging
│     └── /web/                           # Compiled web assets
├── /test/                                # Contains unit tests of backend sources
├── /ui/                                  # React frontend source (based on Create React App)
│     ├── /e2e/                           # End to end tests folder
│     ├── /node_modules/                  # 3rd-party frontend libraries and utilities
│     ├── /src/                           # The frontend source code (modules, componensts, models, directives, services etc.) of the application
│     │     ├── karma.conf.js             # Karma configuration file
│     │     └── proxy.conf.json           # UI proxy configuration      
│     ├── .angular.json                   # Angular CLI configuration
│     ├── .editorconfig                   # Define and maintain consistent coding styles between different editors and IDEs
│     ├── .gitignore                      # Contains ui files to be ignored when pushing to git
│     ├── package.json                    # NPM package configuration.
│     ├── README.md                       # Contains all user guide details for the ui
│     ├── tsconfig.json                   # Contains typescript compiler options
│     └── tslint.json                     # Lint rules for the ui
├── .gitignore                            # Contains files to be ignored when pushing to git
├── build.sbt                             # Play application SBT configuration
├── LICENSE                               # License Agreement file
├── README.md                             # Application user guide
└── ui-build.sbt                          # SBT command hooks associated with frontend npm scripts 
```
