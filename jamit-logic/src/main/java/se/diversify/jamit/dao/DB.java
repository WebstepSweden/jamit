package se.diversify.jamit.dao;

import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;

public class DB {

	private static JDBCConnectionPool pool;

	static {
		try {
			pool = new SimpleJDBCConnectionPool("org.hsqldb.jdbc.JDBCDriver",
					"jdbc:hsqldb:mem:sqlcontainer", "SA", "", 2, 5);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	static SQLContainer getSQLContainer(String table) {

		SQLContainer container = null;

		try {
			TableQuery tq = new TableQuery(table, pool);
			tq.setVersionColumn("OPTLOCK");
			container = new SQLContainer(tq);
			container.setAutoCommit(true);
			return container;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return container;
	}
}
