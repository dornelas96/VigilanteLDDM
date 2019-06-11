package com.example.vigilante;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class registrar extends AppCompatActivity {

    private FirebaseFirestore db;
    String nome,senha,senha2,dataNasc,email,nIncidentes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        db = FirebaseFirestore.getInstance();
        Button button = findViewById(R.id.login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editNome = findViewById(R.id.username);
                EditText editSenha = findViewById(R.id.password);
                EditText editSenha2 = findViewById(R.id.password2);
                EditText editDataNasc = findViewById(R.id.dat_nascimento);
                EditText editEmail = findViewById(R.id.email);


                // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                // String data = sdf.format(currentTime);
                //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                //dataNasc = sdf.format(editDataNasc);

                nome = editNome.getText().toString();
                senha = editSenha.getText().toString();
                senha2 = editSenha2.getText().toString();
                email = editEmail.getText().toString();
                dataNasc = editDataNasc.getText().toString();
                nIncidentes = "0";

                Usuario usuario = new Usuario(nome, senha, dataNasc ,email ,nIncidentes);

                db.collection("Usuarios").add(usuario).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent intent = new Intent(registrar.this, MainActivity.class);
                        intent.putExtra("nome", nome);
                        intent.putExtra("senha", senha);
                        intent.putExtra("dataNasc",dataNasc );
                        intent.putExtra("email", email);
                        intent.putExtra("nIncidentes", nIncidentes);
                        startActivity(intent);
                        finish();
                    }
                });



            }
        });


    }




}
