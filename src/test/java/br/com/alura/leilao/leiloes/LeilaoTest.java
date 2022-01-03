package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeilaoTest {
	
	private LeilaoPage paginaDeLeilao;
	private CadastroLeilaoPage paginaDeCadastro;
	
	@BeforeEach
	public void beforeEach() {
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		this.paginaDeLeilao = paginaDeLogin.efetuaLogin();
		this.paginaDeCadastro = paginaDeLeilao.carregarFormulario();	
		
	}
 	
	@AfterEach
	public void afterEach() {
		this.paginaDeLeilao.fechar();
	}
	
	@Test
	public void deveriaCadastrarLeilao() throws InterruptedException {
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilão do dia "+hoje;
		String valor = "500.00";
		this.paginaDeLeilao =  paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
		
		Assert.assertTrue(paginaDeLeilao.isLeilaoCadastrado(nome, valor, hoje)); 
	}
	
	@Test
	public void deveriaValidarCadastroDeLeilao() {
		this.paginaDeLeilao =  paginaDeCadastro.cadastrarLeilao("", "", "");
		
		Assert.assertFalse(this.paginaDeCadastro.isPaginaAtual()); 
		Assert.assertTrue(this.paginaDeLeilao.isPaginaAtual()); 
		Assert.assertTrue(this.paginaDeCadastro.isMensagensaDeValidacaoVisiveis()); 
		
	}
	
}
