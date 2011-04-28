
=== Introduction ===

This artifact can be used together with the maven-jetty-plugin to run a maven portlet application with jetty, using the regular mvn jetty:run command. Documentation pages can be navigated through the pages list to the right.


=== Configuration ===

== System properties ==

The following system properties are set on the maven-jetty-plugin to configure the behaviour of the portlet rendering:

org.apache.pluto.embedded.portletIds 		Comma separated list of portlet ids as named in the portlet.xml file
org.apache.pluto.embedded.principalName 	The user principal to fake
org.apache.pluto.embedded.principalRoles	Comma separated list of user principal roles to fake

== Example pom.xml ==

Put the following in the pom.xml file of your Maven 2 project:


<profiles>
  <profile>
    <id>pluto-embedded</id>
    <build>
      <plugins>
        <plugin>
          <groupId>org.apache.portals.pluto</groupId>
          <artifactId>maven-pluto-plugin</artifactId>
          <version>2.0.2</version>
          <executions>
            <execution>
              <phase>generate-resources</phase>
              <goals>
                <goal>assemble</goal>
              </goals>
            </execution>
          </executions>
        </plugin>
        <plugin>
          <groupId>org.mortbay.jetty</groupId>
          <version>6.1.14</version>
          <artifactId>maven-jetty-plugin</artifactId>
          <configuration>
            <webXml>${project.build.directory}/pluto-resources/web.xml</webXml>
            <webAppConfig>
              <contextPath>/${project.artifactId}</contextPath>
              <defaultsDescriptor>/WEB-INF/jetty-pluto-web-default.xml</defaultsDescriptor>
            </webAppConfig>
            <systemProperties>
              <!-- The portlet ids can be a comma separated list if you want to run more than one
                   portlet in the portal
              -->
              <systemProperty>
                <name>org.apache.pluto.embedded.portletIds</name>
                <value>MyPortletAsDefinedInPortletXml,MyOtherPortletInTheSameApp</value>
              </systemProperty>
              <systemProperty>
                <name>org.apache.pluto.embedded.principalName</name>
                <value>theUserName</value>
              </systemProperty>
              <systemProperty>
                <name>org.apache.pluto.embedded.principalRoles</name>
                <value>role1,role2</value>
              </systemProperty>
            </systemProperties>
          </configuration>
        </plugin>
      </plugins>
    </build>
    <dependencies>
      <dependency>
        <groupId>com.bekk.boss</groupId>
        <artifactId>maven-jetty-pluto-embedded</artifactId>
        <version>2.0-SNAPSHOT</version>
      </dependency>
    </dependencies>
  </profile>
</profiles>

Substitute MyPortletAsDefinedInPortletXml (if you want to run more than one portlet, use a comma separated list of portlet ids) with the actual id of your portlet, as defined in portlet.xml

=== Running the portlet(s) ===

With all the configuration in place, you should be able to run 'mvn jetty:run -P pluto-embedded' to run the portlet. Open a browser and go to http://localhost:8080/artifactId/portal (subistute artifactId with the artifact id of your maven project) to view the portlet(s).



