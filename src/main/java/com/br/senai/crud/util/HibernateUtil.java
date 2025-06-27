package com.br.senai.crud.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    // Cria uma única instância de SessionFactory ao carregar a classe
    private static final SessionFactory sessionFactory = buildSessionFactory();

    // Metodo privado responsável por construir a SessionFactory
    private static SessionFactory buildSessionFactory() {
        try {
            // Cria o registro de serviços do Hibernate, lendo o arquivo hibernate.cfg.xml
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            // Lê os metadados com base no registro e configurações mapeadas
            Metadata metadata = new MetadataSources(standardRegistry)
                    .getMetadataBuilder()
                    .build();
            // Cria e retorna a SessionFactory
            return metadata.getSessionFactoryBuilder().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Falha ao criar SessionFactory: " + ex);
        }
    }

    // Retorna a SessionFactory para ser usada nas DAO
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    // Fecha a SessionFactory
    public static void shutdown() {
        getSessionFactory().close();
    }
}