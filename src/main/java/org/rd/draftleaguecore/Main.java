package org.rd.draftleaguecore;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Struct;
import java.util.List;

public class Main {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JavaHelps");

    public static void main(String[] args) {
        create(1, "Alice", 22);
        create(2, "Bob", 20);
        create(3, "Charlie", 25);

        update(2, "Bobby", 20);

        delete(1);

        List<Student> students = readAll();
        if(students != null) {
            for(Student student : students) {
                System.out.println(student);
            }
        }

        ENTITY_MANAGER_FACTORY.close();
    }



    private static void create(int id, String name, int age) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setAge(age);

            manager.persist(student);

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    private static void delete(int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Student stu = manager.find(Student.class, id);

            manager.remove(stu);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
    }

    public static void update(int id, String name, int age) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            // Get the Student object
            Student stu = manager.find(Student.class, id);

            // Change the values
            stu.setName(name);
            stu.setAge(age);

            // Update the student
            manager.persist(stu);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
    }

    private static List<Student> readAll() {
        List<Student> students = null;

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            students = manager.createQuery("SELECT s FROM Student s", Student.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            manager.close();
        }

        return students;
    }
}
