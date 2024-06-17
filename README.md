# 📌 사용하는 핵심 Dependency

> 참고: `spring-boot-dependencies BOM (ver.3.3.0)` 을 사용해서 버전을 관리합니다

- spring-webmvc
- thymeleaf
- spring-data-jdbc
- postgresql

<br><br>

# 📌 실행 전 주의할 사항들

## 1. JDBC 설정
placeholder.properties 에 JDBC 설정값들을 넣은 상태입니다. <br/>
자신의 상태에 맞게 편집해주시기 바랍니다!

<br>

## 2. spring boot 3 호환 Java, Tomcat version

 `spring-boot-dependencies BOM (ver.3.3.0)` 을 사용하기 때문에,<br>
 이에 호환되는 Java, Tomcat 버전을 사용해야 합니다.<br>
`Java` 는 `ver.17` 이상이면 되고, 톰캣은  `ver.10.1.x` 을 사용해야 합니다.<br>
이 프로젝트에서는 `Java 21` 와 `tomcat 10.1.23` 을 사용했습니다.<br/>
실습하시려는 분들 모두 버전 유의해서 프로젝트를 구동하시기 바랍니다.

<br><br>

# 📌  pom.xml 버전 관리
사용되는 모든 의존성 라이브러리들은 spring-boot 에서 제공하는 BOM 을 사용합니다.<br/>
수동적인 버전 기입은 추후에 CVE 문제를 고치는데 시간이 많이 걸릴듯하여 BOM 을 사용했습니다.<br/>
여기서 사용되는 BOM 은 spring-boot 3.3.0 에서 사용되는 것과 동일한 BOM 입니다.<br/>



<br><br>

# 📌 how to reload 


## templateResolver 빈 설정에서 cacheable=false 설정

```java
@Bean
public SpringResourceTemplateResolver templateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setPrefix("/WEB-INF/templates/");
    resolver.setSuffix(".html");
    resolver.setCacheable(false); // add this line
    return resolver;
}
```

## idea HotSwap 기능사용

이후에 `ctrl + F10` 을 누르면 어떤창이 뜨는데
`Update classes and resources` 를 선택하고 `OK` 를 클릭한다.

