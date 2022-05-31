package main.java;

import java.util.*;

public class Family {

    private Map<String, Person> familyTree;

    public Family() {
        this.familyTree = new HashMap<String, Person>();
    }

    public Person getMember(String name) {
        return familyTree.get(name);
    }

    public void addMember(Person member) {
        if (!familyTree.containsKey(member.getName())) {
            familyTree.put(member.getName(), member);
        }
    }

    public Person addChild(Person parent, Person child) {

        ParentChild parentChild = new ParentChild(parent, child);
        child.addParentChildRelation(parentChild);
        parent.addParentChildRelation(parentChild);

        if (parent.getSpouse() != null) {
            ParentChild spouseParentChild = new ParentChild(parent.getSpouse(), child);
            child.addParentChildRelation(spouseParentChild);
            parent.getSpouse().addParentChildRelation(spouseParentChild);
        }

        addMember(parent);
        addMember(child);

        return child;
    }

    public void addMarriage(Person spouse1, Person spouse2) {
        Marriage marriage;

        if (spouse1.getGender().equals(Gender.FEMALE)) {
            marriage = new Marriage(spouse2, spouse1);
        } else {
            marriage = new Marriage(spouse1, spouse2);
        }

        marriage.getHusband().addMarriageRelation(marriage);
        marriage.getWife().addMarriageRelation(marriage);

        addMember(marriage.getHusband());
        addMember(marriage.getWife());
    }

    //Problem 3 - Find mother having the most number of daughters
    public List<Person> getMotherWithMostDaughters() {

        List<Person> mothersWithMostDaughters = new ArrayList<Person>();
        int maxDaughters = 0;

        for (Person member : familyTree.values()) {

            if (member.getGender().equals(Gender.FEMALE)) {
                if (member.getDaughters() != null && member.getDaughters().size() > 0) {
                    if (member.getDaughters().size() == maxDaughters) {
                        mothersWithMostDaughters.add(member);
                    } else if (member.getDaughters().size() > maxDaughters) {
                        maxDaughters = member.getDaughters().size();
                        mothersWithMostDaughters.clear();
                        mothersWithMostDaughters.add(member);
                    }
                }
            }
        }
        return mothersWithMostDaughters;
    }

    //Problem 4 - Use BFS to find shortest path of relations from source person to destination person
    public String getShortestRelation(Person source, Person dest) {

        Map<Person, Boolean> isVisited = new HashMap<Person, Boolean>();
        Map<Person, Relation> path = new HashMap<Person, Relation>();

        Queue<Person> personQueue = new LinkedList<Person>();

        //initialise the isVisited to false
        for (Person p : familyTree.values()) {
            isVisited.put(p, false);
        }

        //Add source node to queue
        personQueue.add(source);
        isVisited.put(source, true);

        while (!personQueue.isEmpty()) {
            Person current = personQueue.remove();

            if (current.equals(dest))
                break;

            //look at current's neighbours
            List<Relation> relations = current.getRelations();

            for (Relation relation : relations) {

                if (relation.getClass() == ParentChild.class) {
                    if (((ParentChild) relation).getParent().equals(current)) {
                        processPersonInQueue(((ParentChild) relation).getChild(), relation, isVisited, path, personQueue);
                    } else if (((ParentChild) relation).getChild().equals(current)) {
                        processPersonInQueue(((ParentChild) relation).getParent(), relation, isVisited, path, personQueue);
                    }
                } else if (relation.getClass() == Marriage.class) {
                    if (((Marriage) relation).getHusband().equals(current)) {
                        processPersonInQueue(((Marriage) relation).getWife(), relation, isVisited, path, personQueue);
                    } else if (((Marriage) relation).getWife().equals(current)) {
                        processPersonInQueue(((Marriage) relation).getHusband(), relation, isVisited, path, personQueue);
                    }
                }
            }
        }

        List<RelationType> relationList = getRelationListFromPath(path, source, dest);
        return getRelationshipName(relationList);

    }

    private void processPersonInQueue(Person p, Relation relation, Map<Person, Boolean> isVisited, Map<Person, Relation> path, Queue<Person> personQueue) {
        if (!isVisited.get(p)) {
            personQueue.add(p);
            isVisited.put(p, true);
            path.put(p, relation);
        }
    }

    private List<RelationType> getRelationListFromPath(Map<Person, Relation> path, Person source, Person dest) {

        List<RelationType> relationList = new ArrayList<RelationType>();

        //Generate list of relations from path
        for (Person p = dest; p != source; ) {

            if (path.get(p).getClass() == ParentChild.class) {
                if (((ParentChild) path.get(p)).getChild().equals(p)) {

                    if (p.getGender().equals(Gender.FEMALE)) {
                        relationList.add(RelationType.DAUGHTER);
                    } else {
                        relationList.add(RelationType.SON);
                    }
                    p = ((ParentChild) path.get(p)).getParent();
                } else if (((ParentChild) path.get(p)).getParent().equals(p)) {

                    if (p.getGender().equals(Gender.FEMALE)) {
                        relationList.add(RelationType.MOTHER);
                    } else {
                        relationList.add(RelationType.FATHER);
                    }
                    p = ((ParentChild) path.get(p)).getChild();
                }
            } else if (path.get(p).getClass() == Marriage.class) {

                if (((Marriage) path.get(p)).getWife().equals(p)) {
                    relationList.add(RelationType.WIFE);
                    p = ((Marriage) path.get(p)).getHusband();

                } else if (((Marriage) path.get(p)).getHusband().equals(p)) {

                    relationList.add(RelationType.HUSBAND);
                    p = ((Marriage) path.get(p)).getWife();
                }
            }
        }

        //Reverse list to get order from source to dest
        Collections.reverse(relationList);
        return relationList;
    }

    private String getRelationshipName(List<RelationType> relationList) {
        String relationships = "|";
        for (RelationType relationType : relationList) {
            relationships += relationType.toString() + "|";
        }

        //Derive simplified relation names
        for(Map.Entry<String, String> entry: getRelationMap().entrySet()){
            relationships=relationships.replace(entry.getKey(), entry.getValue());
        }

        relationships = relationships.substring(1, relationships.length() - 1); //Remove delimiters
        relationships = relationships.replace("|", "'S ");
        return relationships;
    }

    private Map<String, String> getRelationMap() {

        Map<String, String> relationMap = new LinkedHashMap<String, String>();
        relationMap.put("|MOTHER|SON|", "|BROTHER|");
        relationMap.put("|FATHER|SON|", "|BROTHER|");
        relationMap.put("|MOTHER|DAUGHTER|", "|SISTER|");
        relationMap.put("|FATHER|DAUGHTER|", "|SISTER|");
        relationMap.put("|SON|SON|", "|GRANDSON|");
        relationMap.put("|DAUGHTER|SON|", "|GRANDSON|");
        relationMap.put("|SON|DAUGHTER|", "|GRANDDAUGHTER|");
        relationMap.put("|DAUGHTER|DAUGHTER|", "|GRANDDAUGHTER|");
        relationMap.put("|FATHER|FATHER|", "|GRANDFATHER|");
        relationMap.put("|MOTHER|FATHER|", "|GRANDFATHER|");
        relationMap.put("|FATHER|MOTHER|", "|GRANDMOTHER|");
        relationMap.put("|MOTHER|MOTHER|", "|GRANDMOTHER|");
        relationMap.put("|FATHER|BROTHER|SON|", "|COUSIN|");
        relationMap.put("|FATHER|SISTER|SON|", "|COUSIN|");
        relationMap.put("|FATHER|BROTHER|DAUGHTER|", "|COUSIN|");
        relationMap.put("|FATHER|SISTER|DAUGHTER|", "|COUSIN|");
        relationMap.put("|MOTHER|BROTHER|SON|", "|COUSIN|");
        relationMap.put("|MOTHER|SISTER|SON|", "|COUSIN|");
        relationMap.put("|MOTHER|BROTHER|DAUGHTER|", "|COUSIN|");
        relationMap.put("|MOTHER|SISTER|DAUGHTER|", "|COUSIN|");
        relationMap.put("|WIFE|BROTHER|", "|BROTHER-IN-LAW|");
        relationMap.put("|HUSBAND|BROTHER|", "|BROTHER-IN-LAW|");
        relationMap.put("|SISTER|HUSBAND|", "|BROTHER-IN-LAW|");
        relationMap.put("|HUSBAND|SISTER|", "|SISTER-IN-LAW|");
        relationMap.put("|WIFE|SISTER|", "|SISTER-IN-LAW|");
        relationMap.put("|BROTHER|WIFE|", "|SISTER-IN-LAW|");
        relationMap.put("|MOTHER|SISTER|", "|MATERNAL AUNT|");
        relationMap.put("|MOTHER|SISTER-IN-LAW|", "|MATERNAL AUNT|");
        relationMap.put("|FATHER|SISTER|", "|PATERNAL AUNT|");
        relationMap.put("|FATHER|SISTER-IN-LAW|", "|PATERNAL AUNT|");
        relationMap.put("|MOTHER|BROTHER|", "|MATERNAL UNCLE|");
        relationMap.put("|MOTHER|BROTHER-IN-LAW|", "|MATERNAL UNCLE|");
        relationMap.put("|FATHER|BROTHER|", "|PATERNAL UNCLE|");
        relationMap.put("|FATHER|BROTHER-IN-LAW|", "|PATERNAL UNCLE|");

        return relationMap;
    }
}
