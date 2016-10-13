package br.com.impacta.t_018_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nalmir on 01/10/2016.
 */
public class F02 extends Fragment {

    private TextView tv_valores;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.f02,
                container,
                false
        );

        inicializarVariavel(view);
        inicializarAcao();

        return view;
    }

    private void inicializarVariavel(View view) {
        tv_valores = (TextView)
                view.findViewById(R.id.f02_tv_valores);
    }

    private void inicializarAcao() {

    }

    public void mudarTexto(String texto){
        tv_valores.setText(texto);
    }
}
