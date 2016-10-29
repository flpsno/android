package curso.com.br.t_032_library;

import android.os.Bundle;
import android.widget.Toast;

import br.com.impacta.curso.hlib.BaseActivity;
import br.com.impacta.curso.hlib.ToolBox;

/**
 * Created by nalmir on 29/10/2016.
 */
public class Login extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void chamarLogin(String nome, String senha) {
        Toast.makeText(
                getBaseContext(),
                ToolBox.fazerSaudacao(nome),
                Toast.LENGTH_SHORT
        ).show();
    }
}
