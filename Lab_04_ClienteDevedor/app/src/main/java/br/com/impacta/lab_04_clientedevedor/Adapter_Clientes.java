package br.com.impacta.lab_04_clientedevedor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nalmir on 03/09/2016.
 */
public class Adapter_Clientes extends BaseAdapter {

    private LayoutInflater mInflater;
    //
    private int resource;
    private ArrayList<HMAux> dados;

    public Adapter_Clientes(Context context, int resource, ArrayList<HMAux> dados) {
        this.mInflater = LayoutInflater.from(context);
        this.resource = resource;
        this.dados = dados;
    }

    @Override
    public int getCount() {
        return dados.size();
    }

    @Override
    public Object getItem(int position) {
        return dados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(dados.get(position).get(HMAux.ID));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(resource, parent, false);
        }

        // Acesso a Celula
        TextView tv = (TextView) convertView.findViewById(R.id.celula_tv_nome);

        // Acesso aos dados da posicao especifica
        HMAux item = dados.get(position);

        // Mover Dados
        tv.setText(item.get(HMAux.TEXTO_01));

        // Regra de Negócio
        if (item.get(HMAux.TEXTO_02).equalsIgnoreCase("0")) {
            tv.setTextColor(0xFF000000);
        } else {
            tv.setTextColor(0xFFFF0000);
        }

        return convertView;
    }
}
