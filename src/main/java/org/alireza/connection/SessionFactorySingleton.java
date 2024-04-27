package org.alireza.connection;

import org.alireza.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {

    private SessionFactorySingleton(){}

    private static class LazyHolder{

        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(BankCard.class)
                    .addAnnotatedClass(Loan.class)
                    .addAnnotatedClass(University.class)
                    .addAnnotatedClass(LoanInstallment.class)
                    .addAnnotatedClass(Spouse.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance(){
        return LazyHolder.INSTANCE;
    }
}
