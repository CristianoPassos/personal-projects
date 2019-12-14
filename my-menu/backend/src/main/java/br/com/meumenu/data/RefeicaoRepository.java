/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.meumenu.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.meumenu.model.cadastro.Fornecedor;
import br.com.meumenu.model.cardapio.Refeicao;
import br.com.meumenu.model.cardapio.Refeicao_;

@ApplicationScoped
public class RefeicaoRepository {

	@Inject
	private EntityManager em;

	public List<Refeicao> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Refeicao> criteria = cb.createQuery(Refeicao.class);
		Root<Refeicao> member = criteria.from(Refeicao.class);
		criteria.select(member);
		return em.createQuery(criteria).getResultList();
	}

	public List<Refeicao> findByFornecedor(int idFornecedor) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Refeicao> query = cb.createQuery(Refeicao.class);
		Root<Refeicao> root = query.from(Refeicao.class);

		root.fetch(Refeicao_.variacoes, JoinType.LEFT);

		query.where(cb.equal(root.get(Refeicao_.fornecedor), new Fornecedor(idFornecedor)));
		query.distinct(true);

		return em.createQuery(query).getResultList();
	}

	public Refeicao findById(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Refeicao> query = cb.createQuery(Refeicao.class);
		Root<Refeicao> root = query.from(Refeicao.class);

		root.fetch(Refeicao_.variacoes, JoinType.LEFT);

		query.where(cb.equal(root.get(Refeicao_.id), id));

		try {
			return em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			return new Refeicao();
		}
	}

}
