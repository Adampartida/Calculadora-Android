package com.example.calculadora;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


        // Muestra los número que se van pulsando
        private TextView txtMissatges;

        // Panatlla secundaria, muestra la operación
        private TextView txtMarca;

        // Primer num marcado
        private double num1 = 0;

        // Segundo num marcado
        private double num2 = 0;

        // Para los botones de Memoria
        private double memoria = 0;

        // Variable mostrar video
         private VideoView videoView;


        // Guarda la operación indicada +,-,*,/
        private String operacio = "";




    // Función para quitar el .0 si el número es entero
    private String formatearNumero(double numero) {
        if (numero % 1 == 0) {
            return String.valueOf((long) numero); // Si es entero, quita los decimales
        } else {
            return String.valueOf(numero); // Si tiene decimales reales (ej: 4.5), los deja
        }
    }




    //Primer metodo que se ejecuta al abrir la app

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);

            setContentView(R.layout.activity_main);

            videoView = findViewById(R.id.video);

            txtMarca = findViewById(R.id.txtMarca);


            txtMissatges = findViewById(R.id.txtMissatges);

            //Conectar botones numericos, (busca el id button0 y guarda en la variable btn0)

            Button btn0 = findViewById(R.id.bttnCero);
            Button btn1 = findViewById(R.id.bttn1);
            Button btn2 = findViewById(R.id.bttn2);
            Button btn3 = findViewById(R.id.bttn3);
            Button btn4 = findViewById(R.id.bttn4);
            Button btn5 = findViewById(R.id.bttn5);
            Button btn6 = findViewById(R.id.bttn6);
            Button btn7 = findViewById(R.id.button7);
            Button btn8 = findViewById(R.id.button8);
            Button btn9 = findViewById(R.id.button9);


        //-------------------------------//
        //         Botones num           //
        //-------------------------------//



        View.OnClickListener numerosListener = v -> {

            Button btnPulsado = (Button) v;

            String numero = btnPulsado.getText().toString();

            if (txtMissatges.getText().toString().equals("0")) {

                txtMissatges.setText(numero);

            } else {

                txtMissatges.append(numero);

            }
        };



        //-------------------------------//
        //         Botones num           //
        //-------------------------------/


        btn0.setOnClickListener(numerosListener);
        btn1.setOnClickListener(numerosListener);
        btn2.setOnClickListener(numerosListener);
        btn3.setOnClickListener(numerosListener);
        btn4.setOnClickListener(numerosListener);
        btn5.setOnClickListener(numerosListener);
        btn6.setOnClickListener(numerosListener);
        btn7.setOnClickListener(numerosListener);
        btn8.setOnClickListener(numerosListener);
        btn9.setOnClickListener(numerosListener);





        //-------------------------------//
        //      Botones especiales       //
        //-------------------------------//

        Button btnPunto = findViewById(R.id.bttnPunto);
        Button btnAC = findViewById(R.id.buttonAC);
        Button btnIgual = findViewById(R.id.bttnIgual);
        Button btnFletxa = findViewById(R.id.bttnFletxa);
        Button btnPercent = findViewById(R.id.buttonPerCent);
        Button btnMasMenos = findViewById(R.id.bttnMasMenos);


        //-------------------------------//
        //   Botones funciones Memoria   //
        //-------------------------------//

        Button btnMMin = findViewById(R.id.bttnM1);
        Button btnMSum = findViewById(R.id.bttnM2);
        Button btnMC = findViewById(R.id.bttnM3);
        Button btnMR = findViewById(R.id.bttnM4);



        View.OnClickListener memoriaListener = v -> {

            Button btn = (Button) v;

            String accio = btn.getText().toString();

            double numeroPantalla;

            try {
                numeroPantalla = Double.parseDouble(
                        txtMissatges.getText().toString()
                );
            } catch (Exception e) {
                numeroPantalla = 0;
            }

            switch (accio) {

                case "M+":
                    memoria += numeroPantalla;
                    txtMissatges.setText("0");
                    break;

                case "M-":
                    memoria -= numeroPantalla;
                    txtMissatges.setText("0");
                    break;

                case "Mc":
                    memoria = 0;
                    break;

                case "Mr":
                    txtMissatges.setText(
                            formatearNumero(memoria)
                    );
                    break;
            }

        };



        //-------------------------------//
        //      Botones operaciones      //
        //-------------------------------//

        Button btnSuma = findViewById(R.id.bttnSuma);
        Button btnResta = findViewById(R.id.bttnResta);
        Button btnMulti = findViewById(R.id.buttonMulti);
        Button btnDiv = findViewById(R.id.buttonDividir);




        android.view.View.OnClickListener operacionsListener = v -> {

                Button btnPulsado = (Button) v;

                String operacioSeleccionada = btnPulsado.getText().toString();

                txtMarca.setText(operacioSeleccionada);



                switch (operacioSeleccionada) {

                    case "+":
                    case "-":
                    case "X":
                    case "/":



                        num1 = Double.parseDouble(txtMissatges.getText().toString());

                        operacio = operacioSeleccionada;

                        txtMarca.setText(formatearNumero(num1) + " " + operacio);

                        txtMissatges.setText("0");

                        break;



                        //Solo aparece este botón cuando se pone lateral la calculadora

                    case "±":

                        double numeroActual = Double.parseDouble(txtMissatges.getText().toString());

                        numeroActual = numeroActual * -1;

                        txtMissatges.setText(formatearNumero(numeroActual));

                        break;


                        // Numero en porcentaje


                    case "%":

                        double numeroPercent = Double.parseDouble(txtMissatges.getText().toString());

                        numeroPercent = numeroPercent / 100;

                        txtMissatges.setText(formatearNumero(numeroPercent));
                        break;


                    // Flecha para borrar numeros

                    case "←":

                        String textoActual = txtMissatges.getText().toString();

                        if (textoActual.length() > 1) {
                            txtMissatges.setText(textoActual.substring(0,textoActual.length() - 1));

                        } else {
                            txtMissatges.setText("0");
                        }
                        break;


                    // Para poner decimales

                    case ".":

                        String texto = txtMissatges.getText().toString();

                        if (!texto.contains(".")) {txtMissatges.append(".");}

                        break;



                    // Resetear calculadora


                    case "AC":

                        txtMarca.setText("0");
                        txtMissatges.setText("0");

                        num1 = 0;
                        num2 = 0;
                        operacio = "";

                        break;

                    // Ejecución del cálculo


                    case "=":

                        num2 = Double.parseDouble(txtMissatges.getText().toString());

                        txtMarca.setText(formatearNumero(num1) + " " + operacio + " " + formatearNumero(num2));

                        double resultat = 0;





                        //-------------------------------//
                        //      Operacions +,-,*,/       //
                        //-------------------------------//


                        switch (operacio) {

                            case "+":
                                resultat = num1 + num2;
                                break;

                            case "-":
                                resultat = num1 - num2;
                                break;

                            case "X":
                                resultat = num1 * num2;
                                break;

                            case "/":
                                if (num2 == 0) {
                                    txtMissatges.setText("Error");
                                    return;
                                }
                                resultat = num1 / num2;
                                break;
                        }





                        //Mostrar operació a través --> =

                        txtMissatges.setText(String.valueOf(formatearNumero(resultat)));
                        num1 = resultat;



                        // Reproducir vídeo al executar --> =

                        Uri videoUri = Uri.parse(
                                "android.resource://" + getPackageName() +
                                        "/" + R.raw.videoplayback
                        );


                        videoView.setVideoURI(videoUri);
                        videoView.start();

                        break;


                }
            };



            //-------------------------------//
            //       Clicks operacions      //
            //-------------------------------//

            btnSuma.setOnClickListener(operacionsListener);
            btnResta.setOnClickListener(operacionsListener);
            btnMulti.setOnClickListener(operacionsListener);
            btnDiv.setOnClickListener(operacionsListener);


            //-------------------------------//
            //  Clicks operaciones especials //
            //-------------------------------//

            btnAC.setOnClickListener(operacionsListener);
            btnIgual.setOnClickListener(operacionsListener);
            btnPunto.setOnClickListener(operacionsListener);
            btnFletxa.setOnClickListener(operacionsListener);
            btnPercent.setOnClickListener(operacionsListener);


            // Para que se ejecute la calculadora con el boton ±
            if (btnMasMenos != null) {
                btnMasMenos.setOnClickListener(operacionsListener);
            }


            //-------------------------------//
            //         Clicks Memoria        //
            //-------------------------------//

        btnMMin.setOnClickListener(memoriaListener);
        btnMSum.setOnClickListener(memoriaListener);
        btnMR.setOnClickListener(memoriaListener);
        btnMC.setOnClickListener(memoriaListener);



        }
    }








