package br.ufv.dpi.testandojava;
import android.util.Log;

import junit.framework.TestCase;

import org.junit.*;
import org.junit.Assert.*;

import java.util.Calendar;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class FuncoesTest extends TestCase {

    Funcoes f = null;

    @Before
    public void setUp() throws Exception {
        f = new Funcoes();
        System.out.println("Chamado antes de um caso de teste");
    }

    @After
    public void tearDown() throws Exception {
        System.gc();
        System.out.println("Chamado depois de um caso de teste");
    }

    @Test
    public void testEhPrimo() {
        System.out.println("Testando ehPrimo");

        assertFalse(f.ehPrimo(1));
        assertTrue(f.ehPrimo(2));
        assertEquals(true,f.ehPrimo(3));
        assertEquals(false,f.ehPrimo(4));

        //boolean resultTest = f.ehPrimo(4);
        //assertTrue(resultTest);
    }

	@Test
	public void testFibo() {
        System.out.println("Testando Fibo");

        assertEquals(2, f.fibo(3));
	}

    @Test
    public void testClassificaFibo() {
        System.out.println("Testando classificaFibo");

        assertEquals("Entrada inválida",f.classificaFibo(0));
        assertEquals("O 3º número da sequencia é Primo",f.classificaFibo(3));
        assertEquals("O 6º número da sequencia não é Primo",f.classificaFibo(6));
    }
}
