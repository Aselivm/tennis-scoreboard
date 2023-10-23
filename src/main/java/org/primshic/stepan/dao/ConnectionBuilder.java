package org.primshic.stepan.dao;

import java.sql.SQLException;
import java.sql.Connection;

public interface ConnectionBuilder {
    Connection getConnection() throws SQLException;
}
