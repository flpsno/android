package br.com.impacta.t_018_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by nalmir on 01/10/2016.
 */
public class F01 extends Fragment {

    private Button btn_mostrar;
    private CheckBox cb_android;

    //private MainActivity host;

    public interface IF01 {
        public void mudarTextoF1(String texto);
    }

    private IF01 delegate;

    public void setOnMudarTextoF1(IF01 delegate) {
        this.delegate = delegate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.f01,
                container,
                false
        );

        inicializarVariavel(view);
        inicializarAcao();

        return view;
    }

    private void inicializarVariavel(View view) {
        btn_mostrar = (Button)
                view.findViewById(R.id.f01_btn_mostrar);
        cb_android = (CheckBox)
                view.findViewById(R.id.f01_cb_android);
        //
        //host = (MainActivity) getActivity();
    }

    private void inicializarAcao() {
        btn_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_android.isChecked()){
                    //
                    //host.mudarTextoF1("Novo Texto");
                    if (delegate != null){
                        delegate.mudarTextoF1("Novo Texto");
                    }
                    //
                } else {
                    Toast.makeText(
                            getActivity(),
                            "Acionou o Mostrar Do F01",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }
}
