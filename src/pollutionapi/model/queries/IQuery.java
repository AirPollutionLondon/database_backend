package pollutionapi.model.queries;

public interface IQuery<T> {
    boolean filter(T object);
}
