
package it.unifi.ast.repository;

import java.util.List;
import java.util.Optional;
import it.unifi.ast.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import it.unifi.ast.model.Expense;
import it.unifi.ast.persistence.JpaUtil;

public class ExpenseRepository {


public Expense save(Expense expense) {

    if (expense == null) {
        throw new IllegalArgumentException("Expense cannot be null");
    }

    EntityManager entityManager =
            JpaUtil.getEntityManagerFactory().createEntityManager();

    EntityTransaction transaction = entityManager.getTransaction();

    try {
        transaction.begin();

        // Find an existing category by name
        String categoryName = expense.getCategory();

        List<Category> categories = entityManager.createQuery(
                "SELECT c FROM Category c WHERE c.name = :name",
                Category.class)
                .setParameter("name", categoryName)
                .getResultList();

        Category category;

        if (categories.isEmpty()) {
            // Create and save a new category
            category = new Category(categoryName);
            entityManager.persist(category);
        } else {
            category = categories.get(0);
        }

        // Connect the expense to the saved category
        expense.setCategoryEntity(category);

        // Save the expense
        entityManager.persist(expense);

        transaction.commit();

        return expense;

    } catch (RuntimeException ex) {

        if (transaction.isActive()) {
            transaction.rollback();
        }

        throw ex;

    } finally {
        entityManager.close();
    }
}


    public List<Expense> findAll() {

        EntityManager entityManager =
                JpaUtil.getEntityManagerFactory()
                       .createEntityManager();

        try {
            return entityManager
                    .createQuery(
                        "SELECT e FROM Expense e ORDER BY e.date DESC",
                        Expense.class
                    )
                    .getResultList();

        } finally {
            entityManager.close();
        }
    }

    public Optional<Expense> findById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                "Expense ID cannot be null"
            );
        }

        EntityManager entityManager =
                JpaUtil.getEntityManagerFactory()
                       .createEntityManager();

        try {
            return Optional.ofNullable(
                entityManager.find(Expense.class, id)
            );

        } finally {
            entityManager.close();
        }
    }

    public boolean deleteById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException(
                "Expense ID cannot be null"
            );
        }

        EntityManager entityManager =
                JpaUtil.getEntityManagerFactory()
                       .createEntityManager();

        EntityTransaction transaction =
                entityManager.getTransaction();

        try {
            transaction.begin();

            Expense expense =
                    entityManager.find(Expense.class, id);

            if (expense == null) {
                transaction.commit();
                return false;
            }

            entityManager.remove(expense);

            transaction.commit();

            return true;

        } catch (RuntimeException ex) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            throw ex;

        } finally {
            entityManager.close();
        }
    }
}
