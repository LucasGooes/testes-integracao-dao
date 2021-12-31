package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeilaoTest {
	
	private LeilaoPage paginaDeLeilao;
	
	@AfterEach
	public void afterEach() {
		this.paginaDeLeilao.fechar();
	}
	
	@Test
	public void deveriaCadastrarLeilao() {
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		this.paginaDeLeilao = paginaDeLogin.efetuaLogin();
		CadastroLeilaoPage paginaDeCadastro = paginaDeLeilao.carregarFormulario();	
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
		String nome = "Leilão do dia "+hoje;
		String valor = "500.00";
		this.paginaDeLeilao =  paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
	
		Assert.assertTrue(paginaDeLeilao.isLeilaoCadastrado(nome, valor, hoje)); 
	}
	
}
