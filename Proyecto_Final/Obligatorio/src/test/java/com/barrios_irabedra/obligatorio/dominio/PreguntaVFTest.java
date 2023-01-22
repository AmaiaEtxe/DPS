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
public class PreguntaVFTest {
    
    public PreguntaVFTest() {
        assertNotNull(new PreguntaVF());
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Invocado antes de comenzar "
                + "las pruebas en Pregunta"
                + "VF.");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Invocado al finalizar todas "
                + "las pruebas en Pregunta"
                + "VF.");
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
     * Test of toString method, of class PreguntaVF.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("True", Boolean.TRUE);
        PreguntaVF instance = new PreguntaVF("El caballo"
                + " blanco de San Martín era blanco", 30, map);
        String expResult = "El caballo blanco de San "
                + "Martín era blanco. \n"
                + "Tienes 30 segundos para "
                + "responder. \nY su respuesta correcta era "
                + "{True=true}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
