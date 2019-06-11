package com.example.vigilante;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NovoIncidente extends AppCompatActivity {

    FirebaseFirestore db;
    String titulo;
    String descricao;
    String endereco;
    String dataHora;
    String detalhes;

    ImageView ivImage;
    Integer REQUEST_CAMERA=1, SELECT_FILE=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novoincidente);

        Button button = findViewById(R.id.reportar);

        db = FirebaseFirestore.getInstance();

        ivImage = (ImageView) findViewById(R.id.ivImage);

        Button fab1 = (Button) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTitulo = findViewById(R.id.titulo);
                EditText editDescricao = findViewById(R.id.descricao_incidente);
                EditText editEndereco = findViewById(R.id.local_incidente);
                EditText editHora = findViewById(R.id.hora_incidente);
                EditText editDetalhes = findViewById(R.id.detalhes_incidente);

                Date currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String data = sdf.format(currentTime);

                titulo = editTitulo.getText().toString();
                descricao = editDescricao.getText().toString();
                endereco = editEndereco.getText().toString();
                dataHora = editHora.getText().toString();
                detalhes = editDetalhes.getText().toString();

                Incidente incidente = new Incidente(titulo, descricao, endereco, dataHora, "", detalhes);

                db.collection("Incidentes").add(incidente).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent intent = new Intent(NovoIncidente.this, activity_detalhe_incidente.class);
                        intent.putExtra("titulo", titulo);
                        intent.putExtra("descricao", descricao);
                        intent.putExtra("endereco", endereco);
                        intent.putExtra("dataHora", dataHora);
                        intent.putExtra("detalhes", detalhes);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });
    }

    private void SelectImage(){

        final CharSequence[] items={/*"Camera",*/"Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(NovoIncidente.this);
        builder.setTitle("Add Image");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);

                } else if (items[i].equals("Gallery")) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, SELECT_FILE);

                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();

    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);

        if(resultCode== Activity.RESULT_OK){

            if(requestCode==REQUEST_CAMERA){

                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                ivImage.setImageBitmap(bmp);

            }else if(requestCode==SELECT_FILE){

                Uri selectedImageUri = data.getData();
                ivImage.setImageURI(selectedImageUri);
            }

        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu_add_image, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
