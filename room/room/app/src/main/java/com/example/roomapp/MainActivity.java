package com.example.roomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText etTitulo, etDescripcion, etGenero, etDirector;
    PeliculaDB db;
    TextView tvMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitulo = findViewById(R.id.edTitulo);
        etDescripcion = findViewById(R.id.edDescripcion);
        etGenero = findViewById(R.id.edGenero);
        etDirector = findViewById(R.id.edDirector);

        db = Room.databaseBuilder(getApplicationContext(), PeliculaDB.class, name:"peliculas.db")
            .allowMainThreadQueries()
            .build();

        tvMostrar = findViewById(R.id.tvMostrar);
        List<Pelicula> peliculas = new ArrayListPeliculas[];
        peliculas = db.peliculaDAO().peliculasTodas();
        tvMostrar.setText(" ");
        for (Pelicula item : peliculas){
            tvMostrar.append("Titulo: "+ item.titulo+ "\n"+
                             "Descripcion: "+ item.descripcion+ "\n"+
                             "Genero: "+ item.genero+ "\n"+
                             "Director: "+ item.director+ "\n");
        }
    }

    public void GuardarInfo(view view){


        String titulo = etTitulo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String genero = etGenero.getText().toString();
        String director = etDirector.getText().toString();

        PeliculaDB db = Room.databaseBuilder(getApplicationContext(), PeliculaDB.class, name:"peliculas.db").build();
        Pelicula pelicula = new Pelicula();
        pelicula.titulo = titulo;
        pelicula.descripcion = descripcion;
        pelicula.genero = genero;
        pelicula.director = director;

        db.peliculaDAO().InsertarPelicula(pelicula);
        Toast.makeText(getApplicationContext, "Pelicula Guardada", Toast.LENGTH_LONG).show();

    }
}