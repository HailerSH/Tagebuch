package com.dcps.tagebuch.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dcps.tagebuch.R;
import com.dcps.tagebuch.controller.InitialInformationDBController;
import com.dcps.tagebuch.controller.MainActivityController;
import com.dcps.tagebuch.model.pojo.Categoria;
import com.dcps.tagebuch.model.pojo.Pensamiento;

import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton botonDeshacer, botonRehacer, botonReportarPensamiento;
    Dialog dialogReportarPensamientoCategorias, dialogReportarPensamiento;

    MainActivityController mainActivityController;
    InitialInformationDBController initialInformationDBController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonDeshacer = findViewById(R.id.button_undo);
        botonRehacer = findViewById(R.id.button_redo);
        botonReportarPensamiento = findViewById(R.id.button_add_thought);

        botonDeshacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deshacer();
            }
        });

        botonRehacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rehacer();
            }
        });

        botonReportarPensamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportarPensamiento();
            }
        });

        dialogReportarPensamientoCategorias = new Dialog(this);
        dialogReportarPensamiento = new Dialog(this);

        mainActivityController = new MainActivityController(this);
        initialInformationDBController = new InitialInformationDBController(this);

        try {
            initialInformationDBController.addInformation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mostrarPensamientos();
    }

    private void deshacer() {
        Toast.makeText(MainActivity.this, "No nos alcanzó el tiempo para implementar el deshacer ๐·°(৹˃̵﹏˂̵৹)°·๐", Toast.LENGTH_SHORT).show();
    }

    private void rehacer() {
        Toast.makeText(MainActivity.this, "No nos alcanzó el tiempo para implementar el rehacer ๐·°(৹˃̵﹏˂̵৹)°·๐", Toast.LENGTH_SHORT).show();
    }

    private void mostrarFormularioReportarPensamiento(String nombreCategoria) {
        dialogReportarPensamiento.setContentView(R.layout.dialog_reportar_pensamiento);
        dialogReportarPensamiento.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button buttonCancelarReportarPensamiento = dialogReportarPensamiento.findViewById(R.id.button_cancelar);
        Button buttonReportarPensamiento = dialogReportarPensamiento.findViewById(R.id.button_reportar);

        EditText inputTitulo = dialogReportarPensamiento.findViewById(R.id.input_titulo);
        EditText inputDescripcion = dialogReportarPensamiento.findViewById(R.id.input_descripcion);

        dialogReportarPensamiento.show();

        buttonCancelarReportarPensamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogReportarPensamiento.dismiss();
            }
        });

        buttonReportarPensamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputTitulo.getText().toString().compareTo("") != 0 && inputDescripcion.getText().toString().compareTo("") != 0 ) {
                    Pensamiento pensamiento = mainActivityController.reportarPensamiento(inputTitulo.getText().toString(),
                                                                                         inputDescripcion.getText().toString(),
                                                                                         nombreCategoria);
                    dialogReportarPensamiento.dismiss();
                    mostrarPensamiento(pensamiento);
                }
            }
        });
    }

    private void mostrarPensamiento(Pensamiento pensamiento) {
        LinearLayout listOfThoughts = findViewById(R.id.list_of_thoughts);

        @SuppressLint("InflateParams") ConstraintLayout itemPensamiento = (ConstraintLayout) this.getLayoutInflater().inflate(R.layout.item_pensamiento, null);

        TextView labelTitulo = itemPensamiento.findViewById(R.id.label_titulo);
        labelTitulo.setText(pensamiento.getTitulo());

        TextView labelDescripcion = itemPensamiento.findViewById(R.id.label_descripcion);
        labelDescripcion.setText(pensamiento.getDescripcion());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        TextView labelFecha = itemPensamiento.findViewById(R.id.label_fecha);
        labelFecha.setText(formatter.format(pensamiento.getFecha()));

        TextView labelCategoria = itemPensamiento.findViewById(R.id.label_categoria);
        labelCategoria.setText(pensamiento.getCategoriaNombre());

        Categoria categoria = mainActivityController.getCategoria(pensamiento.getCategoriaNombre());
        itemPensamiento.setBackgroundColor(Color.parseColor(categoria.getColor()));

        listOfThoughts.addView(itemPensamiento);
    }

    private void mostrarPensamientos() {
        List<Pensamiento> pensamientos = mainActivityController.getPensamientos();

        if (pensamientos.size() > 0) {
            for (Pensamiento pensamiento : pensamientos) {
                mostrarPensamiento(pensamiento);
            }
        } else {
            showMessage("Usted aún no ha registrado pensamientos. Para reportar un pensamiento presione el icono + ubicado en la barra superior.");
        }
    }

    private void mostrarCategorias() {
        List<Categoria> categorias = mainActivityController.getCategorias();

        if (categorias.size() > 0) {

             LinearLayout listOfCategories = dialogReportarPensamientoCategorias.findViewById(R.id.list_of_categories);

            for (Categoria categoria : categorias) {

                @SuppressLint("InflateParams") ConstraintLayout itemCategoria = (ConstraintLayout) this.getLayoutInflater().inflate(R.layout.item_categoria, null);

                TextView labelNombre = itemCategoria.findViewById(R.id.label_nombre);
                labelNombre.setText(categoria.getNombre());

                TextView labelDescripcion = itemCategoria.findViewById(R.id.label_descripcion);
                labelDescripcion.setText(categoria.getDescripcion());

                itemCategoria.setBackgroundColor(Color.parseColor(categoria.getColor()));

                itemCategoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mostrarFormularioReportarPensamiento(categoria.getNombre());
                        dialogReportarPensamientoCategorias.dismiss();
                    }
                });
                listOfCategories.addView(itemCategoria);
            }
        } else {
            showMessage("Usted aún no ha registrado pensamientos. Para reportar un pensamiento presione el icono + ubicado en la barra superior.");
        }
    }

    private void reportarPensamiento() {

        dialogReportarPensamientoCategorias.setContentView(R.layout.dialog_reportar_pensamiento_categorias);
        dialogReportarPensamientoCategorias.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mostrarCategorias();

        Button buttonCancelarReportarPensamiento = dialogReportarPensamientoCategorias.findViewById(R.id.button_cancelar);

        dialogReportarPensamientoCategorias.show();

        buttonCancelarReportarPensamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogReportarPensamientoCategorias.dismiss();
            }
        });
    }

    private void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message)
                .setPositiveButton("Vale", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}