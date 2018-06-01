package com.iesvirgendelcarmen.dam.recuperacion10;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class Sonido02 extends AppCompatActivity {

    TextView sonidosMedia, cancion, duracion;
    ImageView imagen;
    ImageButton retro, pause, play, next;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    double tiempoFinal;
    double tiempoPasado;
    int avance = 2000;
    int retroceso = 2000;

    private Handler controladorTiempo = new Handler();

    private Runnable actualizaBarra = new Runnable() {
        @Override
        public void run() {
            tiempoPasado = mediaPlayer.getCurrentPosition();
            seekBar.setProgress((int)tiempoPasado);
            double timeRestante = tiempoFinal - tiempoPasado;
            duracion.setText(String.format("%d min,%d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) timeRestante),
                    TimeUnit.MILLISECONDS.toSeconds((long) timeRestante)-
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)timeRestante))));
            controladorTiempo.postDelayed(this,100);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonido02);

        sonidosMedia = findViewById(R.id.sonidosConMedia);
        cancion = findViewById(R.id.cancion);
        duracion = findViewById(R.id.duracion);
        imagen = findViewById(R.id.imagen);
        retro = findViewById(R.id.retroceso);
        pause = findViewById(R.id.pause);
        play = findViewById(R.id.play);
        next = findViewById(R.id.avanzar);
        seekBar = findViewById(R.id.seekbar);

        seekBar.setClickable(false);
        mediaPlayer = MediaPlayer.create(this,R.raw.codigo_davinci);

        tiempoFinal = mediaPlayer.getDuration();
        seekBar.setMax((int)tiempoFinal);
        cancion.setText("codigo_davinci.mp3");

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((tiempoPasado + avance)<= tiempoFinal){
                    tiempoPasado = tiempoPasado + avance;
                    mediaPlayer.seekTo((int)tiempoPasado);
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                tiempoPasado = mediaPlayer.getCurrentPosition();
                seekBar.setProgress((int) tiempoPasado);
                controladorTiempo.postDelayed(actualizaBarra,100);
            }
        });

        retro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((tiempoPasado - retroceso)>0){
                    tiempoPasado = tiempoPasado - retroceso;
                    mediaPlayer.seekTo((int) tiempoPasado);
                }
            }
        });

    }

}