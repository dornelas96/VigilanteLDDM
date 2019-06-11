package com.example.vigilante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.QueryDocumentSnapshot;

public class activity_detalhes_perfil extends AppCompatActivity {

    String nome;
    String email;
    String dataNasc;
    String nIncidentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_perfil);

        Usuario usuario = new Usuario();

        Intent intent = getIntent();

        nome = usuario.getNome();
        email = usuario.getEmail();
        dataNasc = usuario.getDataNasc();
        nIncidentes = usuario.getnIncidentes();

        /*
        nome = intent.getStringExtra("nome");
        email = intent.getStringExtra("email");
        dataNasc = intent.getStringExtra("dataNasc");
        nIncidentes = intent.getStringExtra("nIncidentes");*/

        TextView nomeText = findViewById(R.id.text_view_id5);
        TextView dataNascText = findViewById(R.id.text_view_horario2);
        TextView emailText = findViewById(R.id.text_view_descricao2);
        TextView nIncidentesText = findViewById(R.id.text_view_detalhes2);

        nomeText.setText(nome);
        dataNascText.setText(dataNasc);
        emailText.setText(email);
        nIncidentesText.setText(nIncidentes);


    }
}
