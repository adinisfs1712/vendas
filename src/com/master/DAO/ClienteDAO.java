package com.master.DAO;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.master.entities.Cliente;
import com.master.repository.ClienteRepository;

public class ClienteDAO {
	
	@Inject
	public ClienteRepository clienteRepository;

	private static ClienteDAO instance;

	protected EntityManager entityManager;

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = null;
		try {
			factory = Persistence.createEntityManagerFactory("masterPU");
			if (entityManager == null) {
				entityManager = factory.createEntityManager();
			}
		} finally {
			factory.close();
		}
		return entityManager;

	}

	public Cliente salvar(Cliente cliente) throws Exception {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			if (cliente.getId() == null) {
				entityManager.persist(cliente);
			} else {
				cliente = entityManager.merge(cliente);
			}
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		return cliente;
	}

	public void excluir(Cliente cliente) {
		try {
			entityManager.getTransaction().begin();
			cliente = entityManager.find(Cliente.class, cliente.getId());
			entityManager.remove(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public static ClienteDAO getInstance() {
		if (instance == null) {
			instance = new ClienteDAO();
		}
		return instance;
	}

	private ClienteDAO() {
		entityManager = getEntityManager();
	}

	public Cliente getById(final int id) {
		return entityManager.find(Cliente.class, id);
	}

	public Cliente getByFone(final String telefone) {
		return entityManager.find(Cliente.class, telefone);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() {
		return entityManager.createQuery("FROM " + Cliente.class.getName()).getResultList();
	}

}
