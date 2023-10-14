package com.example.forcavendasapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.adapter.ClienteAdapter;
import com.example.forcavendasapp.adapter.EnderecoAdapter;
import com.example.forcavendasapp.adapter.ItemAdapter;
import com.example.forcavendasapp.adapter.ItemPedidoAdapter;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.controller.ItemController;
import com.example.forcavendasapp.controller.ItemPedidoController;
import com.example.forcavendasapp.controller.PedidoController;
import com.example.forcavendasapp.model.Cliente;
import com.example.forcavendasapp.model.Endereco;
import com.example.forcavendasapp.model.Item;
import com.example.forcavendasapp.model.ItemPedido;
import com.example.forcavendasapp.model.Pedido;

import java.util.ArrayList;

public class CadastroPedido extends AppCompatActivity {

    private Button btCancelarPedido;
    private Spinner spinnerCliente;
    private Spinner spinnerItemVenda;
    private EditText editTextQuantidade;
    private Button btnAdicionarItem;
    private Button btnRemoverItem;
    private ListView listViewItensPedido;
    private RadioGroup radioGroupCondicaoPagamento;
    private RadioButton radioButtonAVista;
    private RadioButton radioButtonAPrazo;
    private TextView tvQtnParcelas;
    private Spinner spinnerEnderecoEntrega;
    private Button btnConcluirPedido;
    private ArrayList<Endereco> listaEnderecos;
    private ArrayList<Item> listaItem;
    private ArrayList<Cliente> listaCliente;
    private ArrayList<ItemPedido> listItemPedido = new ArrayList<>();
    private int itemSelecionado = -1;
    private double vlrTotal = 0.0;
    private double vlrTotalVista = 0.0;
    private int qtdTotal = 0;
    private Button btGerarParcelas;
    private EditText edQntParcelas;
    private TextView tvInfoParcelas;
    private TextView tvParcelas;
    private TextView tvTotal;
    private TextView tvQtd;
    private EnderecoController ec = new EnderecoController(this);
    private ItemController ic = new ItemController(this);
    private ClienteController cc = new ClienteController(this);
    private PedidoController pc = new PedidoController(this);
    private ItemPedidoController pic = new ItemPedidoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);

        spinnerCliente = findViewById(R.id.spinnerClientePedido);
        spinnerItemVenda = findViewById(R.id.spinnerItemVenda);
        editTextQuantidade = findViewById(R.id.editTextQuantidade);
        btnAdicionarItem = findViewById(R.id.btnAdicionarItem);
        btnRemoverItem = findViewById(R.id.btnRemoverItem);
        listViewItensPedido = findViewById(R.id.listViewItensPedido);
        radioGroupCondicaoPagamento = findViewById(R.id.radioGroupCondicaoPagamento);
        radioButtonAVista = findViewById(R.id.radioButtonAVista);
        radioButtonAPrazo = findViewById(R.id.radioButtonAPrazo);
        tvQtnParcelas = findViewById(R.id.textViewParcelas);
        spinnerEnderecoEntrega = findViewById(R.id.spinnerEnderecoEntrega);
        btnConcluirPedido = findViewById(R.id.btnConcluirPedido);
        btCancelarPedido = findViewById(R.id.btnCancelarPedido);
        tvInfoParcelas = findViewById(R.id.tvInfoParcelas);
        edQntParcelas = findViewById(R.id.edQntParcelas);
        btGerarParcelas = findViewById(R.id.btGerarParcelas);
        tvQtd = findViewById(R.id.textViewTotalItens);
        tvTotal = findViewById(R.id.textViewValorTotal);
        tvParcelas = findViewById(R.id.textViewParcelas);


        listaEnderecos = ec.findAllEndereco();
        EnderecoAdapter adapter = new EnderecoAdapter(this, listaEnderecos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEnderecoEntrega.setAdapter(adapter);

        listaCliente = cc.findAllCliente();
        ClienteAdapter adapterCli = new ClienteAdapter(this, listaCliente);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCliente.setAdapter(adapterCli);

        listaItem = ic.findAllItem();
        ItemAdapter adapterItem = new ItemAdapter(this, listaItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItemVenda.setAdapter(adapterItem);

        ItemPedidoAdapter ItemPedidoadapter = new ItemPedidoAdapter(this, listItemPedido);

        btCancelarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroPedido.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnConcluirPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pedido pedido = new Pedido();
                Endereco end = (Endereco) spinnerEnderecoEntrega.getSelectedItem();
                Cliente cli = (Cliente) spinnerCliente.getSelectedItem();
                pedido.setCodigoPessoa((int) cli.getCodigo());
                pedido.setValorTotal(vlrTotal);
                pedido.setCodigoEndereco(end.getCodigo());
                int pedidoid = 1 + pc.findAllPedido().size();
                for(int i = 0; i <= listItemPedido.size(); i++){
                    listItemPedido.get(i).setCodigoPedido(pedidoid);
                    pic.salvarItemPedido(listItemPedido.get(i));
                }
                pc.salvarPedido(pedido);
                limpaCampos();
                Toast.makeText(CadastroPedido.this, "Pedido salvo com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        radioButtonAPrazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculaTotalAPrazo();
            }
        });

        radioButtonAVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculaTotalAVista();
            }
        });

        btnAdicionarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemPedido pd = new ItemPedido();
                Item item = (Item) spinnerItemVenda.getSelectedItem();
                if (editTextQuantidade.getText().toString().equals("0") || editTextQuantidade.getText().toString().isEmpty()) {
                    Toast.makeText(CadastroPedido.this, "Por favor, preencha o campo de quantidade", Toast.LENGTH_SHORT).show();
                } else {
                    boolean itemJaExiste = false;
                    for (ItemPedido itemPedido : listItemPedido) {
                        if (itemPedido.getCodigoItem() == item.getCodigo()) {
                            itemJaExiste = true;
                            break;
                        }
                    }
                    if (itemJaExiste) {
                        Toast.makeText(CadastroPedido.this, "Item já está na lista", Toast.LENGTH_SHORT).show();
                    } else {
                        pd.setQuantidade(Integer.parseInt(editTextQuantidade.getText().toString()));
                        pd.setCodigoItem(item.getCodigo());
                        pd.setDesc(item.getDescricao());
                        vlrTotal += item.getVlrUnit() * pd.getQuantidade();
                        vlrTotalVista += item.getVlrUnit() * pd.getQuantidade();
                        tvTotal.setText("Valor Total: " + vlrTotal);
                        qtdTotal += pd.getQuantidade();
                        tvQtd.setText("Total de Itens: " + qtdTotal);
                        listItemPedido.add(pd);
                        listViewItensPedido.setAdapter(ItemPedidoadapter);
                    }
                }
            }
        });

        listViewItensPedido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelecionado = position;
            }
        });

        btnRemoverItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemSelecionado != -1) {
                    if (itemSelecionado < ItemPedidoadapter.getCount()) {
                        qtdTotal -= ItemPedidoadapter.getItem(itemSelecionado).getQuantidade();
                        tvQtd.setText("Total de Itens: " + qtdTotal);
                        ItemPedidoadapter.remove(ItemPedidoadapter.getItem(itemSelecionado));
                        ItemPedidoadapter.notifyDataSetChanged();
                    } else {

                        Toast.makeText(CadastroPedido.this, "Nenhum item selecionado para remover.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btGerarParcelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qntParcelas = 0;
                Double vlrParcelas;
                String parcelas = "";
                if(!edQntParcelas.getText().toString().equals("")){

                    qntParcelas = Integer.parseInt(edQntParcelas.getText().toString());

                    vlrParcelas = vlrTotal/qntParcelas;

                    for(int i = 0; i<qntParcelas;i++){
                        parcelas += "Parcela " + (i + 1) + " - R$ " + String.format("%.2f", vlrParcelas) + "\n";
                    }
                    tvQtnParcelas.setText(parcelas);
                }
            }
        });

        spinnerEnderecoEntrega.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                validaEndereco();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void validaEndereco(){
        Endereco end = (Endereco) spinnerEnderecoEntrega.getSelectedItem();
        if(!end.getCidade().toUpperCase().equals("TOLEDO")){
            vlrTotal = vlrTotal + 20.00;
            tvTotal.setText("Valor Total: " + vlrTotal);
        }else if(!end.getCidade().toUpperCase().equals("TOLEDO") && !end.getUf().toUpperCase().equals("PR")){
            vlrTotal = vlrTotal + 50.00;
            tvTotal.setText("Valor Total: " + vlrTotal);
        }
    }

    public void calculaTotalAPrazo() {
        tvInfoParcelas.setVisibility(View.VISIBLE);
        edQntParcelas.setVisibility(View.VISIBLE);
        btGerarParcelas.setVisibility(View.VISIBLE);
        tvParcelas.setVisibility(View.VISIBLE);
        tvQtnParcelas.setVisibility(View.VISIBLE);
        vlrTotal = vlrTotal + (vlrTotal * 0.05);
        tvTotal.setText("Valor Total: " + vlrTotal);
    }

    public void calculaTotalAVista() {
        tvInfoParcelas.setVisibility(View.GONE);
        edQntParcelas.setVisibility(View.GONE);
        btGerarParcelas.setVisibility(View.GONE);
        tvParcelas.setVisibility(View.GONE);
        tvQtnParcelas.setVisibility(View.GONE);
        vlrTotal = vlrTotalVista;
        vlrTotal = vlrTotal - (vlrTotal * 0.05);
        tvTotal.setText("Valor Total: " + vlrTotal);
    }

    public void limpaCampos(){
        vlrTotal = 0;
        qtdTotal = 0;
        edQntParcelas.setText("0");
        radioButtonAVista.setChecked(true);
        radioButtonAPrazo.setChecked(false);
        edQntParcelas.setText("0");
        listItemPedido.clear();
    }
}
