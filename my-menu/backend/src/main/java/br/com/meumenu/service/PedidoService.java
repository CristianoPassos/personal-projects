package br.com.meumenu.service;

import br.com.meumenu.model.cadastro.Fornecedor;
import br.com.meumenu.model.cardapio.Refeicao;
import br.com.meumenu.model.cardapio.Variacao;

import javax.ejb.Stateless;

@Stateless
public class PedidoService extends BaseService<Refeicao> {

    public void register(Refeicao entity, int idFornecedor) throws Exception {
        log.info("Registering: " + entity);
        entity.setFornecedor(new Fornecedor(idFornecedor));
        validate(entity);
        for (Variacao variacao : entity.getVariacoes()) {
            variacao.setRefeicao(entity);
        }
        em.persist(entity);
        eventSrc.fire(entity);
    }

    @Override
    public void update(Refeicao entity) {
        for (Variacao variacao : entity.getVariacoes()) {
            variacao.setRefeicao(entity);
        }
        super.update(entity);
    }

}
