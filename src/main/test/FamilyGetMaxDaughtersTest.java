package main.test;

import main.java.Family;
import main.java.Gender;
import main.java.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FamilyGetMaxDaughtersTest {

    Family family = new Family();

    @Before
    public void setUp() {

        Person shan = new Person("Shan", Gender.MALE);
        Person anga = new Person("Anga", Gender.FEMALE);
        family.addMarriage(shan, anga);

        Person ish = new Person("Ish", Gender.MALE);
        family.addChild(shan, ish);

        Person chit = new Person("Chit", Gender.MALE);
        Person ambi = new Person("Ambi", Gender.FEMALE);
        family.addChild(shan, chit);
        family.addMarriage(chit, ambi);

        Person vich = new Person("Vich", Gender.MALE);
        Person lika = new Person("Lika", Gender.FEMALE);
        family.addChild(shan, vich);
        family.addMarriage(vich, lika);

        Person satya = new Person("Satya", Gender.FEMALE);
        Person vyan = new Person("Vyan", Gender.MALE);
        family.addChild(shan, satya);
        family.addMarriage(vyan, satya);

        Person drita = new Person("Drita", Gender.MALE);
        Person jaya = new Person("Jaya", Gender.FEMALE);
        family.addChild(ambi, drita);
        family.addMarriage(drita, jaya);

        Person vrita = new Person("Vrita", Gender.MALE);
        family.addChild(ambi, vrita);

        Person vila = new Person("Vila", Gender.MALE);
        Person jnki = new Person("Jnki", Gender.FEMALE);
        family.addChild(vich, vila);
        family.addMarriage(vila, jnki);

        Person chika = new Person("Chika", Gender.FEMALE);
        Person kpila = new Person("Kpila", Gender.MALE);
        family.addChild(vich, chika);
        family.addMarriage(kpila, chika);

        Person satvy = new Person("Satvy", Gender.FEMALE);
        Person asva = new Person("Asva", Gender.MALE);
        family.addChild(satya, satvy);
        family.addMarriage(asva, satvy);

        Person savya = new Person("Savya", Gender.MALE);
        Person krpi = new Person("Krpi", Gender.FEMALE);
        family.addChild(satya, savya);
        family.addMarriage(savya, krpi);

        Person saayan = new Person("Saayan", Gender.MALE);
        Person mina = new Person("Mina", Gender.FEMALE);
        family.addChild(vyan, saayan);
        family.addMarriage(saayan, mina);

        Person misa = new Person("Misa", Gender.MALE);
        family.addChild(mina, misa);

        Person kriya = new Person("Kriya", Gender.MALE);
        family.addChild(krpi, kriya);

        Person lavnya = new Person("Lavnya", Gender.FEMALE);
        Person gru = new Person("Gru", Gender.MALE);
        family.addChild(vila, lavnya);
        family.addMarriage(gru, lavnya);

        Person mnu = new Person("Mnu", Gender.MALE);
        Person driya = new Person("Driya", Gender.FEMALE);
        family.addChild(jaya, driya);
        family.addMarriage(mnu, driya);

        Person jata = new Person("Jata", Gender.MALE);
        family.addChild(jaya, jata);
    }

    @Test
    public void testGetMotherWithMostDaughters() {
        List<Person> mothersWithMostDaughters = family.getMotherWithMostDaughters();
        Assert.assertTrue(mothersWithMostDaughters.contains(family.getMember("Jaya")));
        Assert.assertTrue(mothersWithMostDaughters.contains(family.getMember("Satya")));
        Assert.assertTrue(mothersWithMostDaughters.contains(family.getMember("Lika")));
        Assert.assertTrue(mothersWithMostDaughters.contains(family.getMember("Jnki")));
        Assert.assertTrue(mothersWithMostDaughters.contains(family.getMember("Anga")));
        Assert.assertEquals(5,mothersWithMostDaughters.size());

        Person drini = new Person("Drini", Gender.FEMALE);
        family.addChild(family.getMember("Jaya"),drini);

        mothersWithMostDaughters = family.getMotherWithMostDaughters();
        Assert.assertTrue(mothersWithMostDaughters.contains(family.getMember("Jaya")));
        Assert.assertFalse(mothersWithMostDaughters.contains(family.getMember("Satya")));
        Assert.assertFalse(mothersWithMostDaughters.contains(family.getMember("Lika")));
        Assert.assertEquals(1,mothersWithMostDaughters.size());
    }
}
