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
public class PreguntaMultipleOpcionTest {
    
    public PreguntaMultipleOpcionTest() {
        assertNotNull(new PreguntaMultipleOpcion());
    }
    
     @BeforeClass
    public static void setUpClass() {
        System.out.println("Invocado antes de comenzar las"
                + " pruebas en Pregunta"
                + "MultipleOpcion.");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Invocado al finalizar todas "
                + "las pruebas en Pregunta"
                + "MultipleOpcion.");
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
     * Test of agregarComentarioRespuesta method, of 
     * class PreguntaMultipleOpcion.
     */
    @Test
    public void testAgregarComentarioRespuesta() {
        System.out.println("agregarComentarioRespuesta");
        String unaRespuesta = "Blanco";
        String unComentario = "De hecho, el caballo blanco "
                + "de San Martín era blanco.";
        PreguntaMultipleOpcion instance 
                = new PreguntaMultipleOpcion();
        instance.agregarComentarioRespuesta(unaRespuesta, 
                unComentario);
        assertEquals(instance.getComentario(unaRespuesta),
                unComentario);
    }

    /**
     * Test of getComentario method, of class 
     * PreguntaMultipleOpcion.
     */
    @Test
    public void testGetComentario() {
        System.out.println("getComentario");
        String unaRespuesta = "Blanco";
        PreguntaMultipleOpcion instance 
                = new PreguntaMultipleOpcion();
        String expResult = "De hecho, el caballo blanco "
                + "de San Martín era blanco.";
        instance.agregarComentarioRespuesta(unaRespuesta, 
                expResult);
        String result = instance.getComentario(unaRespuesta);
        assertEquals(expResult, result);
    }
    /**
     * Test of toString method, of class PreguntaMultipleOpcion.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("Blanco", Boolean.TRUE);
        map.put("Negro", Boolean.FALSE);
        map.put("Marroncito", Boolean.FALSE);
        map.put("Barcino", Boolean.FALSE);
        PreguntaMultipleOpcion instance 
                = new PreguntaMultipleOpcion("¿De qué "
                + "color era el caballo blanco de San "
                        + "Martín?", 30, map);
        String expResult = "¿De qué color era el caballo "
                + "blanco de San Martín?. \n"
                + "Tienes 30 segundos para responder. \nY "
                + "sus respuestas eran "
                + "{Negro=false, Blanco=true, Marroncito=false,"
                + " Barcino=false}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
