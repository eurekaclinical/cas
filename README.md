# Eureka! Clinical Central Authentication Service
Patched [JASIG (now Apereo) CAS](https://www.apereo.org/projects/cas/) server used for authentication by Eureka! Clinical projects.

## Version 3.0 development series
Latest release: [![Latest release](https://maven-badges.herokuapp.com/maven-central/org.eurekaclinical/cas-server/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.eurekaclinical/cas-server)

The version 3.0 series has integrated password resets and password changes into this project (they previously were in Eureka! Clinical Analytics).

## Version history
### Version 2.0
Turned on the REST API access.

### Version 1.0.2
Initial version supported version 1 of Eureka! Clinical Analytics.

## What does it do?
It provides single sign on across all Eureka! Clinical microservices, thus allowing microservices to be composed into a single integrated application. It can provide its own screens for the user to enter credentials, which are checked against a local database provided by [eurekaclinical-user-service](https://github.com/eurekaclinical/eurekaclinical-user-service) or an LDAP server. Alternatively, it can delegate that function to an OAuth or other CAS server.

This project depends on version 3.5.3 of CAS server, which implements support for versions 1 and 2 of the CAS protocol.

This project does not provide user authorization. Eureka! Clinical microservices are responsible for providing their own authorization.

## Building it
The project uses the maven build tool. Typically, you build it by invoking `mvn clean install` at the command line. For simple file changes, not additions or deletions, you can usually use `mvn install`. See https://github.com/eurekaclinical/dev-wiki/wiki/Building-Eureka!-Clinical-projects for more details.

## Maven dependency
```
<dependency>
    <groupId>org.eurekaclinical</groupId>
    <artifactId>cas-server</artifactId>
    <version>version</version>
</dependency>
```

## Getting help
Feel free to contact us at help@eurekaclinical.org.

