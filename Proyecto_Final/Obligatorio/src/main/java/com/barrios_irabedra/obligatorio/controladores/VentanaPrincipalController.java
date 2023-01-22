/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrios_irabedra.obligatorio.controladores;

import com.barrios_irabedra.obligatorio.dominio.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class VentanaPrincipalController implements Initializable {

    private Button[][] matrizBotones = new Button[8][8];
    private Button b = new Button();
    private Button btnPivot = new Button();
    private int panelAntesDeAyuda = 0;

    @FXML
    private Pane paneDragAndDrop;
    @FXML
    private Button btnComenzar;
    @FXML
    private Pane panelJuego;
    @FXML
    private Pane panelTextoDragAndDrop;
    @FXML
    private GridPane gridPaneCaminosPreguntas;
    @FXML
    private Text textPreguntaVF;
    @FXML
    private Button btnRespuestaVerdadera;
    @FXML
    private Button btnRespuestaFalsa;
    @FXML
    private Pane panePreguntaVF;
    @FXML
    private Pane panePreguntaMo;
    @FXML
    private Text textPreguntaMo;
    @FXML
    private Button btnRespuestaA;
    @FXML
    private Button btnRespuestaD;
    @FXML
    private Button btnRespuestaB;
    @FXML
    private Button btnRespuestaC;
    @FXML
    private Pane panePreguntaCortaRespuesta;
    @FXML
    private Text textPreguntaCortaRespuesta;
    @FXML
    private TextField txtFieldRespuestaCorta;
    @FXML
    private Button btnSubmitRespuestaCorta;
    @FXML
    private Label labelTimer;
    @FXML
    private Label labelTimerMo;
    @FXML
    private Label labelTimerCortaRespuesta;
    @FXML
    private Pane paneGifCorrecto;
    @FXML
    private Pane paneGiftIncorrecto;
    @FXML
    private Button btnContinuarEnGifCorrecto;
    @FXML
    private Button btnContinuarEnGifInCorrecto;
    @FXML
    private Text txtPreguntasDropeadas;
    @FXML
    private Pane paneTxtCantPreg;
    @FXML
    private Pane paneAyuda;
    @FXML
    private Pane paneOpcionesDerecha;
    @FXML
    private Button btnAyuda;
    @FXML
    private Button tbnVolverAlQuizz;
    @FXML
    private Button btnAcabar;
    @FXML
    private Button btnGenerarReporte;
    @FXML
    private Pane paneTituloDelJuego;
    @FXML
    private Pane txtErrorCargar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        panelJuego.setVisible(false);
        paneDragAndDrop.setVisible(true);
        btnComenzar.setVisible(true);
        panelTextoDragAndDrop.setVisible(true);
        paneTxtCantPreg.setVisible(true);
    }

    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleDrop(DragEvent event) {
        List<File> files = event.getDragboard().getFiles();
        Sistema.getInstance().recibirArchivos(files);
        int contPreguntaS = Sistema.getInstance().
                getListaTodasLasPreguntas().size();
        if (contPreguntaS < 39) {
            txtPreguntasDropeadas.setText(
                    "Preguntas cargadas: " 
                            + contPreguntaS + "/39");
        } else {
            txtPreguntasDropeadas.setText(
                    "Preguntas cargadas: 39/39");
        }
    }

    @FXML
    private void actionBtnComenzar(ActionEvent event) {
        if (Sistema.getInstance().cantidadTotalPreguntas() > 0) {
            crearMatrizBotones();
            asignoPreguntaBoton();
            panelTextoDragAndDrop.setVisible(false);
            paneDragAndDrop.setVisible(false);
            btnComenzar.setVisible(false);
            panePreguntaVF.setVisible(false);
            panelTextoDragAndDrop.setVisible(false);
            panePreguntaMo.setVisible(false);
            panePreguntaCortaRespuesta.setVisible(false);
            paneGifCorrecto.setVisible(false);
            paneGiftIncorrecto.setVisible(false);
            paneTxtCantPreg.setVisible(false);
            paneAyuda.setVisible(false);
            paneOpcionesDerecha.setVisible(true);
            paneTituloDelJuego.setVisible(true);
            gridPaneCaminosPreguntas.setVisible(true);
            panelJuego.setVisible(true);
            playuSonidoMenu();
        }
    }

    private void crearMatrizBotones() {
        Integer fil;
        Integer col;
        for (Node child : this.
                gridPaneCaminosPreguntas.getChildren()) {
            fil = this.gridPaneCaminosPreguntas.
                    getRowIndex(child);
            col = this.gridPaneCaminosPreguntas.
                    getColumnIndex(child);
            int f = fil == null ? 0 : fil;
            int c = col == null ? 0 : col;
            if (fil != null && col != null) {
                if (f == fil && c == col) {
                    this.matrizBotones[fil - 1][col - 1] 
                            = (Button) child;
                }
            }
        }
    }

    private void asignoPreguntaBoton() {
        int cantP = Sistema.getInstance().cantidadTotalPreguntas();
        int contP = cantP;
        int contador = 0;
        ArrayList<Pregunta> listaPreguntas = Sistema.
                getInstance().getListaTodasLasPreguntas();
        Iterator it = listaPreguntas.iterator();
        for (int i = 7; i >= 0 
                && (cantP >= 0) && it.hasNext(); i--) {
            this.matrizBotones[i][0].setUserData(it.next());
            activar(matrizBotones[i][0]);
            contador++;
            cantP--;
        }
        for (int i = 1; i < 8 
                && cantP >= 0 && it.hasNext(); i++) {
            this.matrizBotones[0][i].setUserData(it.next());
            activar(matrizBotones[0][i]);
            contador++;
            cantP--;
        }
        for (int i = 1; i < 8 
                && cantP >= 0 && it.hasNext(); i++) {
            this.matrizBotones[i][7].setUserData(it.next());
            activar(matrizBotones[i][7]);
            contador++;
            cantP--;
        }
        for (int i = 6; i > 1 
                && cantP >= 0 && it.hasNext(); i--) {
            this.matrizBotones[7][i].setUserData(it.next());
            activar(matrizBotones[7][i]);
            contador++;
            cantP--;
        }
        for (int i = 6; i > 1 
                && cantP >= 0 && it.hasNext(); i--) {
            this.matrizBotones[i][2].setUserData(it.next());
            activar(matrizBotones[i][2]);
            contador++;
            cantP--;
        }
        for (int i = 3; i < 6 
                && cantP >= 0 && it.hasNext(); i++) {
            this.matrizBotones[2][i].setUserData(it.next());
            activar(matrizBotones[2][i]);
            contador++;
            cantP--;
        }
        for (int i = 3; i < 6 
                && cantP >= 0 && it.hasNext(); i++) {
            this.matrizBotones[i][5].setUserData(it.next());
            activar(matrizBotones[i][5]);
            contador++;
            cantP--;
        }
        if (cantP >= 0 && it.hasNext()) {
            this.matrizBotones[5][4].setUserData(it.next());
            activar(matrizBotones[5][4]);
            contador++;
            cantP--;
        }
        if (cantP == 0) {
            desactivarElRestoDeBotones(contador);
        }
    }

    private void desactivarElRestoDeBotones(int contador) {
        for (int i = 7; i >= 0; i--) {
            if (contador == 0) {
                matrizBotones[i][0].setStyle(
                        "-fx-background-color: #696969");
                matrizBotones[i][0].setDisable(true);
            } else {
                contador--;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (contador == 0) {
                matrizBotones[0][i].setStyle(
                        "-fx-background-color: #696969");
                matrizBotones[0][i].setDisable(true);
            } else {
                contador--;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (contador == 0) {
                matrizBotones[i][7].setStyle(
                        "-fx-background-color: #696969");
                matrizBotones[i][7].setDisable(true);
            } else {
                contador--;
            }
        }
        for (int i = 6; i > 1; i--) {
            if (contador == 0) {
                matrizBotones[7][i].setStyle(
                        "-fx-background-color: #696969");
                matrizBotones[7][i].setDisable(true);
            } else {
                contador--;
            }
        }
        for (int i = 6; i > 1; i--) {
            if (contador == 0) {
                matrizBotones[i][2].setStyle(
                        "-fx-background-color: #696969");
                matrizBotones[i][2].setDisable(true);
            } else {
                contador--;
            }
        }
        for (int i = 3; i < 6; i++) {
            if (contador == 0) {
                matrizBotones[2][i].setStyle(
                        "-fx-background-color: #696969");
                matrizBotones[2][i].setDisable(true);
            } else {
                contador--;
            }
        }
        for (int i = 3; i < 6; i++) {
            if (contador == 0) {
                matrizBotones[i][5].setStyle(
                        "-fx-background-color: #696969");
                matrizBotones[i][5].setDisable(true);
            } else {
                contador--;
            }
        }
        if (contador == 0) {
            matrizBotones[5][4].setStyle(
                    "-fx-background-color: #696969");
            matrizBotones[5][4].setDisable(true);
        } else {
            contador--;
        }
    }

    private void activar(final Button b) {
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Pregunta p = (Pregunta) b.getUserData();
                btnGenerarReporte.setDisable(true);
                if (p instanceof PreguntaVF) {
                    iniciarPreguntaVF(p);
                    btnPivot = b;
                } else if (p instanceof PreguntaCortaRespuesta) {
                    iniciarPreguntaCortaRespuesta(p);
                    btnPivot = b;
                } else if (p instanceof PreguntaMultipleOpcion) {
                    iniciarPreguntaMo(p);
                    btnPivot = b;
                }
            }

        });
    }

    private void iniciarPreguntaCortaRespuesta(Pregunta p) {
        gridPaneCaminosPreguntas.setVisible(false);
        paneTituloDelJuego.setVisible(false);
        btnAcabar.setDisable(true);
        panePreguntaCortaRespuesta.setVisible(true);
        textPreguntaCortaRespuesta.setText(p.getPregunta());
        btnSubmitRespuestaCorta.setUserData(p);
        labelTimerCortaRespuesta.textProperty().
                bind(p.getSegundosRestantes().asString());
        p.start();
    }

    private void iniciarPreguntaMo(Pregunta p) {
        gridPaneCaminosPreguntas.setVisible(false);
        paneTituloDelJuego.setVisible(false);
        btnAcabar.setDisable(true);
        panePreguntaMo.setVisible(true);
        textPreguntaMo.setText(p.getPregunta());
        btnRespuestaA.setText((String) p.
                getMapaRespuestas().keySet().toArray()[3]);
        btnRespuestaB.setText((String) p.
                getMapaRespuestas().keySet().toArray()[2]);
        btnRespuestaC.setText((String) p.
                getMapaRespuestas().keySet().toArray()[1]);
        btnRespuestaD.setText((String) p.
                getMapaRespuestas().keySet().toArray()[0]);
        btnRespuestaA.setUserData(p);
        btnRespuestaB.setUserData(p);
        btnRespuestaC.setUserData(p);
        btnRespuestaD.setUserData(p);
        labelTimerMo.textProperty().bind(p.
                getSegundosRestantes().asString());
        p.start();

    }

    private void iniciarPreguntaVF(Pregunta p) {
        gridPaneCaminosPreguntas.setVisible(false);
        paneTituloDelJuego.setVisible(false);
        btnAcabar.setDisable(true);
        panePreguntaVF.setVisible(true);
        textPreguntaVF.setText(p.getPregunta());
        btnRespuestaVerdadera.setUserData(p);
        btnRespuestaFalsa.setUserData(p);
        labelTimer.textProperty().bind(p.
                getSegundosRestantes().asString());
        p.start();
    }

    @FXML
    private void onActionRespuestaV(ActionEvent event) {
        PreguntaVF p 
                = (PreguntaVF) btnRespuestaVerdadera.
                        getUserData();
        int checkTiempo = Integer.parseInt(p.
                getSegundosRestantes().asString().get());
        if (checkTiempo > 0) {
            if (procesarRespuestaVF(p, "V")) {
                mostrarGifYSonidoCorrecto(p);
            } else {
                mostrarGifYSonidoIncorrecto(p);
            }
        } else {
            procesarRespuestaFueraDeTiempo(p, "4c6f6c69");
            mostrarGifYSonidoIncorrecto(p);
        }
    }

    private void procesarRespuestaFueraDeTiempo(Pregunta p, 
            String respuesta) {
        Sistema.getInstance().procesarRespuestaSeleccionada(p, 
                respuesta);
        btnPivot.setStyle("-fx-background-color: #000f3f");
        btnPivot.setDisable(true);
    }

    private boolean procesarRespuestaVF(PreguntaVF p, 
            String respuestaSeleccionada) {
        boolean check;
        if (Sistema.getInstance().
                procesarRespuestaSeleccionada(p, 
                        respuestaSeleccionada)) {
            btnPivot.setStyle("-fx-background-color: #01a8e2");
            check = true;
        } else {
            btnPivot.setStyle("-fx-background-color: #000f3f");
            check = false;
        }
        btnPivot.setDisable(true);
        return check;
    }

    @FXML
    private void onActionRespuestaF(ActionEvent event) {
        PreguntaVF p 
                = (PreguntaVF) btnRespuestaVerdadera.
                        getUserData();
        int checkTiempo = Integer.parseInt(p.
                getSegundosRestantes().asString().get());
        if (checkTiempo > 0) {
            if (procesarRespuestaVF(p, "F")) {
                mostrarGifYSonidoCorrecto(p);
            } else {
                mostrarGifYSonidoIncorrecto(p);
            }
        } else {
            procesarRespuestaFueraDeTiempo(p, "4c6f6c69");
            mostrarGifYSonidoIncorrecto(p);
        }
    }

    @FXML
    private void onActionbtnRespuestaA(ActionEvent event) {
        PreguntaMultipleOpcion p 
                = (PreguntaMultipleOpcion) btnPivot.getUserData();
        String respuestaSeleccionada 
                = (String) p.getMapaRespuestas().
                        keySet().toArray()[3];
        int checkTiempo = Integer.parseInt(p.
                getSegundosRestantes().asString().get());
        if (checkTiempo > 0) {
            if (procesarRespuestaMo(p, respuestaSeleccionada)) {
                mostrarGifYSonidoCorrecto(p);
            } else {
                mostrarGifYSonidoIncorrecto(p);
            }
        } else {
            procesarRespuestaFueraDeTiempo(p, "4c6f6c69");
            mostrarGifYSonidoIncorrecto(p);
        }
    }

    @FXML
    private void onActionBtnRespuestaB(ActionEvent event) {
        PreguntaMultipleOpcion p 
                = (PreguntaMultipleOpcion) btnPivot.
                        getUserData();
        String respuestaSeleccionada 
                = (String) p.getMapaRespuestas().keySet().
                        toArray()[2];
        int checkTiempo = Integer.parseInt(p.
                getSegundosRestantes().asString().get());
        if (checkTiempo > 0) {
            if (procesarRespuestaMo(p, respuestaSeleccionada)) {
                mostrarGifYSonidoCorrecto(p);
            } else {
                mostrarGifYSonidoIncorrecto(p);
            }
        } else {
            procesarRespuestaFueraDeTiempo(p, "4c6f6c69");
            mostrarGifYSonidoIncorrecto(p);
        }
    }

    @FXML
    private void onActionBtnRespuestaC(ActionEvent event) {
        PreguntaMultipleOpcion p 
                = (PreguntaMultipleOpcion) btnPivot.
                        getUserData();
        String respuestaSeleccionada 
                = (String) p.getMapaRespuestas().keySet().
                        toArray()[1];
        int checkTiempo = Integer.parseInt(p.
                getSegundosRestantes().asString().get());
        if (checkTiempo > 0) {
            if (procesarRespuestaMo(p, respuestaSeleccionada)) {
                mostrarGifYSonidoCorrecto(p);
            } else {
                mostrarGifYSonidoIncorrecto(p);
            }
        } else {
            procesarRespuestaFueraDeTiempo(p, "4c6f6c69");
            mostrarGifYSonidoIncorrecto(p);
        }
    }

    @FXML
    private void onActionBtnRespuestaD(ActionEvent event) {
        PreguntaMultipleOpcion p 
                = (PreguntaMultipleOpcion) btnPivot.getUserData();
        String respuestaSeleccionada 
                = (String) p.getMapaRespuestas().keySet().
                        toArray()[0];
        int checkTiempo = Integer.parseInt(p.
                getSegundosRestantes().asString().get());
        if (checkTiempo > 0) {
            if (procesarRespuestaMo(p, respuestaSeleccionada)) {
                mostrarGifYSonidoCorrecto(p);
            } else {
                mostrarGifYSonidoIncorrecto(p);
            }
        } else {
            procesarRespuestaFueraDeTiempo(p, "4c6f6c69");
            mostrarGifYSonidoIncorrecto(p);
        }
    }

    private void mostrarGifYSonidoCorrecto(Pregunta p) {
        if (p instanceof PreguntaVF) {
            panePreguntaVF.setVisible(false);
            btnRespuestaVerdadera.
                    getProperties().clear();
            //Limpia el map generado por el setUserData
            btnRespuestaFalsa.getProperties().clear();
        } else if (p instanceof PreguntaMultipleOpcion) {
            panePreguntaMo.setVisible(false);
            btnRespuestaA.getProperties().clear();
            btnRespuestaB.getProperties().clear();
            btnRespuestaC.getProperties().clear();
            btnRespuestaD.getProperties().clear();
        } else if (p instanceof PreguntaCortaRespuesta) {
            panePreguntaCortaRespuesta.setVisible(false);
            btnRespuestaA.getProperties().clear();
        }
        paneGifCorrecto.setVisible(true);
        playAudioCorrecto();
    }

    private void playAudioCorrecto() {
        InputStream in;
        btnPivot.setText("✓");
        try {
            in = new FileInputStream(
                    new File("src/main/resources/styles/ok.wav"));
            AudioStream audioOk = new AudioStream(in);
            AudioPlayer.player.start(audioOk);
        } catch (Exception e) {
        }
    }

    private void playAudioIncorrecto() {
        InputStream in;
        btnPivot.setText("X");
        try {
            in = new FileInputStream(new File(
                    "src/main/resources/styles/Wrong.wav"));
            AudioStream audioOk = new AudioStream(in);
            AudioPlayer.player.start(audioOk);
        } catch (Exception e) {
        }
    }

    private void mostrarGifYSonidoIncorrecto(Pregunta p) {
        if (p instanceof PreguntaVF) {
            panePreguntaVF.setVisible(false);
            btnRespuestaVerdadera.getProperties().clear();
//Limpia el map generado por el setUserData
            btnRespuestaFalsa.getProperties().clear();
        } else if (p instanceof PreguntaMultipleOpcion) {
            panePreguntaMo.setVisible(false);
            btnRespuestaA.getProperties().clear();
            btnRespuestaB.getProperties().clear();
            btnRespuestaC.getProperties().clear();
            btnRespuestaD.getProperties().clear();
        } else if (p instanceof PreguntaCortaRespuesta) {
            panePreguntaCortaRespuesta.setVisible(false);
            btnRespuestaA.getProperties().clear();
        }
        paneGiftIncorrecto.setVisible(true);
        playAudioIncorrecto();
    }

    private boolean procesarRespuestaMo(
            PreguntaMultipleOpcion p, 
            String respuestaSeleccionada) {
        boolean check;
        if (Sistema.getInstance().
                procesarRespuestaSeleccionada(p, 
                        respuestaSeleccionada)) {
            btnPivot.setStyle("-fx-background-color: #01a8e2");
            check = true;
        } else {
            btnPivot.setStyle("-fx-background-color: #000f3f");
            check = false;
        }
        btnPivot.setDisable(true);
        return check;
    }

    @FXML
    private void onActionBtnSubmit(ActionEvent event) {
        String respuestaSeleccionada 
                = txtFieldRespuestaCorta.getText();
        if (!"".equals(respuestaSeleccionada)) {
            PreguntaCortaRespuesta p 
                    = (PreguntaCortaRespuesta) btnPivot.
                            getUserData();
            int checkTiempo = Integer.parseInt(p.
                    getSegundosRestantes().asString().get());
            if (checkTiempo > 0) {
                if (procesarRespuestaCortaRespuesta(p, 
                        respuestaSeleccionada)) {
                    mostrarGifYSonidoCorrecto(p);
                } else {
                    mostrarGifYSonidoIncorrecto(p);
                }
            } else {
                procesarRespuestaFueraDeTiempo(p, "4c6f6c69");
                mostrarGifYSonidoIncorrecto(p);
            }
        }
    }

    private boolean procesarRespuestaCortaRespuesta(
            PreguntaCortaRespuesta p, 
            String respuestaSeleccionada) {
        boolean check;
        if (Sistema.getInstance().
                procesarRespuestaSeleccionada(p, 
                        respuestaSeleccionada)) {
            btnPivot.setStyle("-fx-background-color: #01a8e2");
            check = true;
        } else {
            btnPivot.setStyle("-fx-background-color: #000f3f");
            check = false;
        }
        btnPivot.setDisable(true);
        return check;
    }

    @FXML
    private void onActionContinuarEnGifCorrecto(
            ActionEvent event) {
        paneGifCorrecto.setVisible(false);
        btnAcabar.setDisable(false);
        btnGenerarReporte.setDisable(false);
        gridPaneCaminosPreguntas.setVisible(true);
        paneTituloDelJuego.setVisible(true);
    }

    @FXML
    private void onActionContinuarEnGifInCorrecto(
            ActionEvent event) {
        paneGiftIncorrecto.setVisible(false);
        btnAcabar.setDisable(false);
        btnGenerarReporte.setDisable(false);
        gridPaneCaminosPreguntas.setVisible(true);
        paneTituloDelJuego.setVisible(true);
    }

    @FXML
    private void onActionBotonAyuda(ActionEvent event) {
        paneOpcionesDerecha.setVisible(false);
        if (gridPaneCaminosPreguntas.isVisible()) {
            panelAntesDeAyuda = 0;
        } else if (panePreguntaCortaRespuesta.isVisible()) {
            panelAntesDeAyuda = 1;
        } else if (panePreguntaMo.isVisible()) {
            panelAntesDeAyuda = 2;
        } else if (panePreguntaVF.isVisible()) {
            panelAntesDeAyuda = 3;
        }
        switch (panelAntesDeAyuda) {
            case 0:
                gridPaneCaminosPreguntas.setVisible(false);
                paneTituloDelJuego.setVisible(false);
                break;
            case 1:
                panePreguntaCortaRespuesta.setVisible(false);
                break;
            case 2:
                panePreguntaMo.setVisible(false);
                break;
            case 3:
                panePreguntaVF.setVisible(false);
                break;
        }
        paneAyuda.setVisible(true);
    }

    @FXML
    private void onActionBbnVolverAlQuizz(ActionEvent event) {
        paneAyuda.setVisible(false);
        switch (panelAntesDeAyuda) {
            case 0:
                gridPaneCaminosPreguntas.setVisible(true);
                paneTituloDelJuego.setVisible(true);
                break;
            case 1:
                panePreguntaCortaRespuesta.setVisible(true);
                break;
            case 2:
                panePreguntaMo.setVisible(true);
                break;
            case 3:
                panePreguntaVF.setVisible(true);
                break;
        }
        paneOpcionesDerecha.setVisible(true);
    }

    @FXML
    private void onActionbtnAcabar(ActionEvent event) {
        sonidoExit();
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException ex) {
        }
        System.exit(911);
    }

    private void sonidoExit() {
        InputStream in;
        try {
            in = new FileInputStream(new File(
                    "src/main/resources/styles/Exit.wav"));
            AudioStream audioOk = new AudioStream(in);
            AudioPlayer.player.start(audioOk);
        } catch (Exception e) {
        }
    }

    private void playuSonidoMenu() {

        try {
            File musicPath = new File(
                    "src/main/resources/styles/"
                            + "sonidoEspiral.wav");
            AudioInputStream audioInput 
                    = AudioSystem.getAudioInputStream(musicPath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
        }
    }

    @FXML
    private void onActionBtnGenerarReporte(ActionEvent event) {
        if (Sistema.getInstance().
                getRespuestasSeleccionadas().size() > 0) {
            Sistema.getInstance().crearReporte();
        }
    }

}
