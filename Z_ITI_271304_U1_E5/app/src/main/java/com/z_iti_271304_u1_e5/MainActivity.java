package com.z_iti_271304_u1_e5;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String[] valores = { "0", "1","X" };

    Spinner SP2_0, SP2_1, SP2_2, SP2_3;// spinner 2 var
    Button BT2;// boton 2 var


    Spinner SP3_0, SP3_1, SP3_2, SP3_3, SP3_4, SP3_5, SP3_6, SP3_7;// spinner 3 var
    Button BT3;// boton 3 var


    Spinner SP4_0, SP4_1, SP4_2, SP4_3, SP4_4, SP4_5, SP4_6, SP4_7, SP4_8, SP4_9, SP4_10, SP4_11, SP4_12, SP4_13, SP4_14, SP4_15;// spinner 4 var
    Button BT4;// boton 4 var


    Spinner SP5_0, SP5_1, SP5_2, SP5_3, SP5_4, SP5_5, SP5_6, SP5_7, SP5_8, SP5_9, SP5_10, SP5_11, SP5_12, SP5_13, SP5_14, SP5_15, SP5_16, SP5_17, SP5_18, SP5_19, SP5_20, SP5_21, SP5_22, SP5_23, SP5_24, SP5_25, SP5_26, SP5_27, SP5_28, SP5_29, SP5_30, SP5_31;// spinner 5 var
    Button BT5;// boton 5 var

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Importante: Esto va antes de instanciar controles dentro de cada pestaña

        // Agregar las pestañas---
        Resources res = getResources();
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("2 Variables");

        TabHost.TabSpec spec2 = tabHost.newTabSpec("");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("3 Variables");

        TabHost.TabSpec spec3 = tabHost.newTabSpec("");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("4 Variables");
        //spec3.setIndicator("",getResources().getDrawable(R.mipmap.ic_launcher));

        TabHost.TabSpec spec4 = tabHost.newTabSpec("");
        spec4.setContent(R.id.tab4);
        spec4.setIndicator("5 VAriables");

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
        tabHost.addTab(spec4);

        ////////////////////////////////////////////////////////////////////////////////////////////
        //valores tabla 2 variables
        String[] lValores2 = new String[4];
        SP2_0 = (Spinner) findViewById(R.id.spinner2_0);
            ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
            adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SP2_0.setAdapter(adapter0);
        SP2_1 = (Spinner) findViewById(R.id.spinner2_1);
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SP2_1.setAdapter(adapter1);
        SP2_2 = (Spinner) findViewById(R.id.spinner2_2);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SP2_2.setAdapter(adapter2);
        SP2_3 = (Spinner) findViewById(R.id.spinner2_3);
            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SP2_3.setAdapter(adapter3);
        BT2 = (Button) findViewById(R.id.btnCalcular2);
        BT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lValores2[0]=SP2_0.getSelectedItem().toString();
                lValores2[1]=SP2_1.getSelectedItem().toString();
                lValores2[2]=SP2_2.getSelectedItem().toString();
                lValores2[3]=SP2_3.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, "Valor seleccionado: " + Arrays.toString(lValores2), Toast.LENGTH_SHORT).show();
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////
        //valores tabla 3 variables
        String[] lValores3 = new String[8];
        SP3_0 = (Spinner) findViewById(R.id.spinner3_0);
            ArrayAdapter<String> adapter3_0 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, valores);
            adapter3_0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SP3_0.setAdapter(adapter3_0);
        SP3_1 = (Spinner) findViewById(R.id.spinner3_1);
            ArrayAdapter<String> adapter3_1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
            adapter3_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SP3_1.setAdapter(adapter3_1);
        SP3_2 = (Spinner) findViewById(R.id.spinner3_2);
            ArrayAdapter<String> adapter3_2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
            adapter3_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SP3_2.setAdapter(adapter3_2);
        SP3_3 = (Spinner) findViewById(R.id.spinner3_3);
            ArrayAdapter<String> adapter3_3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
            adapter3_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SP3_3.setAdapter(adapter3_3);
        SP3_4 = (Spinner) findViewById(R.id.spinner3_4);
            ArrayAdapter<String> adapter3_4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
            adapter3_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SP3_4.setAdapter(adapter3_4);
        SP3_5 = (Spinner) findViewById(R.id.spinner3_5);
            ArrayAdapter<String> adapter3_5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
            adapter3_5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SP3_5.setAdapter(adapter3_5);
        SP3_6 = (Spinner) findViewById(R.id.spinner3_6);
            ArrayAdapter<String> adapter3_6 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
            adapter3_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SP3_6.setAdapter(adapter3_6);
        SP3_7 = (Spinner) findViewById(R.id.spinner3_7);
            ArrayAdapter<String> adapter3_7 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
            adapter3_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SP3_7.setAdapter(adapter3_7);

        BT3 = (Button) findViewById(R.id.btnCalcular3);
        BT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lValores3[0]=SP3_0.getSelectedItem().toString();
                lValores3[1]=SP3_1.getSelectedItem().toString();
                lValores3[2]=SP3_2.getSelectedItem().toString();
                lValores3[3]=SP3_3.getSelectedItem().toString();
                lValores3[4]=SP3_4.getSelectedItem().toString();
                lValores3[5]=SP3_5.getSelectedItem().toString();
                lValores3[6]=SP3_6.getSelectedItem().toString();
                lValores3[7]=SP3_7.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, "Valor seleccionado: " + Arrays.toString(lValores3), Toast.LENGTH_SHORT).show();
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////
        //valores tabla 4 variables
        String[] lValores4 = new String[16];
        SP4_0 = (Spinner) findViewById(R.id.spinner4_0);
        ArrayAdapter<String> adapter4_0 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_0.setAdapter(adapter4_0);
        SP4_1 = (Spinner) findViewById(R.id.spinner4_1);
        ArrayAdapter<String> adapter4_1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_1.setAdapter(adapter4_1);
        SP4_2 = (Spinner) findViewById(R.id.spinner4_2);
        ArrayAdapter<String> adapter4_2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_2.setAdapter(adapter4_2);
        SP4_3 = (Spinner) findViewById(R.id.spinner4_3);
        ArrayAdapter<String> adapter4_3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_3.setAdapter(adapter4_3);
        SP4_4 = (Spinner) findViewById(R.id.spinner4_4);
        ArrayAdapter<String> adapter4_4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_4.setAdapter(adapter4_0);
        SP4_5 = (Spinner) findViewById(R.id.spinner4_5);
        ArrayAdapter<String> adapter4_5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_5.setAdapter(adapter4_1);
        SP4_6 = (Spinner) findViewById(R.id.spinner4_6);
        ArrayAdapter<String> adapter4_6 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_6.setAdapter(adapter4_2);
        SP4_7 = (Spinner) findViewById(R.id.spinner4_7);
        ArrayAdapter<String> adapter4_7 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_7.setAdapter(adapter4_3);
        SP4_8 = (Spinner) findViewById(R.id.spinner4_8);
        ArrayAdapter<String> adapter4_8 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_8.setAdapter(adapter4_8);
        SP4_9 = (Spinner) findViewById(R.id.spinner4_9);
        ArrayAdapter<String> adapter4_9 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_9.setAdapter(adapter4_9);
        SP4_10 = (Spinner) findViewById(R.id.spinner4_10);
        ArrayAdapter<String> adapter4_10 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_10.setAdapter(adapter4_10);
        SP4_11 = (Spinner) findViewById(R.id.spinner4_11);
        ArrayAdapter<String> adapter4_11 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_11.setAdapter(adapter4_11);
        SP4_12 = (Spinner) findViewById(R.id.spinner4_12);
        ArrayAdapter<String> adapter4_12 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_12.setAdapter(adapter4_12);
        SP4_13 = (Spinner) findViewById(R.id.spinner4_13);
        ArrayAdapter<String> adapter4_13 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_13.setAdapter(adapter4_13);
        SP4_14 = (Spinner) findViewById(R.id.spinner4_14);
        ArrayAdapter<String> adapter4_14 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_14.setAdapter(adapter4_14);
        SP4_15 = (Spinner) findViewById(R.id.spinner4_15);
        ArrayAdapter<String> adapter4_15 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter4_15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP4_15.setAdapter(adapter4_15);

        BT4 = (Button) findViewById(R.id.btnCalcular4);
        BT4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lValores4[0]=SP4_0.getSelectedItem().toString();
                lValores4[1]=SP4_1.getSelectedItem().toString();
                lValores4[2]=SP4_2.getSelectedItem().toString();
                lValores4[3]=SP4_3.getSelectedItem().toString();
                lValores4[4]=SP4_4.getSelectedItem().toString();
                lValores4[5]=SP4_5.getSelectedItem().toString();
                lValores4[6]=SP4_6.getSelectedItem().toString();
                lValores4[7]=SP4_7.getSelectedItem().toString();
                lValores4[8]=SP4_8.getSelectedItem().toString();
                lValores4[9]=SP4_9.getSelectedItem().toString();
                lValores4[10]=SP4_10.getSelectedItem().toString();
                lValores4[11]=SP4_11.getSelectedItem().toString();
                lValores4[12]=SP4_12.getSelectedItem().toString();
                lValores4[13]=SP4_13.getSelectedItem().toString();
                lValores4[14]=SP4_14.getSelectedItem().toString();
                lValores4[15]=SP4_15.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, "Valor seleccionado: " + Arrays.toString(lValores4), Toast.LENGTH_SHORT).show();
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////
        //valores tabla 4 variables
        String[] lValores5 = new String[32];
        SP5_0 = (Spinner) findViewById(R.id.spinner5_0);
        ArrayAdapter<String> adapter5_0 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_0.setAdapter(adapter5_0);
        SP5_1 = (Spinner) findViewById(R.id.spinner5_1);
        ArrayAdapter<String> adapter5_1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_1.setAdapter(adapter5_1);
        SP5_2 = (Spinner) findViewById(R.id.spinner5_2);
        ArrayAdapter<String> adapter5_2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_2.setAdapter(adapter5_2);
        SP5_3 = (Spinner) findViewById(R.id.spinner5_3);
        ArrayAdapter<String> adapter5_3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_3.setAdapter(adapter5_3);
        SP5_4 = (Spinner) findViewById(R.id.spinner5_4);
        ArrayAdapter<String> adapter5_4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_4.setAdapter(adapter5_4);
        SP5_5 = (Spinner) findViewById(R.id.spinner5_5);
        ArrayAdapter<String> adapter5_5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_5.setAdapter(adapter5_5);
        SP5_6 = (Spinner) findViewById(R.id.spinner5_6);
        ArrayAdapter<String> adapter5_6 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_6.setAdapter(adapter5_6);
        SP5_7 = (Spinner) findViewById(R.id.spinner5_7);
        ArrayAdapter<String> adapter5_7 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_7.setAdapter(adapter5_7);
        SP5_8 = (Spinner) findViewById(R.id.spinner5_8);
        ArrayAdapter<String> adapter5_8 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_8.setAdapter(adapter5_8);
        SP5_9 = (Spinner) findViewById(R.id.spinner5_9);
        ArrayAdapter<String> adapter5_9 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_9.setAdapter(adapter5_9);
        SP5_10 = (Spinner) findViewById(R.id.spinner5_10);
        ArrayAdapter<String> adapter5_10 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_10.setAdapter(adapter5_10);
        SP5_11 = (Spinner) findViewById(R.id.spinner5_11);
        ArrayAdapter<String> adapter5_11 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_11.setAdapter(adapter5_11);
        SP5_12 = (Spinner) findViewById(R.id.spinner5_12);
        ArrayAdapter<String> adapter5_12 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_12.setAdapter(adapter5_12);
        SP5_13 = (Spinner) findViewById(R.id.spinner5_13);
        ArrayAdapter<String> adapter5_13 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_13.setAdapter(adapter5_13);
        SP5_14 = (Spinner) findViewById(R.id.spinner5_14);
        ArrayAdapter<String> adapter5_14 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_14.setAdapter(adapter5_14);
        SP5_15 = (Spinner) findViewById(R.id.spinner5_15);
        ArrayAdapter<String> adapter5_15 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_15.setAdapter(adapter5_15);
        SP5_16 = (Spinner) findViewById(R.id.spinner5_16);
        ArrayAdapter<String> adapter5_16 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_16.setAdapter(adapter5_16);
        SP5_17 = (Spinner) findViewById(R.id.spinner5_17);
        ArrayAdapter<String> adapter5_17 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_17.setAdapter(adapter5_17);
        SP5_18 = (Spinner) findViewById(R.id.spinner5_18);
        ArrayAdapter<String> adapter5_18 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_18.setAdapter(adapter5_18);
        SP5_19 = (Spinner) findViewById(R.id.spinner5_19);
        ArrayAdapter<String> adapter5_19 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_19.setAdapter(adapter5_19);
        SP5_20 = (Spinner) findViewById(R.id.spinner5_20);
        ArrayAdapter<String> adapter5_20 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_20.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_20.setAdapter(adapter5_20);
        SP5_21 = (Spinner) findViewById(R.id.spinner5_21);
        ArrayAdapter<String> adapter5_21 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_21.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_21.setAdapter(adapter5_21);
        SP5_22 = (Spinner) findViewById(R.id.spinner5_22);
        ArrayAdapter<String> adapter5_22 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_22.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_22.setAdapter(adapter5_22);
        SP5_23 = (Spinner) findViewById(R.id.spinner5_23);
        ArrayAdapter<String> adapter5_23 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_23.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_23.setAdapter(adapter5_23);
        SP5_24 = (Spinner) findViewById(R.id.spinner5_24);
        ArrayAdapter<String> adapter5_24 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_24.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_24.setAdapter(adapter5_24);
        SP5_25 = (Spinner) findViewById(R.id.spinner5_25);
        ArrayAdapter<String> adapter5_25 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_25.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_25.setAdapter(adapter5_25);
        SP5_26 = (Spinner) findViewById(R.id.spinner5_26);
        ArrayAdapter<String> adapter5_26 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_26.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_26.setAdapter(adapter5_26);
        SP5_27 = (Spinner) findViewById(R.id.spinner5_27);
        ArrayAdapter<String> adapter5_27 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_27.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_27.setAdapter(adapter5_27);
        SP5_28 = (Spinner) findViewById(R.id.spinner5_28);
        ArrayAdapter<String> adapter5_28 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_28.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_28.setAdapter(adapter5_28);
        SP5_29 = (Spinner) findViewById(R.id.spinner5_29);
        ArrayAdapter<String> adapter5_29 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_29.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_29.setAdapter(adapter5_29);
        SP5_30 = (Spinner) findViewById(R.id.spinner5_30);
        ArrayAdapter<String> adapter5_30 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_30.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_30.setAdapter(adapter5_30);
        SP5_31 = (Spinner) findViewById(R.id.spinner5_31);
        ArrayAdapter<String> adapter5_31 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores);
        adapter5_31.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP5_31.setAdapter(adapter5_31);

        BT5 = (Button) findViewById(R.id.btnCalcular5);
        BT5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lValores5[0]=SP5_0.getSelectedItem().toString();
                lValores5[1]=SP5_1.getSelectedItem().toString();
                lValores5[2]=SP5_2.getSelectedItem().toString();
                lValores5[3]=SP5_3.getSelectedItem().toString();
                lValores5[4]=SP5_4.getSelectedItem().toString();
                lValores5[5]=SP5_5.getSelectedItem().toString();
                lValores5[6]=SP5_6.getSelectedItem().toString();
                lValores5[7]=SP5_7.getSelectedItem().toString();
                lValores5[8]=SP5_8.getSelectedItem().toString();
                lValores5[9]=SP5_9.getSelectedItem().toString();
                lValores5[10]=SP5_10.getSelectedItem().toString();
                lValores5[11]=SP5_11.getSelectedItem().toString();
                lValores5[12]=SP5_12.getSelectedItem().toString();
                lValores5[13]=SP5_13.getSelectedItem().toString();
                lValores5[14]=SP5_14.getSelectedItem().toString();
                lValores5[15]=SP5_15.getSelectedItem().toString();
                lValores5[16]=SP5_16.getSelectedItem().toString();
                lValores5[17]=SP5_17.getSelectedItem().toString();
                lValores5[18]=SP5_18.getSelectedItem().toString();
                lValores5[19]=SP5_19.getSelectedItem().toString();
                lValores5[20]=SP5_20.getSelectedItem().toString();
                lValores5[21]=SP5_21.getSelectedItem().toString();
                lValores5[22]=SP5_22.getSelectedItem().toString();
                lValores5[23]=SP5_23.getSelectedItem().toString();
                lValores5[24]=SP5_24.getSelectedItem().toString();
                lValores5[25]=SP5_25.getSelectedItem().toString();
                lValores5[26]=SP5_26.getSelectedItem().toString();
                lValores5[27]=SP5_27.getSelectedItem().toString();
                lValores5[28]=SP5_28.getSelectedItem().toString();
                lValores5[29]=SP5_29.getSelectedItem().toString();
                lValores5[30]=SP5_30.getSelectedItem().toString();
                lValores5[31]=SP5_31.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, "Valor seleccionado: " + Arrays.toString(lValores5), Toast.LENGTH_SHORT).show();
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////
    }

}
