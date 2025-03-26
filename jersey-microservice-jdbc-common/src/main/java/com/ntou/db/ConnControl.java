package com.ntou.db;

import com.ntou.tool.Common;
import lombok.extern.log4j.Log4j2;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@Log4j2
public class ConnControl {
    static final String PROPERTIES_FILE = "application.properties";
    private static final Properties properties = new Properties();

    // 使用 ThreadLocal 儲存每個執行緒的 Connection
    private static final ThreadLocal<Connection> threadLocalConn = new ThreadLocal<>();

    // 初始化讀取資料庫設定
    static {
        try (InputStream input = ConnControl.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                log.error("Unable to find " + PROPERTIES_FILE);
                throw new RuntimeException("Properties file not found");
            }
            properties.load(input);
        } catch (Exception e) {
            log.error(Common.EXCEPTION, e);
            throw new RuntimeException("Failed to load properties", e);
        }
    }

    // 取得資料庫連線
    public Connection getConnection() {
        try {
            Connection conn = threadLocalConn.get();
            if (conn == null || conn.isClosed()) {
                log.info("Creating new database connection...");
                Class.forName(properties.getProperty("jdbc.driver"));
                conn = DriverManager.getConnection(
                        properties.getProperty("jdbc.url"),
                        properties.getProperty("jdbc.username"),
                        properties.getProperty("jdbc.password")
                );
                threadLocalConn.set(conn);
            }
            return conn;
        } catch (Exception e) {
            log.error("Failed to get connection: ", e);
            throw new RuntimeException("Failed to get database connection", e);
        }
    }

    // 關閉 Connection
    public void closeConn() {
        try {
            Connection conn = threadLocalConn.get();
            if (conn != null && !conn.isClosed()) {
                conn.close();
                log.info("Database connection closed.");
            }
        } catch (SQLException e) {
            log.error("Error closing connection: ", e);
        } finally {
            threadLocalConn.remove(); // 避免記憶體洩漏
        }
    }

    // 關閉 PreparedStatement
    public void closePS(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
                log.info("PreparedStatement closed.");
            } catch (SQLException e) {
                log.error("Error closing PreparedStatement: ", e);
            }
        }
    }

    // 關閉 ResultSet
    public void closeRS(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                log.info("ResultSet closed.");
            } catch (SQLException e) {
                log.error("Error closing ResultSet: ", e);
            }
        }
    }
}
