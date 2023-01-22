package com.barrios_irabedra.obligatorio.dominio;

import java.util.*;

/**
 *
 * @author Usuario
 */
public class PreguntaMultipleOpcion extends Pregunta {
    private HashMap<String, String> comentarioRespuesta;
    
    public PreguntaMultipleOpcion(String pregunta, 
            int tiempo, HashMap<String, Boolean> respuestas) {
        super(pregunta, tiempo, respuestas);
    }

    public PreguntaMultipleOpcion() {
        this.comentarioRespuesta = new HashMap<>();
    }

    public void agregarComentarioRespuesta(
            String unaRespuesta, String unComentario){
        this.comentarioRespuesta.put(unaRespuesta, 
                unComentario);
    }
    
    public String getComentario(String unaRespuesta){
        return this.comentarioRespuesta.get(unaRespuesta);
    }
    
    @Override
    public String toString() {
        return this.getPregunta() + ". \n" + "Tienes " 
                + this.getTiempo() 
                + " segundos para responder. \n"
                + "Y sus respuestas eran " 
                + this.getRespuestas();
    }
}
