package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/DB", "name", "pass");
    }

    @SneakyThrows
    public static String getOrderId() {
        var codeSQL = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var conn = getConnection();
        return runner.query(conn, codeSQL, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static String getPaymentId() {
        var codeSQL = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConnection();
        return runner.query(conn, codeSQL, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static String getStatus() {
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConnection();
        return runner.query(conn, codeSQL, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static int getAmount() {
        var codeSQL = "SELECT amount FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConnection();
        return runner.query(conn, codeSQL, new ScalarHandler<Integer>());
    }


    @SneakyThrows
    public static void cleanDB() {
        var conn = getConnection();
        runner.update(conn, "DELETE FROM credit_request_entity");
        runner.update(conn, "DELETE FROM order_entity");
        runner.update(conn, "DELETE FROM payment_entity");
    }
}
