package pl.sda.orange.lambda;

@FunctionalInterface
public interface BoysDontCry {

    //functional interface because it has only one abstract method
    void silnoreki();

    //metoda domyślna nie jest abstrakcyjna - ma ciało
    default void cry() {
    }
}
