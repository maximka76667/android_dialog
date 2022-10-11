package com.example.android_event_listeners;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int currentAnho;
    boolean[] checkedLineas = {false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvLineas = findViewById(R.id.tv_lineas);
        TextView tvMes = findViewById(R.id.tv_mes);
        TextView tvAnho = findViewById(R.id.tv_anho);

        Button btnBorrar = findViewById(R.id.btn_Borrar);
        Button btnBuscar = findViewById(R.id.btn_Buscar);

        currentAnho = -1;

        // TextView Lines
        tvLineas.setOnClickListener(new View.OnClickListener() {
            final String[] lineas = { "666345123", "123123123"};

            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setTitle("Seleccione lineas");

                dialog.setMultiChoiceItems(lineas, checkedLineas, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        checkedLineas[i] = true;
                    }
                });

                dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String texto = "";
                        for(int j = 0; j < checkedLineas.length; j++) {
                            if(!checkedLineas[j]) {
                                continue;
                            }

                            if(!texto.equals("")) texto += ", ";
                            texto += lineas[j];
                        }
                        tvLineas.setText(texto);
                    }
                });

                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                dialog.create().show();
            }
        });
        // TextView Lines /

        // TextView Mes
        tvMes.setOnClickListener(new View.OnClickListener() {
            String[] meses = {" ", "Enero", "Febrero"};

            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setItems(meses, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tvMes.setText(meses[i]);
                    }
                });

                dialog.create().show();
            }
        });
        // TextView Mes /

        // TextView Anho
        tvAnho.setOnClickListener(new View.OnClickListener() {
            String[] anhos = {"2011", "2012"};

            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setSingleChoiceItems(anhos, currentAnho, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        currentAnho = i;
                    }
                });

                dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(currentAnho == -1) return;
                        tvAnho.setText(anhos[currentAnho]);
                    }
                });

                dialog.create().show();
            }
        });
        // TextView Anho /

        // Boton Borrar
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setMessage("Estas seguro?");

                dialog.setPositiveButton("Seguro", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tvLineas.setText("");
                        tvMes.setText("");
                        tvAnho.setText("");
                        currentAnho = -1;
                        checkedLineas = new boolean[]{false, false};
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                dialog.setCancelable(false);

                dialog.create().show();
            }
        });
        // Boton Borrar /

        // Boton Buscar
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvAnho.getText().toString().equals("") || tvLineas.getText().toString().equals("")) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                    dialog.setTitle("Atencion");
                    dialog.setMessage("Debe indicar al menos una linea y un anho");

                    dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                            dialog.setMessage("Indique al menos una línea telefónica y año de facturación");

                            dialog.create().show();

                        }
                    });

                    dialog.create().show();
                    return;
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage("Busqueda successiva");
                dialog.create().show();
            }
        });
        // Boton Buscar /
    }
}