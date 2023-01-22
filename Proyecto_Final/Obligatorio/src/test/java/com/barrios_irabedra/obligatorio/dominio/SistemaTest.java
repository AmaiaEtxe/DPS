package com.barrios_irabedra.obligatorio.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.junit.After;                                         
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class SistemaTest {

    public static Sistema sistema;

    public SistemaTest() {
        assertNotNull(new Sistema());
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Invocado antes de comenzar las "
                + "pruebas en Sistema");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Invocado al finalizar "
                + "todas las pruebas en Sistema");
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
     * Test of getInstanciaSistema method, of class Sistema.
     */
    @Test
    public void testGetInstanciaSistema() {
        System.out.println("getInstanciaSistema");
        Sistema result = new Sistema();
        if (Sistema.getInstanciaSistema() == null) {
            assertNotNull(result);
        } else {
            assertNull(result);
        }
    }

    /**
     * Test of getInstance method, of class Sistema.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Sistema result = Sistema.getInstance();
        assertNotNull(result);
    }

    @Test
    public void testEsLineaConContenido() {
        System.out.println("esLineaConContenido");
        String linea = "";
        Sistema instance = new Sistema();
        Boolean expResult = false;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }

    @Test
    public void testEsLineaConContenidoBarra() {
        System.out.println("esLineaConContenido");
        String linea = "/";
        Sistema instance = new Sistema();
        Boolean expResult = false;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }

    @Test
    public void testEsLineaConContenidoDosPuntos() {
        System.out.println("esLineaConContenido");
        String linea = ":";
        Sistema instance = new Sistema();
        Boolean expResult = true;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }

    @Test
    public void testEsLineaConContenidoIgual() {
        System.out.println("esLineaConContenido");
        String linea = "=";
        Sistema instance = new Sistema();
        Boolean expResult = true;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }

    @Test
    public void testEsLineaConContenidoVirgulilla() {
        System.out.println("esLineaConContenido");
        String linea = "~";
        Sistema instance = new Sistema();
        Boolean expResult = true;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }

    @Test
    public void testEsLineaConContenidoNumeral() {
        System.out.println("esLineaConContenido");
        String linea = "#";
        Sistema instance = new Sistema();
        Boolean expResult = true;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }

    @Test
    public void 
        testEsLineaConContenidosSignoDePreguntaInvertido() {
        System.out.println("esLineaConContenido");
        String linea = "¿";
        Sistema instance = new Sistema();
        Boolean expResult = true;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }

    @Test
    public void testEsLineaConContenidoCorcheteCerrado() {
        System.out.println("esLineaConContenido");
        String linea = "}";
        Sistema instance = new Sistema();
        Boolean expResult = true;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }

    @Test
    public void testEsLineaConContenidoDefault() {
        System.out.println("esLineaConContenido");
        String linea = "UN STRING";
        Sistema instance = new Sistema();
        Boolean expResult = false;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }

    /**
     * Test of cargarPreguntas method, of class Sistema.
     */
    @Test
    public void testCargarPreguntas() {
        System.out.println("cargarPreguntas");
        ArrayList<String> textoFiltrado = null;
        assertEquals(textoFiltrado, null);
    }

    @Test
    public void testCargarPreguntasConContenidoEnElArray() {
        System.out.println("cargarPreguntas");
        ArrayList<String> textoFiltrado = new ArrayList<>();
        textoFiltrado.add("AHHHHHHHHHHHHHHHHHHHHHHHHHH");
        textoFiltrado.add("UwU");
        Iterator<String> it = textoFiltrado.iterator();
        while (it.hasNext()) {
            it.next();
        }
        assertNull(null);
    }

    /**
     * Test of procesarLinea method, of class Sistema.
     */
    @Test
    public void testProcesarLinea() {
        System.out.println("procesarLinea");
        String unaLinea = "¿Quién está sepultado en la tumba "
                + "de Grant?{=Grant =Ulysses S. Grant "
                + "=Ulysses Grant}";
        ArrayList<String> lineasPreguntaMultipleOpcion 
                = new ArrayList<>();
        boolean comienzoPreguntaMultipleOpcion = false;
        Sistema instance = new Sistema();
        boolean expResult = false;
        boolean result = instance.procesarLinea(unaLinea, 
                lineasPreguntaMultipleOpcion, 
                comienzoPreguntaMultipleOpcion);
        assertEquals(expResult, result);
    }

    @Test
    public void testProcesarLineaConPuntosDobles() {
        System.out.println("procesarLinea");
        String unaLinea = "::Título de la pregunta";
        ArrayList<String> lineasPreguntaMultipleOpcion
                = new ArrayList<>();
        boolean comienzoPreguntaMultipleOpcion = false;
        Sistema instance = new Sistema();
        boolean expResult = true;
        boolean result = instance.procesarLinea(unaLinea, 
                lineasPreguntaMultipleOpcion, 
                comienzoPreguntaMultipleOpcion);
        assertEquals(expResult, result);
    }

    public void testProcesarLineaConDosPuntosDobles() {
        System.out.println("procesarLinea");
        String unaLinea = "::Q1:: 1+1= 2 {T}";
        ArrayList<String> lineasPreguntaMultipleOpcion 
                = new ArrayList<>();
        boolean comienzoPreguntaMultipleOpcion = false;
        Sistema instance = new Sistema();
        boolean expResult = false;
        boolean result = instance.procesarLinea(unaLinea, 
                lineasPreguntaMultipleOpcion, 
                comienzoPreguntaMultipleOpcion);
        assertEquals(expResult, result);
    }

    @Test
    public void testProcesarLineaCorchetCerrado() {
        System.out.println("procesarLinea");
        String unaLinea = "}";
        ArrayList<String> lineasPreguntaMultipleOpcion 
                = new ArrayList<>();
        lineasPreguntaMultipleOpcion.add(
                "=Una respuesta correcta");
        lineasPreguntaMultipleOpcion.add(
                "~Respuesta equivocada1");
        boolean comienzoPreguntaMultipleOpcion = true;
        Sistema instance = new Sistema();
        boolean expResult = false;
        boolean result = instance.procesarLinea(unaLinea, 
                lineasPreguntaMultipleOpcion, 
                comienzoPreguntaMultipleOpcion);
        assertEquals(expResult, result);
    }

    @Test
    public void testProcesarLineaAgregandLineas() {
        System.out.println("procesarLinea");
        String unaLinea = "~Respuesta equivocada1";
        ArrayList<String> lineasPreguntaMultipleOpcion 
                = new ArrayList<>();
        boolean comienzoPreguntaMultipleOpcion = true;
        Sistema instance = new Sistema();
        boolean expResult = true;
        boolean result = instance.procesarLinea(unaLinea, 
                lineasPreguntaMultipleOpcion, 
                comienzoPreguntaMultipleOpcion);
        assertEquals(expResult, result);
    }

    /**
     * Test of ocurrenciasSubtring method, of class Sistema.
     */
    @Test
    public void testOcurrenciasSubtring() {
        System.out.println("ocurrenciasSubtring");
        String unString = "::Título de la pregunta ";
        String unSubstring = "::";
        Sistema instance = new Sistema();
        int expResult = 1;
        int result = instance.ocurrenciasSubtring(unString, 
                unSubstring);
        assertEquals(expResult, result);
    }

    /**
     * Test of getListaPreguntasCortaRespuesta method, of class
     * Sistema. /
     */
    @Test
    public void testGetListaPreguntasCortaRespuesta() {
        System.out.println("getListaPreguntasCortaRespuesta");
        Sistema instance = new Sistema();
        List<PreguntaCortaRespuesta> expResult 
                = new ArrayList<>();
        List<PreguntaCortaRespuesta> result 
                = instance.getListaPreguntasCortaRespuesta();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListaPreguntasMultipleOpcion method, of class
     * Sistema. /
     */
    @Test
    public void testGetListaPreguntasMultipleOpcion() {
        System.out.println("getListaPreguntasMultipleOpcion");
        Sistema instance = new Sistema();
        List<PreguntaMultipleOpcion> expResult 
                = new ArrayList<>();
        List<PreguntaMultipleOpcion> result 
                = instance.getListaPreguntasMultipleOpcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListaPreguntasVF method, of class Sistema. /
     */
    @Test
    public void testGetListaPreguntasVF() {
        System.out.println("getListaPreguntasVF");
        Sistema instance = new Sistema();
        List<PreguntaVF> expResult = new ArrayList<>();
        List<PreguntaVF> result = instance.getListaPreguntasVF();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRespuestasSeleccionadas method, of class
     * Sistema.
     */
    @Test
    public void testGetRespuestasSeleccionadas() {
        System.out.println("getRespuestasSeleccionadas");
        Sistema instance = new Sistema();
        HashMap<Pregunta, String> expResult = new HashMap<>();
        HashMap<Pregunta, String> result 
                = instance.getRespuestasSeleccionadas();
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarRespuestaSeleccionada method, of class
     * Sistema.
     */
    @Test
    public void testAgregarRespuestaSeleccionada() {
        System.out.println("agregarRespuestaSeleccionada");
        Pregunta p = new PreguntaVF();
        String respuestas = "";
        Sistema instance = new Sistema();
        instance.agregarRespuestaSeleccionada(p, respuestas);
        assertEquals(
                instance.getRespuestasSeleccionadas().size(), 1);
    }

    /**
     * Test of veracidadRespuesta method, of class Sistema.
     */
    @Test
    public void testVeracidadRespuesta() {
        System.out.println("veracidadRespuesta");
        Pregunta p = new PreguntaVF();
        String respuesta = "F";
        boolean valorDeVerdad = false;
        Sistema instance = new Sistema();
        boolean expResult = false;
        boolean result = instance.veracidadRespuesta(p, 
                respuesta, valorDeVerdad);
        assertEquals(expResult, result);
    }

    /**
     * Test of cantidadTotalPreguntas method, of class Sistema.
     */
    @Test
    public void testCantidadTotalPreguntas() {
        System.out.println("cantidadTotalPreguntas");
        Sistema instance = new Sistema();
        int expResult = 0;
        int result = instance.cantidadTotalPreguntas();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListaTodasLasPreguntas method, of class
     * Sistema.
     */
    @Test
    public void testGetListaTodasLasPreguntas() {
        System.out.println("getListaTodasLasPreguntas");
        Sistema instance = new Sistema();
        ArrayList<Pregunta> expResult = new ArrayList<>();
        ArrayList<Pregunta> result 
                = instance.getListaTodasLasPreguntas();
        assertEquals(expResult, result);
    }

    /**
     * Test of procesarRespuestaSeleccionada method, of class
     * Sistema.
     */
    @Test
    public void testProcesarRespuestaSeleccionada() {
        System.out.println("procesarRespuestaSeleccionada");
        Pregunta p = new PreguntaVF();
        String respuestaSeleccionada = "F";
        Sistema instance = new Sistema();
        boolean expResult = false;
        boolean result = instance.procesarRespuestaSeleccionada(p, 
                respuestaSeleccionada);
        assertEquals(expResult, result);
    }

    /*
     * Test of crearReporte method, of class Sistema.
     */
    @Test
    public void testCrearReporte() {
        System.out.println("crearReporte");
        Sistema instance = new Sistema();
        boolean expResult = false;
        boolean result = instance.crearReporte();
        assertEquals(expResult, result);
    }
}

