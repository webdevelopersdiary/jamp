JAMP
====
A portable platform-independent PHP, webserver and database stack in Java.
[See blog post at webdevelopersdiary.com.](http://www.webdevelopersdiary.com/1/post/2012/07/jamp-an-ultra-portable-php-web-server-and-database-stack-in-java.html)

Install
=======
Install the latest version of Quercus and clone the JAMP repository, e.g.

	wget http://caucho.com/download/quercus-4.0.25.war
	mvn install:install-file -Dfile=quercus-4.0.25.war -DgroupId=com.caucho -DartifactId=quercus -Dversion=4.0.25 -Dpackaging=war
	git clone https://github.com/webdevelopersdiary/jamp.git

Run
===
Put your files in the document root located at `src/main/webapp/` and start the webserver:

	cd jamp
	mvn jetty:run

Wait until you see `[INFO] Started Jetty Server` in the console output and point your browser to `http://localhost:8080/`.

Internal database
===================
By default the database is temporary, meaning if you stop the web server, the data is erased.
If you want to use a persistent database file instead of the temporary in-memory database,
go to `src/main/webapp/WEB-INF/jetty-env.xml` and change line

	<Set name="url">jdbc:h2:mem:database;MODE=MYSQL</Set>


to

	<Set name="url">jdbc:h2:file:filename;MODE=MYSQL</Set>

Where you replace 'filename' with a relative path to a file
(relative to `pom.xml`) or an absolute path to a file.
For more information about the H2 JDBC URL see
[H2's feature list](http://www.h2database.com/html/features.html#database_url).

External MySQL database
=======================
If you want to connect to an external MySQL database, go to `src/main/webapp/WEB-INF/web.xml`
and comment the following block:

	<init-param>
		<param-name>database</param-name>
		<param-value>java:comp/env/jdbc/h2</param-value>
	</init-param>

With this code block still enabled, Quercus will just ignore all connect parameters
and connect you with the internal database instead.

php.ini
=======
php.ini is located at `src/main/webapp/WEB-INF/php.ini`.

.htaccess
=========
JAMP has partial support for .htaccess, currently it can parse mod_rewrite rules.
.htaccess *must* be located in the web root `src/main/webapp/`.

Known limitations
=================
JAMP only supports PDO database connections to its internal database
(`mysql_connect()` is supported, but only to external databases).
