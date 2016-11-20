package impacta.com.br.ex_mvp;

/**
 * Created by nalmir on 19/11/2016.
 */
public interface Dao<T> {

    void inserirAtualizar(T item);

    void apagar(String sqlQuery);

    T obter(String sqlQuery);


}
