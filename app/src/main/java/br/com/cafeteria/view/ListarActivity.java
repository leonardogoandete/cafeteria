package br.com.cafeteria.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import br.com.cafeteria.R;
import br.com.cafeteria.adapter.LinhaCafeAdapter;
import br.com.cafeteria.dao.AppDatabase;
import br.com.cafeteria.dao.CafeDAO;
import br.com.cafeteria.entity.Cafe;

public class ListarActivity extends AppCompatActivity {

    private ListView listCafes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listCafes = this.findViewById(R.id.listViewCafe);
        CafeDAO cafeDAO = AppDatabase.getInstance(getApplicationContext()).createCafeDAO();
        getAll(cafeDAO.getAllCafe());
    }

    protected void getAll(List<Cafe> cafes){
        listCafes.setAdapter(new LinhaCafeAdapter(this,cafes));
    }

    @Override
    protected void onResume() {
        super.onResume();
        CafeDAO cafeDAO = AppDatabase.getInstance(getApplicationContext()).createCafeDAO();
        List<Cafe> cafes = cafeDAO.getAllCafe();
        updateListView(cafes);
    }

    private void updateListView(List<Cafe> cafes) {
        listCafes.setAdapter(new LinhaCafeAdapter(this,cafes));
    }
}