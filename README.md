# DiffConfig
This tool compares 2 configuration files (for example - defconfigs). Usual difftools fail while comparing 2 such files when configurations present in it are unordered, but this tool shines exactly in this situation. However, this is very basic as of now because this was made very quickly.  

### Requirements
- Full __Java 8__ support is required.  

### How to build
- A MakeFile has been supplied with this. To test quickly, use `make test`, which will generate __.class__ files. Use `make release` to generate __.jar__ file.  

### Usage
- To use with __.class__ files, use `java DiffConfig.class <path to config 1> <path to config 2>`.
- To use with __.jar__ file, use `java -jar diffconfig.jar <path to config 1> <path to config 2>`. You can find a release .jar build in the _Releases_ section.  

### But why in Java?
Coz I don't essentially know any other language :/
