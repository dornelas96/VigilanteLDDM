package com.example.vigilante;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class activity_historico extends AppCompatActivity {

    private ArrayList<String> incidentes;
    private CustomAdapterHistorico adapter;
    private FirebaseFirestore db;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        db = FirebaseFirestore.getInstance();

        incidentes = new ArrayList<>();
        listView = findViewById(R.id.listView_historico);

        db.collection("Incidentes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                incidentes.add(document.getData().get("titulo").toString() + " - " + document.getData().get("dataHora").toString());
                            }
                            adapter = new CustomAdapterHistorico(incidentes, R.layout.listview_historico, getApplicationContext());
                            listView.setAdapter(adapter);
                        }
                    }
                });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                db.collection("Incidentes")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if((document.getData().get("titulo").toString() + " - " + document.getData().get("dataHora").toString()).equals(incidentes.get(position))){
                                            Intent intent = new Intent(activity_historico.this, activity_detalhe_incidente.class);
                                            intent.putExtra("titulo", document.getData().get("titulo").toString());
                                            intent.putExtra("descricao", document.getData().get("descricao").toString());
                                            intent.putExtra("endereco", document.getData().get("endereco").toString());
                                            intent.putExtra("dataHora", document.getData().get("dataHora").toString());
                                            intent.putExtra("detalhes", document.getData().get("detalhes").toString());
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });



    }
}
