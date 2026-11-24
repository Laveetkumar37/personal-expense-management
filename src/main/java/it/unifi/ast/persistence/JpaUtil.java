package it.unifi.ast.persistence;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JpaUtil {

    private static EntityManagerFactory entityManagerFactory;

    private JpaUtil() {
    }

    public static synchronized EntityManagerFactory getEntityManagerFactory() {

        if (entityManagerFactory == null) {

            Map<String, Object> properties = new HashMap<>();

            String username = System.getProperty(
                    "DB_USER", System.getenv("DB_USER"));

            String password = System.getProperty(
                    "DB_PASSWORD", System.getenv("DB_PASSWORD"));

            String databaseUrl = System.getProperty("DB_URL");

            if (username == null || password == null) {
                throw new IllegalStateException(
                        "Database environment variables are missing.");
            }

            properties.put(
                    "jakarta.persistence.jdbc.user", username);

            properties.put(
                    "jakarta.persistence.jdbc.password", password);

            if (databaseUrl != null) {
                properties.put(
                        "jakarta.persistence.jdbc.url", databaseUrl);
            }

            entityManagerFactory =
                    Persistence.createEntityManagerFactory(
                            "personal-expense-pu", properties);
        }

        return entityManagerFactory;
    }

    public static synchronized void close() {

        if (entityManagerFactory != null
                && entityManagerFactory.isOpen()) {

            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }
}