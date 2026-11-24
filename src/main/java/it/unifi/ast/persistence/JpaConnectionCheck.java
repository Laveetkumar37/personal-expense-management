
package it.unifi.ast.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class JpaConnectionCheck {

    public static void main(String[] args) {

        EntityManagerFactory factory = null;
        EntityManager entityManager = null;

        try {
            factory = JpaUtil.getEntityManagerFactory();
            entityManager = factory.createEntityManager();

            Object result = entityManager
                    .createNativeQuery("SELECT 1")
                    .getSingleResult();

            System.out.println("MySQL connection successful!");
            System.out.println("Query result: " + result);

        } catch (Exception e) {
            System.err.println("Database connection failed:");
            e.printStackTrace();

        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }

            JpaUtil.close();
        }
    }
}
