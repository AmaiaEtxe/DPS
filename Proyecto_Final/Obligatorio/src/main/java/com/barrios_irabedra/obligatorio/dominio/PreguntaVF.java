package com.barrios_irabedra.obligatorio.dominio;

import java.util.HashMap;

/**
 *
 * @author Usuario
 */
public class PreguntaVF extends Pregunta{

    public PreguntaVF(String pregunta, int tiempo, 
            HashMap<String, Boolean> respuestas) {
        super(pregunta, tiempo, respuestas);
    }
    
    public PreguntaVF() {
    }
    


    @Override
    public String toString() {
         return this.getPregunta() + ". \n" + "Tienes " 
                 + this.getTiempo() 
                 + " segundos para responder. \n"
                + "Y su respuesta correcta era " 
                 + this.getRespuestas();
    }
    
    
    
}
