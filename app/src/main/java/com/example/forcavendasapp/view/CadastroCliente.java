package com.example.forcavendasapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.adapter.EnderecoAdapter;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.model.Cliente;
import com.example.forcavendasapp.model.Endereco;

import java.util.ArrayList;

public class CadastroCliente extends AppCompatActivity {

    private Spinner spinnerLogradouros;
    private Button btCancelar;
    private Button btSalvarCliente;
    private EditText editTextCodigo;
    private EditText editTextNome;
    private EditText editTextCpf;
    private EditText editTextDtNasc;

    private ArrayList<Endereco> listaEnderecos;

    private ClienteController cc = new ClienteController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        spinnerLogradouros = findViewById(R.id.spinnerEndereco);
        btCancelar = findViewById(R.id.btnCancelarCliente);
        btSalvarCliente = findViewById(R.id.btnSalvarCliente);
        editTextCodigo = findViewById(R.id.editTextCodigoCliente);
        editTextNome = findViewById(R.id.editTextNome);
        editTextCpf = findViewById(R.id.editTextCPF);
        editTextDtNasc = findViewById(R.id.editTextDataNasc);

        EnderecoController ec = new EnderecoController(this);

        listaEnderecos = ec.findAllEndereco();
        EnderecoAdapter adapter = new EnderecoAdapter(this, listaEnderecos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLogradouros.setAdapter(adapter);

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroCliente.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btSalvarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    Cliente cliente = new Cliente();
                    Endereco end = (Endereco) spinnerLogradouros.getSelectedItem();
                    cliente.setNome(editTextNome.getText().toString());
                    cliente.setCPF(editTextCpf.getText().toString());
                    cliente.setDtNasc(editTextDtNasc.getText().toString());
                    if (end != null) {
                        cliente.setCodEndereco(end.getCodigo());
                        cc.salvarCliente(cliente);
                        limpaCampos();
                        Toast.makeText(CadastroCliente.this, "Cliente salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CadastroCliente.this, "Selecione um endereço válido.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroCliente.this, "Por favor, preencha todos os campos para criar um novo cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validarCampos() {
        String nome = editTextNome.getText().toString().trim();
        String cpf = editTextCpf.getText().toString().trim();
        String dtNasc = editTextDtNasc.getText().toString().trim();

        if (nome.isEmpty() || cpf.isEmpty() || dtNasc.isEmpty()) {
            return false;
        }

        return true;
    }

    private void limpaCampos() {
        editTextCodigo.setText("0");
        editTextNome.setText("");
        editTextCpf.setText("");
        editTextDtNasc.setText("");
    }
}

