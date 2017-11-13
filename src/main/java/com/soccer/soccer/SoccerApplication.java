package com.soccer.soccer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tests.Strategy;

import java.io.IOException;

import static controller.Util.getMediumOdds;

@SpringBootApplication
public class SoccerApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(SoccerApplication.class, args);
        Configuration cfg = new Configuration()
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(Team.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Fixture.class)
                .addAnnotatedClass(Odds.class)
                .addAnnotatedClass(Competition.class)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/soccer")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "root")
                .setProperty("hibernate.connection.autoReconnect", "true")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.use_sql_comments", "true")
                .setProperty("hibernate.format_sql", "true");


        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        SessionFactory sessFact = cfg.buildSessionFactory(ssrb.build());
        Session sess = sessFact.openSession();

        //getDataFromFile(sess);
        //calculateElo(sess);
        try {
            System.out.println(getMediumOdds());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Strategy.calculateOutcome(sess);

        sess.close();


//


    }
}
