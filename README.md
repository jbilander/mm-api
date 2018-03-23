<h4> Technologies used in this project: </h4>
<br />
<ul>
    <li>Jersey 2.26/ JAX-RS 2.1</li>
    <li>Json/Jackson 2.26 (annotations)</li>
    <li>Java SDK 9</li>
    <li>Tomcat 9</li>
    <li>JNDI DataSource (Commons DBCP 2 Connection Pool in Tomcat 9)</li>
    <li>MariaDb jdbc-driver 2.2.3 for MySQL</li>
    <li>Fast Socket-connection to MySQL (Unix-socket/Windows-Pipe), Requires App and Db on same machine!</li>
    <li>Pure JDBC with Prepared Statements and transaction-handling/rollbacks</li>
    <li>Simple logging with java.util.logging</li>
    <li>Maven 3</li>
    <li>Packaged with Maven 3 "package"-feature as a war (mm-api-1.0-SNAPSHOT.war)</li>
</ul>
<br /><br />
Caveats Java 9 SDK: <br /><br />

The java.xml.bind module is deprecated for removal in Java 9 (along with the other modules shared with Java EE) so expect these modules to not be included in the JDK some day.
To make the JAXB APIs available at runtime, specify the following VM option:

Windows, add this to bin/catalina.bat:<br /><br />
<pre>

set JDK_JAVA_OPTIONS=--add-modules java.xml.bind
</pre><br />
Linux, add this to bin/catalina.sh:<br /><br />
<pre>

export JDK_JAVA_OPTIONS=--add-modules java.xml.bind
</pre>
<br />
<br />

DataSource config added in Tomcats conf/context.xml-file: 

Windows:<br /><br />
<pre>
&lt;Context&gt;
    &lt;Resource name="jdbc/MyAtgDS" auth="Container" type="javax.sql.DataSource"
               maxTotal="100" maxIdle="30" maxWaitMillis="10000" testOnBorrow="true" 
               validationQuery="select 1" maxConnLifetimeMillis="28800000"
               username="my_user" password="my_password" driverClassName="org.mariadb.jdbc.Driver"
               url="jdbc:mariadb://localhost/myatg?autoReconnect=true&amp;pipe=C:\tmp\mysql.sock"/&gt;

&lt;/Context&gt;
</pre>
Linux:<br /><br />
<pre>
&lt;Context&gt;
    &lt;Resource name="jdbc/MyAtgDS" auth="Container" type="javax.sql.DataSource"
               maxTotal="100" maxIdle="30" maxWaitMillis="10000" testOnBorrow="true" 
               validationQuery="select 1" maxConnLifetimeMillis="28800000"
               username="my_user" password="my_password" driverClassName="org.mariadb.jdbc.Driver"
               url="jdbc:mariadb://localhost/myatg?autoReconnect=true&amp;localSocket=/var/run/mysqld/mysqld.sock"/&gt;
&lt;/Context&gt;
</pre>
<br />
Connection to database in code through DataSource:
<br /><br /><br />
<pre>
Context initContext = new InitialContext();
Context envContext  = (Context)initContext.lookup("java:/comp/env");
DataSource ds = (DataSource)envContext.lookup("jdbc/MyAtgDS");
Connection conn = ds.getConnection();
</pre>