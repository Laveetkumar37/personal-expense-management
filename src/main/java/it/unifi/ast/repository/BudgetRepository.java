
package it.unifi.ast.repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import it.unifi.ast.model.Budget;
import it.unifi.ast.model.Category;
import it.unifi.ast.persistence.JpaUtil;

public class BudgetRepository {

    public Budget save(Budget budget) {

        if (budget == null) {
            throw new IllegalArgumentException("Budget cannot be null");
        }

        EntityManager em =
                JpaUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            String categoryName = budget.getCategory();

            List<Category> categories = em.createQuery(
                    "SELECT c FROM Category c WHERE c.name = :name",
                    Category.class)
                    .setParameter("name", categoryName)
                    .getResultList();

            Category category;

            if (categories.isEmpty()) {
                category = new Category(categoryName);
                em.persist(category);
            } else {
                category = categories.get(0);
            }

            budget.setCategoryEntity(category);

            em.persist(budget);

            transaction.commit();

            return budget;

        } catch (RuntimeException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw ex;

        } finally {
            em.close();
        }
    }

    public List<Budget> findAll() {

        EntityManager em =
                JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            return em.createQuery(
                    "SELECT b FROM Budget b ORDER BY b.month DESC",
                    Budget.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public Optional<Budget> findById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Budget ID cannot be null");
        }

        EntityManager em =
                JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            return Optional.ofNullable(em.find(Budget.class, id));

        } finally {
            em.close();
        }
    }

    public boolean deleteById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Budget ID cannot be null");
        }

        EntityManager em =
                JpaUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Budget budget = em.find(Budget.class, id);

            if (budget == null) {
                transaction.commit();
                return false;
            }

            em.remove(budget);
            transaction.commit();

            return true;

        } catch (RuntimeException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw ex;

        } finally {
            em.close();
        }
    }
}
