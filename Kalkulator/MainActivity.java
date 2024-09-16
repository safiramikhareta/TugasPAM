package com.example.kalkulator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void prosesHitung(View view){
        EditText angka1 = findViewById(R.id.angka1);
        EditText angka2 = findViewById(R.id.angka2);

        RadioGroup operatorGroup = findViewById(R.id.radioGroupOperator);
        int selectedOperatorId = operatorGroup.getCheckedRadioButtonId();
        RadioButton selectedOperator = findViewById(selectedOperatorId);
        String tipeOperator = selectedOperator.getText().toString();

        Intent intent = new Intent(this, HasilPerhitungan.class);

        double nilai1 = Double.parseDouble(angka1.getText().toString());
        double nilai2 = Double.parseDouble(angka2.getText().toString());
        double hasil = 0;

        if(tipeOperator.equals("Tambah")){
            hasil = nilai1 + nilai2;
        }else if(tipeOperator.equals("Kurang")){
            hasil = nilai1 - nilai2;
        }else if(tipeOperator.equals("Kali")){
            hasil = nilai1 * nilai2;
        }else if(tipeOperator.equals("Bagi")){
            if(nilai2 != 0) {
                hasil = nilai1 / nilai2;
            }else{
                String pesanErrorNol = getString(R.string.psnErrNol);
                intent.putExtra("pesanError", pesanErrorNol);
            }
        }

        intent.putExtra("hasil", hasil);
        startActivity(intent);
    }
}