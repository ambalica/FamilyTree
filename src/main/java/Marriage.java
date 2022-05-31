package main.java;

public class Marriage extends Relation {

    public Marriage(Person husband, Person wife) {
        this.personA = husband;
        this.personB = wife;
    }

    public Person getHusband() {
        return personA;
    }

    public Person getWife() {
        return personB;
    }
}
