package br.com.fiap.checkpoint2;

import br.com.fiap.checkpoint2.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PedidoControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoRepository pedidoRepository;

    @BeforeEach
    void limparBanco() {
        pedidoRepository.deleteAll();
    }

    @Test
    void deveExecutarCrudCompleto() throws Exception {
        String novoPedido = """
                {
                  "clienteNome": "Ana Souza",
                  "valorTotal": 149.90
                }
                """;

        MvcResult criacao = mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoPedido))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.clienteNome").value("Ana Souza"))
                .andExpect(jsonPath("$.dataPedido").isNotEmpty())
                .andExpect(jsonPath("$.valorTotal").value(149.90))
                .andReturn();

        String location = criacao.getResponse().getHeader("Location");

        mockMvc.perform(get("/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].clienteNome").value("Ana Souza"));

        mockMvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteNome").value("Ana Souza"));

        String pedidoAtualizado = """
                {
                  "clienteNome": "Ana Souza Atualizada",
                  "valorTotal": 199.90
                }
                """;

        mockMvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pedidoAtualizado))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteNome").value("Ana Souza Atualizada"))
                .andExpect(jsonPath("$.valorTotal").value(199.90));

        mockMvc.perform(delete(location))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

        mockMvc.perform(get(location))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensagem").isNotEmpty());
    }

    @Test
    void deveRejeitarPedidoInvalido() throws Exception {
        String pedidoInvalido = """
                {
                  "clienteNome": "   ",
                  "valorTotal": -1
                }
                """;

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pedidoInvalido))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.campos.clienteNome").isNotEmpty())
                .andExpect(jsonPath("$.campos.valorTotal").isNotEmpty());
    }
}
