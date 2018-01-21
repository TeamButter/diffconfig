test: DiffConfig.java  
	@javac DiffConfig.java
    
release: DiffConfig.java Manifest.MF
	@javac DiffConfig.java
	@jar -cvmf Manifest.MF diffconfig.jar *.class
    
clean:
	@rm -rf *.jar *.class
