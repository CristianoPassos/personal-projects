package br.com.meumenu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

public abstract class BaseService<T> {
	@Inject
	protected Validator validator;

	@Inject
	protected EntityManager em;

	@Inject
	protected Event<T> eventSrc;

	@Inject
	protected Logger log;

	public void register(List<T> entities) throws Exception {
		for (T entity : entities) {
			validate(entity);
		}

		for (T entity : entities) {
			log.info("Registering: " + entity);
			em.persist(entity);
			eventSrc.fire(entity);
		}
	}

	public void register(T entity) throws Exception {
		log.info("Registering: " + entity);
		validate(entity);
		em.persist(entity);
		eventSrc.fire(entity);
	}

	public void remove(T entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	public void update(T entity) {
		log.info("Merging: " + entity);
		validate(entity);
		em.merge(entity);
		eventSrc.fire(entity);
	}

	protected void validate(T entity) {
		Set<ConstraintViolation<T>> violations = validator.validate(entity);

		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

}
