package br.ufv.dpi.testandojava;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class FuncoesTestExemplo {

    @Before
    public void setUp() throws Exception {
        System.out.println("Chamado antes de um caso de teste");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Chamado depois de um caso de teste");
    }

    @Test
    public void testEhPrimo() {
        System.out.println("Testando ehPrimo");

        fail("Não implementado");
    }

	@Test
	public void testFibo() {
        System.out.println("Testando Fibo");

        fail("Não implementado");
	}

    @Test
    public void testClassificaFibo() {
        System.out.println("Testando classificaFibo");

        fail("Não implementado");
    }
}
