package pl.sda.orange.enumerable;

public enum DBConfig {

    DB_URL("jdbc:h2:~/databases/jdbc"),
    USER("sa"),
    PASSWORD("sa");

    private String value;

    DBConfig(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }


}
