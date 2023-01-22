/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrios_irabedra.obligatorio.dominio;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class PreguntaCortaRespuestaTest {
    
    public PreguntaCortaRespuestaTest() {
        assertNotNull(new PreguntaCortaRespuesta());
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Invocado antes de comenzar las "
                + "pruebas en Pregunta"
                + "CortaRespuesta.");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Invocado al finalizar todas las "
                + "pruebas en Pregunta"
                + "CortaRespuesta.");
    }
    
    @Before
    public void setUp() {
        System.out.println("Invocado al comenzar una prueba.");
    }
    
    @After
    public void tearDown() {
        System.out.println("Invocado al finalizar una prueba.");
    }

    /**
     * Test of toString method, of class PreguntaCortaRespuesta.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("Blanco", Boolean.TRUE);
        PreguntaCortaRespuesta instance
                = new PreguntaCortaRespuesta("¿De qué "
                + "color era el caballo blanco de "
                        + "San Martín?", 30, map);
        String expResult = "¿De qué color era el caballo "
                + "blanco de San Martín?. \n"
                + "Tienes 30 segundos para responder. \nY "
                + "sus respuesta correcta era "
                + "{Blanco=true}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
