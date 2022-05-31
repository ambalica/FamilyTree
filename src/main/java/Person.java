package main.java;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private Gender gender;
    private List<Relation> relations;

    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        this.relations = new ArrayList<Relation>();
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void addParentChildRelation(ParentChild parentChild) {
        this.relations.add(parentChild);
    }

    public void addMarriageRelation(Marriage marriage) {
        this.relations.add(marriage);
    }

    public Person getSpouse() {
        Person spouse = null;
        for (Relation relation : relations) {
            if (relation.getClass() == Marriage.class) {
                if (((Marriage) relation).getHusband().equals(this)) {
                    spouse = ((Marriage) relation).getWife();
                } else if (((Marriage) relation).getWife().equals(this)) {
                    spouse = ((Marriage) relation).getHusband();
                }
            }
        }
        return spouse;
    }

    public List<Person> getDaughters() {

        List<Person> daughters = new ArrayList<Person>();

        for (Relation relation : relations) {
            if (relation.getClass() == ParentChild.class) {
                if (((ParentChild) relation).getParent().equals(this) &&
                        ((ParentChild) relation).getChild().getGender().equals(Gender.FEMALE)) {
                    daughters.add(((ParentChild) relation).getChild());
                }
            }
        }
        return daughters;
    }
}
