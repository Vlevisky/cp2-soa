package br.com.fiap.checkpoint2.service;

import br.com.fiap.checkpoint2.exception.PedidoNaoEncontradoException;
import br.com.fiap.checkpoint2.model.Pedido;
import br.com.fiap.checkpoint2.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Transactional
    public Pedido criar(Pedido pedido) {
        pedido.setId(null);
        pedido.setDataPedido(null);
        return pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }

    @Transactional
    public Pedido atualizar(Long id, Pedido dadosAtualizados) {
        Pedido pedidoExistente = buscarPorId(id);
        pedidoExistente.setClienteNome(dadosAtualizados.getClienteNome());
        pedidoExistente.setValorTotal(dadosAtualizados.getValorTotal());
        return pedidoRepository.save(pedidoExistente);
    }

    @Transactional
    public void excluir(Long id) {
        Pedido pedido = buscarPorId(id);
        pedidoRepository.delete(pedido);
    }
}
