package br.com.cafeteria.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.List;

import br.com.cafeteria.R;
import br.com.cafeteria.dao.AppDatabase;
import br.com.cafeteria.dao.CafeDAO;
import br.com.cafeteria.entity.Cafe;
import br.com.cafeteria.view.EditarActivity;
import br.com.cafeteria.view.ListarActivity;

public class LinhaCafeAdapter extends BaseAdapter {
    private static LayoutInflater layoutInflater = null;

    List<Cafe> cafes;

    private ListarActivity listarActivity;

    private EditarActivity editarActivity;

    public LinhaCafeAdapter(ListarActivity listarActivity, List<Cafe> cafes){
        this.cafes = cafes;
        this.listarActivity = listarActivity;
        this.layoutInflater = (LayoutInflater) this.listarActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return cafes.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final View viewLinhasCafe = layoutInflater.inflate(R.layout.list_dados_tipos_cafe_row,null);
        ImageView imageView = viewLinhasCafe.findViewById(R.id.imagemDado);
        AppCompatTextView textViewTitulo = viewLinhasCafe.findViewById(R.id.txtViewTituloCard);
        AppCompatTextView textViewDescricao = viewLinhasCafe.findViewById(R.id.txtViewDescricaoCard);
        AppCompatTextView textViewOrigem = viewLinhasCafe.findViewById(R.id.txtViewOrigemCard);

        imageView.setImageResource(R.drawable.cafe);
        textViewTitulo.setText(String.valueOf(cafes.get(position).getTitulo()));
        textViewDescricao.setText(String.valueOf(cafes.get(position).getDescricao()));
        textViewOrigem.setText(String.valueOf(cafes.get(position).getOrigem()));

        AppCompatButton buttonCompartilhar = viewLinhasCafe.findViewById(R.id.btnCompartilhar);
        buttonCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cafe cafe = new Cafe(String.valueOf(cafes.get(position).getTitulo()),
                        String.valueOf(cafes.get(position).getDescricao()),
                        String.valueOf(cafes.get(position).getOrigem())
                );

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, cafe.toString());
                intent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(intent, null);
                listarActivity.startActivity(shareIntent);

            }
        });

        AppCompatButton buttonEditar = viewLinhasCafe.findViewById(R.id.btnEditar);
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cafe cafe = cafes.get(position);
                Intent intent = new Intent(listarActivity,EditarActivity.class);
                intent.putExtra("cafe",cafe);
                listarActivity.startActivity(intent);
            }
        });

        AppCompatImageButton buttonDelete = viewLinhasCafe.findViewById(R.id.btnDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cafe cafeParaDeletar = cafes.get(position);

                cafes.remove(cafeParaDeletar);
                notifyDataSetChanged();

                CafeDAO cafeDAO = AppDatabase.getInstance(listarActivity.getApplicationContext()).createCafeDAO();
                cafeDAO.delete(cafeParaDeletar);

            }
        });

        return viewLinhasCafe;
    }
}
