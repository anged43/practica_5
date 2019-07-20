package com.angel.practica5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView cajaCedula;
    TextView cajaNombres, cajaApellidos,cajaDatos;
    Button botonLeer, botonEscribir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cajaCedula = (TextView) findViewById(R.id.txtCedulaMI);
        cajaApellidos = (TextView) findViewById(R.id.txtApellidosMI);
        cajaNombres = (TextView) findViewById(R.id.txtNombresMI);
        cajaDatos = (TextView) findViewById(R.id.txtDatosMI);

        botonEscribir = (Button) findViewById(R.id.btnEscribirMI);
        botonLeer = (Button) findViewById(R.id.btnLeerMI);


    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.btnEscribirMI:
                try{
                    OutputStreamWriter escritor = new OutputStreamWriter(openFileOutput("archivo.txt", Context.MODE_APPEND));
                    escritor.write(cajaCedula.getText().toString()+"," +cajaApellidos.getText().toString()+","+cajaNombres.getText().toString());
                    escritor.close();
                }catch (Exception ex){
                    Log.e("Archivo MI","Error en el archivo de escritura");
                }
                break;
            case R.id.btnLeerMI:
                try{
                    BufferedReader lector = new BufferedReader(new InputStreamReader(openFileInput("archivo.txt")));
                    String datos = lector.readLine();
                    String [] listaPersonas = datos.split(";");
                    for (int i = 0; i < listaPersonas.length; i++){
                        cajaDatos.append(listaPersonas[i].split(",")[0]+""+listaPersonas[i].split(",")[1]);
                    }
                    cajaDatos.setText("");

                    lector.close();

                }catch (Exception ex){
                    Log.e("Archivo MI","Error en el archivo de escritura"+ex.getMessage());
                }
                break;
        }

    }

}
