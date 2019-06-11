package com.example.vigilante;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vigilante.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Object view;
    private ArrayList<String> incidentes;
    private CustomAdapterMain adapter;
    private FirebaseFirestore db;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), NovoIncidente.class));
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        db = FirebaseFirestore.getInstance();

        incidentes = new ArrayList<>();
        listView = findViewById(R.id.listView_main);

        db.collection("Incidentes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                incidentes.add(document.getData().get("titulo").toString());
                            }
                            adapter = new CustomAdapterMain(incidentes, R.layout.listview_main, getApplicationContext());
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
                                        if(document.getData().get("titulo").toString().equals(incidentes.get(position))){
                                            Intent intent = new Intent(MainActivity.this, activity_detalhe_incidente.class);
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

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null) {
            incidentes.clear();
            db.collection("Incidentes")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    incidentes.add(document.getData().get("titulo").toString());
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            //conta
            Tela_Perfil((View) view);
        } else if (id == R.id.nav_slideshow) {
            //carteira
            Tela_Carteira((View) view);
        } else if (id == R.id.nav_mapa) {
            Tela_Mapa((View) view);

        } else if (id == R.id.nav_historico) {
            //detalhe_incidente
            Tela_Historico((View) view);

        } else if (id == R.id.nav_tools) {
            Tela_Settings((View) view);

        } else if (id == R.id.nav_logout) {
            Tela_Login((View) view);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Tela_Login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void Tela_Carteira(View view){
        Intent intent = new Intent(this, activity_carteira.class);
        startActivity(intent);
    }

    public void Tela_Perfil(View view){
        Intent intent = new Intent(this, activity_detalhes_perfil.class);
        startActivity(intent);
    }

    public void Tela_Historico(View view){
        Intent intent = new Intent(this, activity_historico.class);
        startActivity(intent);
    }

    public void Tela_Mapa(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void Tela_Settings(View view){
        Intent intent = new Intent(this, activity_settings.class);
        startActivity(intent);
    }
}
