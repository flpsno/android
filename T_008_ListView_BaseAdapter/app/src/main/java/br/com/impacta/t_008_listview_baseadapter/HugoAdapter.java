package br.com.impacta.t_008_listview_baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nalmir on 03/09/2016.
 */
public class HugoAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
    //
    private int resource;
    //
    private ArrayList<HMAux> dados;

    private int indice = 0;

    private boolean bNovo = false;

    private long idselecionado = -1;

    public void setIdselecionado(long idselecionado) {
        this.idselecionado = idselecionado;
        //
        notifyDataSetChanged();
    }

    public HugoAdapter(Context context, int resource, ArrayList<HMAux> dados) {
        this.context = context;
        //
//        mInflater = (LayoutInflater)
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater = LayoutInflater.from(context);
        //
        this.resource = resource;
        this.dados = dados;
    }

    // Devolve a quantidade de registros no adapter
    @Override
    public int getCount() {
        return dados.size();
    }

    // Devolve o registro inteira da posicao referenciada pelo parametro position;
    @Override
    public Object getItem(int position) {
        return dados.get(position);
    }

    // Devolver o ID customizado para utilizacao
    @Override
    public long getItemId(int position) {
        HMAux item = dados.get(position);
        //
        return Long.parseLong(item.get(HMAux.ID));
    }

    // Delvor um celula pronta com os dados já processados
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            bNovo = true;
            //
            convertView = mInflater.inflate(resource, parent, false);
        } else {
            bNovo = false;
        }

        // Me deu acesso ao layout da Celula
        LinearLayout ll = (LinearLayout)
                convertView.findViewById(R.id.celula_ll);
        LinearLayout ll_status = (LinearLayout)
                convertView.findViewById(R.id.celula_status);
        TextView tv_nome = (TextView)
                convertView.findViewById(R.id.celula_tv_nome);
        TextView tv_indice = (TextView)
                convertView.findViewById(R.id.celula_indice);

        // Acesso aos dados da posicao refenciada pelo parametro position
        HMAux item = dados.get(position);


        // Processar os dados
        if (idselecionado == Long.parseLong(item.get(HMAux.ID))){
            ll.setBackgroundColor(0xFF00FF00);
        } else {
            ll.setBackgroundColor(0x00000000);
        }
        //
        if ((position % 2) == 0) {
            ll_status.setBackgroundColor(0xFF0000FF);
        } else {
            ll_status.setBackgroundColor(0xFFFF00FF);
        }

        tv_nome.setText(item.get(HMAux.TEXTO_01));

        if (bNovo){
            indice += 1;
            //
            tv_indice.setText(String.valueOf(indice));
        }

        return convertView;
    }
}
