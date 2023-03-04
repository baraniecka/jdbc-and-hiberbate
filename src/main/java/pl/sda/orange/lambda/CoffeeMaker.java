package pl.sda.orange.lambda;

@FunctionalInterface
public interface CoffeeMaker {

    String prepare(int water, String coffeeType);
}
