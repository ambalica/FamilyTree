package main.test;

import main.java.Gender;
import main.java.Marriage;
import main.java.ParentChild;
import main.java.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PersonTest {

    Person shan;
    Person anga;
    Person ish;
    Person satya;
    Person anvi;
    Person vanya;

    @Before
    public void setUp() {
        shan = new Person("Shan", Gender.MALE);
        anga = new Person("Anga", Gender.FEMALE);
        ish = new Person("Ish", Gender.MALE);
        satya = new Person("Satya", Gender.FEMALE);
        anvi = new Person("Anvi", Gender.FEMALE);
        vanya = new Person("Vanya", Gender.FEMALE);

        Marriage marriage = new Marriage(shan,anga);
        shan.addMarriageRelation(marriage);
        anga.addMarriageRelation(marriage);

        ParentChild parentChildIsh = new ParentChild(shan, ish);
        shan.addParentChildRelation(parentChildIsh);
        anga.addParentChildRelation(parentChildIsh);
        ish.addParentChildRelation(parentChildIsh);

        ParentChild parentChildSatya = new ParentChild(shan, satya);
        shan.addParentChildRelation(parentChildSatya);
        anga.addParentChildRelation(parentChildSatya);
        satya.addParentChildRelation(parentChildSatya);

        ParentChild parentChildAnvi = new ParentChild(shan, anvi);
        shan.addParentChildRelation(parentChildAnvi);
        anga.addParentChildRelation(parentChildAnvi);
        anvi.addParentChildRelation(parentChildAnvi);

        ParentChild parentChildVanya = new ParentChild(anvi, vanya);
        anvi.addParentChildRelation(parentChildVanya);
        vanya.addParentChildRelation(parentChildVanya);
    }

    @Test
    public void testGetSpouseFromHusband() {
        Assert.assertEquals(anga,shan.getSpouse());
    }

    @Test
    public void testGetSpouseFromWife() {
        Assert.assertEquals(shan,anga.getSpouse());
    }

    @Test
    public void testGetSpouseNull() {
        Assert.assertNull(satya.getSpouse());
    }

    @Test
    public void testGetDaughtersShan() {
        List<Person> daughters = shan.getDaughters();
        Assert.assertTrue(daughters.contains(anvi));
        Assert.assertTrue(daughters.contains(satya));
        Assert.assertFalse(daughters.contains(vanya));
        Assert.assertFalse(daughters.contains(ish));
        Assert.assertEquals(2, daughters.size());
    }

    @Test
    public void testGetDaughtersAnga() {
        List<Person> daughters = shan.getDaughters();
        Assert.assertTrue(daughters.contains(anvi));
        Assert.assertTrue(daughters.contains(satya));
        Assert.assertFalse(daughters.contains(vanya));
        Assert.assertFalse(daughters.contains(ish));
        Assert.assertEquals(2, daughters.size());
    }

    @Test
    public void testGetDaughtersAnvi() {
        List<Person> daughters = anvi.getDaughters();
        Assert.assertTrue(daughters.contains(vanya));
        Assert.assertEquals(1, daughters.size());
    }

    @Test
    public void testGetDaughtersIsh() {
        List<Person> daughters = ish.getDaughters();
        Assert.assertEquals(0, daughters.size());
    }
}

