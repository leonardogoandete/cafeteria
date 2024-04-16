package br.com.cafeteria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import br.com.cafeteria.R;
import br.com.cafeteria.model.ItemLista;

public class ItemListaMenuAdapter extends ArrayAdapter<ItemLista> {
    private Context mContext;
    private int mResource;

    public ItemListaMenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemLista> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent,false);

        ImageView imageView = convertView.findViewById(R.id.imgMenuOpcao);

        TextView txtName = convertView.findViewById(R.id.txtNomeOpc);

        imageView.setImageResource(getItem(position).getImagem());

        txtName.setText(getItem(position).getNome());

        return convertView;
    }
}
