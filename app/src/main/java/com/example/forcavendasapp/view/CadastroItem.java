package com.example.forcavendasapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.ItemController;
import com.example.forcavendasapp.model.Item;

public class CadastroItem extends AppCompatActivity {

    private Button btCancelarItem;
    private Button btSalvarItem;
    private EditText editTextCodigo;
    private EditText editTextDescricao;
    private EditText editTextVlrUnit;
    private EditText editTextUnMedida;
    private ItemController ic = new ItemController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);

        btCancelarItem = findViewById(R.id.btnCancelarItem);
        btSalvarItem = findViewById(R.id.btnSalvarItem);
        editTextCodigo = findViewById(R.id.editTextCodigoItem);
        editTextDescricao = findViewById(R.id.editTextDescricaoItem);
        editTextVlrUnit = findViewById(R.id.editTextValorUnitario);
        editTextUnMedida = findViewById(R.id.editTextUnidadeDeMedida);
        btCancelarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroItem.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btSalvarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    Item item = new Item();
                    item.setDescricao(editTextDescricao.getText().toString());
                    item.setVlrUnit(Integer.parseInt(editTextVlrUnit.getText().toString()));
                    item.setUnMedida(editTextUnMedida.getText().toString());

                    ic.salvarItem(item);
                    limpaCampos();
                    Toast.makeText(CadastroItem.this, "Item salvo com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CadastroItem.this, "Por favor, preencha todos os campos para criar um novo item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validarCampos() {
        String descricao = editTextDescricao.getText().toString().trim();
        String vlrUnit = editTextVlrUnit.getText().toString().trim();
        String unMedida = editTextUnMedida.getText().toString().trim();

        return !descricao.isEmpty() && !vlrUnit.isEmpty() && !unMedida.isEmpty();
    }

    private void limpaCampos() {
        editTextCodigo.setText("0");
        editTextDescricao.setText("");
        editTextVlrUnit.setText("");
        editTextUnMedida.setText("");
    }
}
