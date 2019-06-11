package com.example.vigilante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class activity_detalhe_incidente extends AppCompatActivity {

    String titulo;
    String descricao;
    String endereco;
    String dataHora;
    String detalhes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_incidente);

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String data = sdf.format(currentTime);

        Intent intent = getIntent();

        titulo = intent.getStringExtra("titulo");
        descricao = intent.getStringExtra("descricao");
        endereco = intent.getStringExtra("endereco");
        dataHora = intent.getStringExtra("dataHora");
        detalhes = intent.getStringExtra("detalhes");

        TextView tituloText = findViewById(R.id.text_view_id3);
        TextView descricaoText = findViewById(R.id.text_view_descricao2);
        TextView enderecoText = findViewById(R.id.text_view_endereco2);
        TextView dataHoraText = findViewById(R.id.text_view_horario2);
        TextView detalhesText = findViewById(R.id.text_view_detalhes2);

        tituloText.setText(titulo);
        descricaoText.setText(descricao);
        enderecoText.setText(endereco);
        dataHoraText.setText(data + " - " + dataHora);
        detalhesText.setText(detalhes);

    }
}
