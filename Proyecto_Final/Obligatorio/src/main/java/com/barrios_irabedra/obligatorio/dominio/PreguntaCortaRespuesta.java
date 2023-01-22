/*
 * To change this license header, choose License
 * Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrios_irabedra.obligatorio.dominio;

import java.util.HashMap;

/**
 *
 * @author Usuario
 */
public class PreguntaCortaRespuesta extends Pregunta {

    public PreguntaCortaRespuesta(String pregunta,
            int tiempo, HashMap<String, Boolean> respuestas) {
        super(pregunta, tiempo, respuestas);
    }

    public PreguntaCortaRespuesta() {
    }

    @Override
    public String toString() {
        return this.getPregunta() + ". \n"
                + "Tienes " + this.getTiempo()
                + " segundos para responder. \n"
                + "Y sus respuesta correcta era "
                + this.getRespuestas();
    }

}
