package main.java;

public class ParentChild extends Relation {

    public ParentChild(Person parent, Person child) {
        this.personA = parent;
        this.personB = child;
    }

    public Person getParent() {
        return personA;
    }

    public Person getChild() {
        return personB;
    }
}
