package impacta.com.br.t_036_bluetooth;

/**
 * Created by nalmir on 19/11/2016.
 */
public interface Mapper<From, To> {
    To map(From item);
}
