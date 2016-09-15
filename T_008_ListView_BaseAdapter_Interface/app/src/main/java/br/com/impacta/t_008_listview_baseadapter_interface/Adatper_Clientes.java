package br.com.impacta.t_008_listview_baseadapter_interface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nalmir on 10/09/2016.
 */
public class Adatper_Clientes extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
    //
    private int resource;
    private ArrayList<HMAux> dados;

    public interface IADTC {
        public void chamarCliente(String texto);
    }

    private IADTC delegate;

    public void setOnClienteListener(IADTC delegate) {
        this.delegate = delegate;
    }

    public Adatper_Clientes(Context context, int resource, ArrayList<HMAux> dados) {
        this.context = context;
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
        // Recuperando o registro da posicao referenciada
        HMAux item = dados.get(position);

        TextView tv_nome = (TextView) convertView.findViewById(R.id.celula_tv_nome);
        tv_nome.setText(item.get(HMAux.TEXTO_01));
        //
        Button btn_ativar = (Button) convertView.findViewById(R.id.celula_btn_ativar);
        btn_ativar.setTag(item.get(HMAux.ID));
        btn_ativar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo_cliente = (String) v.getTag();
                //
                if (delegate != null) {
                    delegate.chamarCliente(codigo_cliente);
                }
            }
        });
        //
        return convertView;
    }
}
