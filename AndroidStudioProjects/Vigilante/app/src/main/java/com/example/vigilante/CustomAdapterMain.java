package com.example.vigilante;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapterMain extends ArrayAdapter<String> {

    private int resourceLayout;
    private List<String> incidentes;
    Context mContext;

    public CustomAdapterMain(List<String> incidentes, int resource, Context context) {
        super(context, resource, incidentes);
        this.resourceLayout = resource;
        this.incidentes = incidentes;
        this.mContext=context;

    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }
        ImageView imageView5 = v.findViewById(R.id.imageView5);
        imageView5.setImageResource(R.drawable.default_image_thumbnail);
        TextView titulo = v.findViewById(R.id.textView9);
        titulo.setText(incidentes.get(position));

        return v;
    }

}
