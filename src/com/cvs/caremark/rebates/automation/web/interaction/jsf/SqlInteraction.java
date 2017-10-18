package com.cvs.caremark.rebates.automation.web.interaction.jsf;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ibm.db2.jcc.DB2Driver;

public abstract class SqlInteraction extends Interaction {

	public static class JdbcDriverFactory {
		public static Driver createDriverString(String db) {
			if (Dbs.DB2.name().equalsIgnoreCase(db)) {
				return new DB2Driver();
			}
			return null;
		}

		public static String createUrlString(String db, String url) {
			if (Dbs.DB2.name().equalsIgnoreCase(db)) {
				return "jdbc:db2://" + url;
			}
			return null;
		}

		public static String getSchemaString(String db, String schema) {

			if (Dbs.DB2.name().equalsIgnoreCase(db)) {
				return "SET SCHEMA " + schema;
			}
			return null;
		}
	}

	public class Db {

		String dbUrl, user, pass;
		Driver driver;

		public Db(String user, String pass, String dbUrl, String dbType) {
			this.dbUrl = dbUrl;
			this.user = user;
			this.pass = pass;

			try {
				driver = JdbcDriverFactory.createDriverString(dbType);
				DriverManager.registerDriver(driver);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public String getSelectStmtString(String column, String where,
				String table) {
			return SqlOperation.SELECT + " " + column + " from " + table
					+ " where " + where;
		}

		public String getCountStmt(String table) {
			return SqlOperation.SELECT + " count(*) from " + table;
		}

		public Connection getConnection() throws SQLException {

			return DriverManager.getConnection(dbUrl, user, pass);
		}
	}

	public static enum Dbs {
		DB2, Oracle
	}

	public static enum SqlOperation {
		SELECT
	}

	private String user;
	private String pass;
	private String dbUrl;
	private String dbType;
	private String table;
	private String schema;
	private String query;


	public ResultSet executeUserQuery() {
		Connection c = null;
		try {
			Db d = new Db(user, pass, dbUrl, dbType);
			c = d.getConnection();
			Statement s = c.createStatement();
			ResultSet r;
			r = s.executeQuery(query);

			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	

	@Test
	@Parameters({ "user", "pass", "db_url", "db_type", "query", "table",
			"schema" })
	public void setDbParams(String user, String pass, String dbUrl,
			String dbType, String query, @Optional String table,
			@Optional String schema) {
		this.user = user;
		this.pass = pass;
		this.schema = schema;
		this.dbUrl = JdbcDriverFactory.createUrlString(dbType, dbUrl);
		this.dbType = dbType;
		this.table = table;
		this.query = query;
	}

}
