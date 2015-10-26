# HfutEduLib
A JAR package for getting data from the Educational Administrator System in HFUT

### Dependency JARs

+ httpclient
+ httpclient-cache
+ httpcore
+ httpmime
+ dom4j


### Quick Start

+ Install
```bash
$ mvn install
```
+ Test
```bash
$ mvn test
```

+ Pack
```bash
$ mvn package
```

+ Usage
```java
HfutEduLib edu = new HfutEduLib("Your schoolId", "password");
edu.getStudentInfo();
...
```
