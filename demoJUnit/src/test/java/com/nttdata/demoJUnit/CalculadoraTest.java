package com.nttdata.demoJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;



public class CalculadoraTest {

	@Test
	public void testSuma() {
		Calculadora calculadora = new Calculadora();
		Integer resultado = calculadora.suma(2, 3);
		assertEquals(6, resultado);
	}

}
