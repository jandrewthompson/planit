package com.jthompson.music.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Test
public abstract class AbstractRollbackJpaRepository 
{

	protected EntityManagerFactory emf;
	protected EntityManager em;
	
	@BeforeMethod
	public void before()
	{
		emf = Persistence.createEntityManagerFactory("test-planitPU");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		createFixtureAndTestData();
	}
	
	@AfterMethod
	public void after()
	{
		if( null != em )
		{
			em.getTransaction().rollback();
			em.close();
		}
		if( null != emf )
		{
			emf.close();
		}
	}
	
	abstract void createFixtureAndTestData();
	
}
