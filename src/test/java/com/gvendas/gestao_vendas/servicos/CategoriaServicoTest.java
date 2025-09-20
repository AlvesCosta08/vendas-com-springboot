package com.gvendas.gestao_vendas.servicos;


import com.gvendas.gestao_vendas.entidades.Categoria;
import com.gvendas.gestao_vendas.repositorio.CategoriaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaServicoTest {

    @Mock
    private CategoriaRepositorio categoriaRepositorio;

    @InjectMocks
    private CategoriaServico categoriaServico;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setCodigo(1L);
        categoria.setNome("Eletrônicos");
    }

    // ========== TESTES ========== //

    @Test
    void listarTodas_DeveRetornarListaDeCategorias_QuandoExistiremCategorias() {
        // Arrange
        List<Categoria> categorias = Arrays.asList(categoria, new Categoria());
        when(categoriaRepositorio.findAll()).thenReturn(categorias);

        // Act
        List<Categoria> resultado = categoriaServico.listarTodas();

        // Assert
        assertThat(resultado).hasSize(2);
        verify(categoriaRepositorio, times(1)).findAll();
    }

    @Test
    void buscarPorCodigo_DeveRetornarCategoria_QuandoCodigoExistir() {
        // Arrange
        when(categoriaRepositorio.findById(1L)).thenReturn(Optional.of(categoria));

        // Act
        Optional<Categoria> resultado = categoriaServico.buscarPorCodigo(1L);

        // Assert
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getCodigo()).isEqualTo(1L);
        verify(categoriaRepositorio, times(1)).findById(1L);
    }

    @Test
    void buscarPorCodigo_DeveRetornarOptionalVazio_QuandoCodigoNaoExistir() {
        // Arrange
        when(categoriaRepositorio.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<Categoria> resultado = categoriaServico.buscarPorCodigo(999L);

        // Assert
        assertThat(resultado).isEmpty();
        verify(categoriaRepositorio, times(1)).findById(999L);
    }

    @Test
    void salvar_DeveSalvarECriarNovaCategoria() {
        // Arrange
        when(categoriaRepositorio.save(any(Categoria.class))).thenReturn(categoria);

        // Act
        Categoria resultado = categoriaServico.salvar(categoria);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getCodigo()).isEqualTo(1L);
        verify(categoriaRepositorio, times(1)).save(categoria);
    }

    @Test
    void atualizar_DeveAtualizarCategoriaExistente() {
        // Arrange
        Categoria categoriaAtualizada = new Categoria();
        categoriaAtualizada.setNome("Eletrodomésticos");

        when(categoriaRepositorio.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaRepositorio.save(any(Categoria.class))).thenReturn(categoria);

        // Act
        Categoria resultado = categoriaServico.atualizar(1L, categoriaAtualizada);

        // Assert
        assertThat(resultado.getNome()).isEqualTo("Eletrodomésticos");
        assertThat(resultado.getCodigo()).isEqualTo(1L); // não deve mudar
        verify(categoriaRepositorio, times(1)).findById(1L);
        verify(categoriaRepositorio, times(1)).save(any(Categoria.class));
    }

    @Test
    void atualizar_DeveLancarExcecao_QuandoCategoriaNaoExistir() {
        // Arrange
        Categoria categoriaInexistente = new Categoria();
        categoriaInexistente.setNome("Inexistente");

        when(categoriaRepositorio.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> categoriaServico.atualizar(999L, categoriaInexistente))
                .isInstanceOf(EmptyResultDataAccessException.class)
                .hasMessageContaining("1");

        verify(categoriaRepositorio, times(1)).findById(999L);
        verify(categoriaRepositorio, never()).save(any());
    }
}
