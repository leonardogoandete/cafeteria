package br.com.cafeteria.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import br.com.cafeteria.R;
import br.com.cafeteria.adapter.ItemListaMenuAdapter;
import br.com.cafeteria.model.ItemLista;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
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

        listView = this.findViewById(R.id.listaItensMenu);
        ArrayList<ItemLista> itensListas = new ArrayList<>();
        itensListas.add(new ItemLista(R.drawable.adicionar,getString(R.string.btnMenuCadadastrar)));
        itensListas.add(new ItemLista(R.drawable.listar,getString(R.string.btnMenuListar)));
        itensListas.add(new ItemLista(R.drawable.sobre,getString(R.string.btnMenuSobre)));

        ItemListaMenuAdapter itemListaMenuAdapter = new ItemListaMenuAdapter(this,R.layout.lista_menu_row,itensListas);
        listView.setAdapter(itemListaMenuAdapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0://cadastrar
                Intent cadastrar = new Intent(this,CadastrarActivity.class);
                startActivity(cadastrar);
                break;
            case 1://listar
                Intent listar = new Intent(this,ListarActivity.class);
                startActivity(listar);
                break;
            case 2:
                Intent sobre = new Intent(this,SobreActivity.class);
                startActivity(sobre);
                break;
        }
    }
}