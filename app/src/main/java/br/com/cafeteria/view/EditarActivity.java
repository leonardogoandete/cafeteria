package br.com.cafeteria.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.cafeteria.R;
import br.com.cafeteria.dao.AppDatabase;
import br.com.cafeteria.entity.Cafe;

public class EditarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Cafe cafe = (Cafe) getIntent().getSerializableExtra("cafe");

        EditText tipo = findViewById(R.id.txtEditInputTipo);
        EditText origem = findViewById(R.id.txtEditInputOrigem);
        EditText descricao = findViewById(R.id.txtEditInputDescricao);

        tipo.setText(cafe.getTitulo());
        origem.setText(cafe.getOrigem());
        descricao.setText(cafe.getDescricao());

        Button buttonEditar = findViewById(R.id.btnAtualizar);

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    cafe.setTitulo(tipo.getText().toString());
                    cafe.setOrigem(origem.getText().toString());
                    cafe.setDescricao(descricao.getText().toString());
                    AppDatabase.getInstance(getApplicationContext()).createCafeDAO().update(cafe);
                    finish();
                }
            }
        });
    }
}