package com.example.forcavendasapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.model.Endereco;

public class CadastroEndereco extends AppCompatActivity {

    private Button btCancelarEnd;
    private Button btSalvarEnd;
    private Button btBusca;
    private EditText editTextCodigo;
    private EditText editTextLogradouro;
    private EditText editTextNumero;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextUF;
    private EnderecoController ec = new EnderecoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        btCancelarEnd = findViewById(R.id.btnCancelarEndereco);
        btSalvarEnd = findViewById(R.id.btnSalvar);
        btBusca = findViewById(R.id.btnBuscar);
        editTextCodigo = findViewById(R.id.editTextCodigo);
        editTextLogradouro = findViewById(R.id.editTextLogradouro);
        editTextNumero = findViewById(R.id.editTextNumero);
        editTextBairro = findViewById(R.id.editTextBairro);
        editTextCidade = findViewById(R.id.editTextCidade);
        editTextUF = findViewById(R.id.editTextUF);

        btBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarEnderecoPorCodigo();
            }
        });

        btCancelarEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroEndereco.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btSalvarEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextCodigo == null || editTextCodigo.getText().toString() == "") {
                    editTextCodigo.setText("0");
                }
                if (editTextCodigo != null && !editTextCodigo.getText().toString().isEmpty() && !editTextCodigo.getText().toString().equals("0")) {
                    if (validarCampos()) {
                        Endereco end = new Endereco();
                        end.setCodigo(Integer.parseInt(editTextCodigo.getText().toString()));
                        end.setBairro(editTextBairro.getText().toString());
                        end.setCidade(editTextCidade.getText().toString());
                        end.setLogradouro(editTextLogradouro.getText().toString());
                        end.setNumero(Integer.parseInt(editTextNumero.getText().toString()));
                        end.setUf(editTextUF.getText().toString());
                        ec.atualizarEndereco(end);
                        limpaCampos();
                        Toast.makeText(CadastroEndereco.this, "Endereço atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Pelo menos um campo está vazio, exiba uma mensagem de erro ao usuário
                        Toast.makeText(CadastroEndereco.this, "Por favor, preencha todos os campos para atualizar um endereço", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (validarCampos()) {
                        Endereco end = new Endereco();
                        end.setBairro(editTextBairro.getText().toString());
                        end.setCidade(editTextCidade.getText().toString());
                        end.setLogradouro(editTextLogradouro.getText().toString());
                        end.setNumero(Integer.parseInt(editTextNumero.getText().toString()));
                        end.setUf(editTextUF.getText().toString());
                        ec.salvarEndereco(end);
                        limpaCampos();
                        Toast.makeText(CadastroEndereco.this, "Endereço salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Pelo menos um campo está vazio, exiba uma mensagem de erro ao usuário
                        Toast.makeText(CadastroEndereco.this, "Por favor, preencha todos os campos para criar um novo endereço", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validarCampos() {

        String logradouro = editTextLogradouro.getText().toString().trim();
        String numero = editTextNumero.getText().toString().trim();
        String bairro = editTextBairro.getText().toString().trim();
        String cidade = editTextCidade.getText().toString().trim();
        String uf = editTextUF.getText().toString().trim();

        if (logradouro.isEmpty() || numero.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || uf.isEmpty()) {
            return false;
        }

        return true;
    }

    private void findId(int id) {
        Endereco ed = ec.findByIdEndereco(id);
        editTextBairro.setText(ed.getBairro());
        editTextLogradouro.setText(ed.getLogradouro());
        editTextNumero.setText(ed.getNumero());
        editTextCidade.setText(ed.getCidade());
        editTextUF.setText(ed.getUf());
    }

    private void limpaCampos() {
        editTextCodigo.setText("0");
        editTextBairro.setText("");
        editTextLogradouro.setText("");
        editTextNumero.setText("");
        editTextCidade.setText("");
        editTextUF.setText("");
    }

    private void buscarEnderecoPorCodigo() {
        String codigoStr = editTextCodigo.getText().toString();

        if (!codigoStr.isEmpty()) {
            int codigo = Integer.parseInt(codigoStr);
            Endereco endereco = ec.findByIdEndereco(codigo);

            if (endereco != null) {
                editTextBairro.setText(endereco.getBairro());
                editTextLogradouro.setText(endereco.getLogradouro());
                editTextNumero.setText(String.valueOf(endereco.getNumero()));
                editTextCidade.setText(endereco.getCidade());
                editTextUF.setText(endereco.getUf());
            } else {
                limpaCampos();
                Toast.makeText(CadastroEndereco.this, "Endereço não encontrado.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(CadastroEndereco.this, "Por favor, insira um código para buscar.", Toast.LENGTH_SHORT).show();
        }
    }

}
