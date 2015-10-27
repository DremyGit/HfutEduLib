# HfutEduLib
A JAR package for getting data from the Educational Administrator System in HFUT

### Dependency JARs
This project is builded on Maven, so please install Maven before you want to **rebuild** it.
Also, you can import **the release JAR package** to your project to use it, but you should import these dependency jars **at the same time**:
+ httpclient
+ httpclient-cache
+ httpcore
+ httpmime
+ dom4j


### Quick Start

#### Maven command:

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

#### Usage
```java
HfutEduLib edu = new HfutEduLib("student id", "password");
edu.getStudentInfo();
...
```
