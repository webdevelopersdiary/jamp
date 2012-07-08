package com.webdevelopersdiary.jamp.scl;

import javax.naming.NamingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * This listener initialises the JAMP stack.
 */
public class WebInitialiser implements ServletContextListener {
	private static Logger logger = Logger.getLogger(WebInitialiser.class.getName());
	 
	public void contextInitialized(ServletContextEvent contextEvent) {
		// Initialise DataSource by opening and closing a connection.
		try {
			getJNDIConnection().close();
			logger.info("DataSource initialised");
		} catch(SQLException e) {
			logger.warning("Could not close connection.");
		}
	}
	public void contextDestroyed(ServletContextEvent contextEvent) {}
	
	/**
	 * Gets a connection from the pool.
	 */
	protected Connection getJNDIConnection() {
		String DATASOURCE_CONTEXT = "java:comp/env/jdbc/database";

		Connection result = null;
		try {
			Context initialContext = new InitialContext();
			if ( initialContext == null) {
				logger.warning("JNDI problem. Cannot get InitialContext.");
			}
			DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
			if (datasource != null) {
				result = datasource.getConnection();
			}
			else {
				logger.warning("Failed to lookup datasource.");
			}
		} catch ( NamingException ex ) {
			logger.warning("Cannot get connection: " + ex);
		} catch(SQLException ex) {
			logger.warning("Cannot get connection: " + ex);
		}
		return result;
	}
}
