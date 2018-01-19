package com.framgia.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				        .configure("/com/framgia/util/hibernate.cfg.xml").build();
				Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				return metadata.getSessionFactoryBuilder().build();

			} catch (HibernateException he) {
				System.out.println("Session Factory creation failure");
				throw he;
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}
