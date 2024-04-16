package br.com.cafeteria.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.cafeteria.R;
import br.com.cafeteria.dao.AppDatabase;
import br.com.cafeteria.entity.Cafe;

public class CadastrarActivity extends AppCompatActivity {

    TextView tipo;
    TextView descricao;
    TextView origem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button botao = findViewById(R.id.btnCadastrar);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo = findViewById(R.id.txtInputTipo);
                descricao = findViewById(R.id.txtInputDescricao);
                origem = findViewById(R.id.txtInputOrigem);


                if (tipo.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), getString(R.string.msgToastCadTipo), Toast.LENGTH_LONG).show();
                    tipo.requestFocus();
                } else if (descricao.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.msgToastCadDescricao), Toast.LENGTH_LONG).show();
                    descricao.requestFocus();
                } else if (origem.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.msgToastCadOrigem), Toast.LENGTH_LONG).show();
                    origem.requestFocus();
                } else {
                    Cafe cafe = new Cafe();
                    cafe.setTitulo(tipo.getText().toString());
                    cafe.setDescricao(descricao.getText().toString());
                    cafe.setOrigem(origem.getText().toString());
                    AppDatabase.getInstance(getApplicationContext()).createCafeDAO().insert(cafe);
                    Toast.makeText(getApplicationContext(), getString(R.string.msgToastCadOk), Toast.LENGTH_LONG).show();
                    limparCampos();
                    finish();

                }
            }
        });
    }

    private void limparCampos(){
        tipo = findViewById(R.id.txtInputTipo);
        descricao = findViewById(R.id.txtInputDescricao);
        origem = findViewById(R.id.txtInputOrigem);

        tipo.setText("");
        descricao.setText("");
        origem.setText("");
        voltarPaginaMenu();
    }
    private void voltarPaginaMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}