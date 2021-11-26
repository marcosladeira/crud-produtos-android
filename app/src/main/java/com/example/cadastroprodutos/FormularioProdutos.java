package com.example.cadastroprodutos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cadastroprodutos.BDHelper.ProdutosBd;
import com.example.cadastroprodutos.model.Produtos;

public class FormularioProdutos extends AppCompatActivity {

    EditText editText_NomeProduto, editText_Descricao, editText_Quantidade;
    Button btn_Polimorf;
    Produtos editarProduto, produto;
    ProdutosBd bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produtos);

        produto = new Produtos();

        bdHelper = new ProdutosBd(FormularioProdutos.this);

        Intent intent = getIntent();
        editarProduto = (Produtos) intent.getSerializableExtra("produto-escolhido");

        editText_NomeProduto = (EditText) findViewById(R.id.editText_NomeProduto);
        editText_Descricao = (EditText) findViewById(R.id.editText_Descricao);
        editText_Quantidade = (EditText) findViewById(R.id.editText_Quantidade);

        btn_Polimorf = (Button) findViewById(R.id.btn_Poliform);

        if (editarProduto !=null){
            btn_Polimorf.setText("Modificar");

            editText_NomeProduto.setText(editarProduto.getNomeProduto());
            editText_Descricao.setText(editarProduto.getDescricao());
            editText_Quantidade.setText(editarProduto.getQuantidade()+"");

            produto.setId(editarProduto.getId());


        }else{
            btn_Polimorf.setText("Cadastrar");
        }

        btn_Polimorf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produto.setNomeProduto(editText_NomeProduto.getText().toString());
                produto.setDescricao(editText_Descricao.getText().toString());
                produto.setQuantidade(Integer.parseInt(editText_Quantidade.getText().toString()));


                if (btn_Polimorf.getText().toString().equals("Cadastrar")) {
                    bdHelper.salvarProduto(produto);
                    bdHelper.close();


                }else{
                    bdHelper.alterarProduto(produto);
                    bdHelper.close();
                }

            }
                                        }
        );
    }

}
