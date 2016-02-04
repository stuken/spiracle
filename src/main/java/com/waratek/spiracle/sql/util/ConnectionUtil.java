/*
 *  Copyright 2014 Waratek Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.waratek.spiracle.sql.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.waratek.spiracle.sql.servlet.util.ParameterNullFix;

public class ConnectionUtil {

    private static final Logger logger = Logger.getLogger(ParameterNullFix.class);

    public static Connection getConnection(ServletContext application, String connectionType) throws SQLException {
        Connection con = null;
        logger.info("Connection Type: " + connectionType);
        if (connectionType.equals(Constants.C3P0_ORACLE)) {
            ComboPooledDataSource ds = (ComboPooledDataSource) application.getAttribute(Constants.ORACLE_CONNECTION_POOL);
            con = ds.getConnection();
        } else if (connectionType.equals(Constants.C3P0_MYSQL)) {
            ComboPooledDataSource ds = (ComboPooledDataSource) application.getAttribute(Constants.MYSQL_CONNECTION_POOL);
            con = ds.getConnection();
        } else if (connectionType.equals(Constants.C3P0_MSSQL)) {
            ComboPooledDataSource ds = (ComboPooledDataSource) application.getAttribute(Constants.MSSQL_CONNECTION_POOL);
            con = ds.getConnection();
        } else if (connectionType.equals("jndi")) {
            DataSource ds = (DataSource) application.getAttribute("jndiConnectionPool");
            con = ds.getConnection();
        }

        logger.info("Returning connection: " + con.toString());
        return con;
    }
}
