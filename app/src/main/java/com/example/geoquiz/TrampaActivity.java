package com.example.geoquiz;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TrampaActivity extends AppCompatActivity{
private static final String EXTRA_ANSWER_IS_TRUE="com.example.geoquiz";
private static final String EXTRA_RESPUESTA_MOSTRADA="com.example.geoquiz_:respuesta_mostrada";
private static final String TAG="QuiszActivity";
private static final String KEY_INDEX="index";
private static final int CODIGO_TRAMPA_REQUERIDO=0;
private boolean mAnswerIsTrue;
private TextView mTextoRespuesta;
private Button mBotonRespuesta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trampa);
        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        mTextoRespuesta=(TextView)findViewById(R.id.Text_respuesta);

        mBotonRespuesta=(Button)findViewById(R.id.button_mostrar_respuesta);
        mBotonRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mTextoRespuesta.setText(R.string.true_button);
                    mTextoRespuesta.setTextColor(Color.parseColor("#00e676"));
                }else{
                    mTextoRespuesta.setText(R.string.lbl_false_button);
                    mTextoRespuesta.setTextColor(Color.parseColor("#e60300"));
                }
            }
        });
    }

    public  static boolean seMostroRespuesta(Intent result){
        return result.getBooleanExtra(EXTRA_RESPUESTA_MOSTRADA,false);
    }


    private void setResultPreguntaMostrada(boolean isRespuestaMostrada){
        Intent data=new Intent();
        data.putExtra(EXTRA_RESPUESTA_MOSTRADA,isRespuestaMostrada);
        setResult(RESULT_OK,data);
    }



    public static Intent nuevoIntent(Context packageContext,boolean respuestaCorrecta){
        Intent intent=new Intent(packageContext,TrampaActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,respuestaCorrecta);
        return intent;
    }




    }


