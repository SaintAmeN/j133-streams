package pl.sda.j133.streams.podstawy.zad1;

/**
 * @author Paweł Recław, AmeN
 * @project j133-streams
 * @created 10.12.2022
 */
@FunctionalInterface
public interface IWypisywacz<T> {
    void wypisz(T obiekt);
}