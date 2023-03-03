package data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class DbUtils {

    private SelenideElement notification
            = $x("//*[@class = 'notification notification_visible notification_status_ok notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']");

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5431/app", "app", "pass");
//            connection = DriverManager
//                        .getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return connection;
    }


    public void cleanDb() {
        var runner = new QueryRunner();
        var cleanCredit = "DELETE FROM credit_request_entity";
        var cleanDebit = "DELETE FROM payment_entity";
        var cleanOrder = "DELETE FROM order_entity";

        try (
                var connection = getConnection();
        ) {
            runner.update(connection, cleanCredit);
            runner.update(connection, cleanDebit);
            runner.update(connection, cleanOrder);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public String getCreditStatus() {
        var runner = new QueryRunner();
        var request
                = "select status from credit_request_entity where created = (select max(created)from credit_request_entity)";
        var result = "";
        try (
                var connection = getConnection();
        ) {
            var status = runner.query(connection, request, new ScalarHandler<>());
            System.out.println(status);
            result = (String) status;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public String getDebitStatus() {
        var runner = new QueryRunner();
        var request = "select status from payment_entity where created = (select max(created)from payment_entity)";
        var result = "";

        try (
                var connection = getConnection();
        ) {
            var status = runner.query(connection, request, new ScalarHandler<>());
            System.out.println(status);
            result = (String) status;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public void waitNotificationForDb() {
        notification.shouldBe(Condition.visible, Duration.ofSeconds(12));
    }
}

