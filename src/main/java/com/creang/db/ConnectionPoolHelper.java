package com.creang.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.logging.Logger;

public enum ConnectionPoolHelper {
    INSTANCE;

    private DataSource dataSource;

    ConnectionPoolHelper() {

        Context initContext;

        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/MyAtgDS");

        } catch (Exception e) {
            Logger logger = Logger.getLogger(ConnectionPoolHelper.class.getName());
            logger.severe(e.getMessage());
        }
    }

    public static ConnectionPoolHelper getInstance() {
        return INSTANCE;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
