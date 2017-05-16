# Eureka! Clinical Central Authentication Service (CAS)
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

This project depends on [version 3.5.3](https://github.com/apereo/cas/releases/tag/v3.5.3) of CAS server, which implements support for versions 1 and 2 of the [CAS protocol](https://apereo.github.io/cas/5.0.x/protocol/CAS-Protocol.html).

This project does not provide user authorization. Eureka! Clinical microservices are responsible for providing their own authorization.

## Build requirements
* [Oracle Java JDK 8](http://www.oracle.com/technetwork/java/javase/overview/index.html)
* [Maven 3.2.5 or greater](https://maven.apache.org)

## Runtime requirements
* [Oracle Java JRE 8](http://www.oracle.com/technetwork/java/javase/overview/index.html)
* [Tomcat 7](https://tomcat.apache.org)
* Also running [eurekaclinical-user-service](https://github.com/eurekaclinical/eurekaclinical-user-service) in order for local authentication to work

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

## Developer documentation
* [Javadoc for latest development release](http://javadoc.io/doc/org.eurekaclinical/cas-server) [![Javadocs](http://javadoc.io/badge/org.eurekaclinical/cas-server.svg)](http://javadoc.io/doc/org.eurekaclinical/cas-server)
* [JASIG CAS wiki](https://wiki.jasig.org/display/CASUM/Home)

## Configuration
Eureka! Clinical CAS is configured via a properties file located at `/etc/eureka/cas.properties`. In addition to the properties described in the [JASIG CAS documentation](https://wiki.jasig.org/display/casum/configuring), it supports the following properties:
* `eureka.authHandlers`: comma-separated list of handlers for checking credentials entered on the built-in login screen, which may be `jdbc` (check with eurekaclinical-user-service) or `ldap` (check with a specified LDAP directory); default is `jdbc`.
* `eureka.ldap.uid.attribute`: for specifying the UID attribute for an LDAP directory; default is `cn`.
* `eureka.ldap.email.attribute1`: for specifying the email attribute for an LDAP directory; default is `mail`.
* `eureka.ldap.firstName.attribute1`: for specifying the first name attribute for an LDAP directory; default is `firstName`.
* `eureka.ldap.lastName.attribute1`: for specifying the last name attribute for an LDAP directory; default is `lastName`.
* `eureka.ldap.title.attribute1`: for specifying the title attribute for an LDAP directory; default is `title`.
* `eureka.ldap.department.attribute1`: for specifying the department attribute for an LDAP directory; default is `ou`.
* `eureka.ldap.organization.attribute1`: for specifying the organization attribute for an LDAP directory; default is `o`.

A typical `cas.properties` file looks like the following:
```
server.name=https://hostname.running.cas
server.prefix=${server.name}/cas-server
host.name=hostname.running.cas
eureka.authHandlers=jdbc

# Whitelist the services that may access this CAS server.
cas.services.filter=https://(hostname1\\.edu|hostname2\\.edu|localhost).*
```

## Getting help
Feel free to contact us at help@eurekaclinical.org.

