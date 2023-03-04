package pl.sda.orange.lambda;

public class LambdaExercises {

    public static void main(String[] args) {

        BoysDontCry boy = new ChlopakiNiePlacza(); //przykład polimorfizmu -> przypinam klasę-dziecko do interfejsu-rodzica
        boy.silnoreki();

        ((ChlopakiNiePlacza)boy).sing(); //rzutowanie - interface BoysDontCry nie ma takiej metody, nie mogę się dostać bezpośrednio

        ChlopakiNiePlacza boy2 = new ChlopakiNiePlacza();
        boy2.sing();

        BoysDontCry annonymousClassReference = new BoysDontCry() {
            @Override
            public void silnoreki() {
                System.out.println("Anonimowy Silnoręki");
            }
        };


        BoysDontCry firstLambda = () -> {}; // implementacja metody z interface'u
        BoysDontCry secondLambda = () -> System.out.println("Lambda Silnoręki!");
        secondLambda.silnoreki(); //muszę wywołać metodę na obiekcie klasy abstrakcyjnej

        BoysDontCry thirdLambda = () -> {
            System.out.println("First");
            System.out.println("Second");
        };


    }
}
