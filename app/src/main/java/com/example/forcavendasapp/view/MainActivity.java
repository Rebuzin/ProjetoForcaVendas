package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.forcavendasapp.R;

public class MainActivity extends AppCompatActivity {

    private Button btEndereco;
    private Button btCliente;
    private Button btItem;
    private Button btPedido;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btEndereco = findViewById(R.id.btEd);
        btCliente = findViewById(R.id.btCli);
        btPedido = findViewById(R.id.btPed);
        btItem = findViewById(R.id.btItem);

        btEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CadastroEndereco.class);
                startActivity(intent);
            }
        });

        btCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroCliente.class);
                startActivity(intent);
            }
        });

        btItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroItem.class);
                startActivity(intent);
            }
        });

        btPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroPedido.class);
                startActivity(intent);
            }
        });
    }
}