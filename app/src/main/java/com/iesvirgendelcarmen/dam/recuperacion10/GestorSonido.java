package com.iesvirgendelcarmen.dam.recuperacion10;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by matinal on 01/06/2018.
 */

public class GestorSonido extends AppCompatActivity {

    SoundPool sndPool;
    Context pContext;
    private float frecuencia = 1.0f;
    private float balance = 0.5f;
    private float derecho = 1.0f;
    private float izquierdo = 1.0f;
    private float volumen = 0.5f;

    public GestorSonido(Context appContext){
        sndPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 100);
        pContext = appContext;

    }

    public void suena (int sound_id){
        sndPool.play(sound_id,1,1,1,0,frecuencia);
    }

    public void unloadAll(){
        sndPool.release();
    }

    public int carga(int sound_id){
        return sndPool.load(pContext,sound_id,1);
    }

    public void ajustarFrecuencia(float rate){
        frecuencia = rate;
        if (rate < 0.01f) frecuencia = 0.01f;
        if (rate > 2.0f) frecuencia = 2.0f;
    }

    public void ajustarBalance (float balVal) {
        this.balance = balVal;
        ajustarVolumen(volumen);
    }

    public void ajustarVolumen (float vol) {
        this.volumen = vol;
        if (balance < 1.0f) {
            izquierdo = vol;
            derecho = vol*balance;
        } else {
            derecho = vol;
            izquierdo = vol*(2.0f - balance);
        }
    }

}
