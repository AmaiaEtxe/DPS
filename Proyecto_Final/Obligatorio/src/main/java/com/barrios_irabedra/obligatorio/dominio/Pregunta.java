package com.barrios_irabedra.obligatorio.dominio;

// @author Juan Barrios, Juan Irabedra
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Pregunta {

    private String pregunta;
    private int tiempo;
    private HashMap<String, Boolean> respuestas;
    private SimpleIntegerProperty segundosRestantes;
    private TimerPregunta timerPregunta;

    //Acceso y modificacion
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public SimpleIntegerProperty getSegundosRestantes() {
        return segundosRestantes;
    }

    public void setSegundosRestantes(SimpleIntegerProperty 
            segundosRestantes) {
        this.segundosRestantes = segundosRestantes;
    }

    public void disminuirTiempoRestante() {
        Platform.runLater(() -> {
            this.getSegundosRestantes().set(
                    getSegundosRestantes().subtract(1).get());
        });
    }

    public void agregarRespuesta(String respuesta, 
            Boolean valorVerdad) {
        this.respuestas.put(respuesta, valorVerdad);
    }

    public String getRespuestas() {
        return this.respuestas.toString();
    }

    public HashMap<String, Boolean> getMapaRespuestas() {
        return this.respuestas;
    }

    //Constructor con params
    public Pregunta(String nombre, int tiempo, 
            HashMap<String, Boolean> respuestas) {
        this.pregunta = nombre;
        this.tiempo = tiempo;
        this.respuestas = respuestas;
        this.segundosRestantes 
                = new SimpleIntegerProperty(tiempo);
        this.timerPregunta = new TimerPregunta();
    }

    //Constructor sin params.
    public Pregunta() {
        this.tiempo = 30;
        this.respuestas = new HashMap<>();
        this.segundosRestantes 
                = new SimpleIntegerProperty(this.tiempo);
        this.timerPregunta = new TimerPregunta();
    }

    @Override
    public String toString() {
        return this.getPregunta() + ". \n" + "Tienes " + 
                this.getTiempo() 
                + " segundos para responder. \n"
                + "Y sus respuestas eran " 
                + this.getRespuestas();
    }

    private class TimerPregunta extends TimerTask {

        @Override
        public void run() {
            if (getSegundosRestantes().get() > 0) {
                disminuirTiempoRestante();
            } else {
                stop();
            }
        }
    }

    public void stop() {
        this.timerPregunta.cancel();
    }

    public void start() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerPregunta, 0, 1000);
    }
}
