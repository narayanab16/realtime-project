package com.narayana.bank.app.dbserver;

import org.h2.tools.RunScript;
import org.h2.tools.Server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

public class RunDBServer {
    public static void main(String[] args) {
        try {
            tcpServer();
            createTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void tcpServer() throws SQLException {
        Server.main("-web", "-webAllowOthers", "-tcp", "-tcpPort", "8888", "-tcpAllowOthers",
                "-baseDir", "D:/test/bank-db", "-ifNotExists");
    }

    private static void createTables() throws SQLException, FileNotFoundException {
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost:8888/bankdb", "sa", "welcome1");
        String file = ClassLoader.getSystemResource("create_db.sql").getFile();
        System.out.println(" file: " + file);
        RunScript.execute(conn, new FileReader(file));
        PreparedStatement showTables = conn.prepareStatement("show tables");
        ResultSet resultSet = showTables.executeQuery();
        while (resultSet.next()) {
            System.out.println(" " + resultSet.getString(1));
        }
    }
}
