package main.test;

import main.java.Family;
import main.java.Gender;
import main.java.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FamilyRelationTest {

    Family family = new Family();

    @Before
    public void setUp() {

        Person shan = new Person("Shan", Gender.MALE);
        Person anga = new Person("Anga", Gender.FEMALE);
        family.addMarriage(shan, anga);

        Person ish = new Person("Ish", Gender.MALE);
        family.addChild(shan, ish);

        Person anvi = new Person("Anvi", Gender.FEMALE);
        Person ansh = new Person("Ansh", Gender.MALE);
        family.addChild(shan, anvi);
        family.addMarriage(ansh,anvi);

        Person chit = new Person("Chit", Gender.MALE);
        Person ambi = new Person("Ambi", Gender.FEMALE);
        family.addChild(shan, chit);
        family.addMarriage(ambi, chit);

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
    public void testFather(){
        String relation = family.getShortestRelation(family.getMember("Ish"),family.getMember("Shan"));
        Assert.assertEquals("FATHER", relation);
    }

    @Test
    public void testMother(){
        String relation = family.getShortestRelation(family.getMember("Chit"),family.getMember("Anga"));
        Assert.assertEquals("MOTHER", relation);
    }

    @Test
    public void testSon(){
        String relation = family.getShortestRelation(family.getMember("Ambi"),family.getMember("Drita"));
        Assert.assertEquals("SON", relation);
    }

    @Test
    public void testDaughter(){
        String relation = family.getShortestRelation(family.getMember("Vich"),family.getMember("Chika"));
        Assert.assertEquals("DAUGHTER", relation);
    }

    @Test
    public void testWife(){
        String relation = family.getShortestRelation(family.getMember("Mnu"),family.getMember("Driya"));
        Assert.assertEquals("WIFE", relation);
    }

    @Test
    public void testHusband(){
        String relation = family.getShortestRelation(family.getMember("Driya"),family.getMember("Mnu"));
        Assert.assertEquals("HUSBAND", relation);
    }

    @Test
    public void testBrother(){
        String relation = family.getShortestRelation(family.getMember("Savya"),family.getMember("Saayan"));
        Assert.assertEquals("BROTHER", relation);
    }

    @Test
    public void testSister(){
        String relation = family.getShortestRelation(family.getMember("Vila"),family.getMember("Chika"));
        Assert.assertEquals("SISTER", relation);
    }

    @Test
    public void testGrandfatherFathersFather(){
        String relation = family.getShortestRelation(family.getMember("Lavnya"),family.getMember("Vich"));
        Assert.assertEquals("GRANDFATHER", relation);
    }

    @Test
    public void testGrandfatherMothersFather(){
        String relation = family.getShortestRelation(family.getMember("Savya"),family.getMember("Shan"));
        Assert.assertEquals("GRANDFATHER", relation);
    }

    @Test
    public void testGrandmotherFathersMother(){
        String relation = family.getShortestRelation(family.getMember("Kriya"),family.getMember("Satya"));
        Assert.assertEquals("GRANDMOTHER", relation);
    }

    @Test
    public void testGrandmotherMothersMother(){
        String relation = family.getShortestRelation(family.getMember("Satvy"),family.getMember("Anga"));
        Assert.assertEquals("GRANDMOTHER", relation);
    }

    @Test
    public void testGrandsonSonsSon(){
        String relation = family.getShortestRelation(family.getMember("Ambi"),family.getMember("Jata"));
        Assert.assertEquals("GRANDSON", relation);
    }

    @Test
    public void testGrandsonDaughtersSon(){
        String relation = family.getShortestRelation(family.getMember("Shan"),family.getMember("Saayan"));
        Assert.assertEquals("GRANDSON", relation);
    }

    @Test
    public void testGranddaughterSonsDaughter(){
        String relation = family.getShortestRelation(family.getMember("Shan"),family.getMember("Chika"));
        Assert.assertEquals("GRANDDAUGHTER", relation);
    }


    @Test
    public void testGranddaughterDaughtersDaughter(){
        String relation = family.getShortestRelation(family.getMember("Shan"),family.getMember("Satvy"));
        Assert.assertEquals("GRANDDAUGHTER", relation);
    }

    @Test
    public void testCousinFathersBrothersSon(){
        String relation = family.getShortestRelation(family.getMember("Vrita"),family.getMember("Vila"));
        Assert.assertEquals("COUSIN", relation);
    }

    @Test
    public void testCousinFathersSistersDaughter(){
        String relation = family.getShortestRelation(family.getMember("Chika"),family.getMember("Satvy"));
        Assert.assertEquals("COUSIN", relation);
    }

    @Test
    public void testCousinMothersBrothersSon(){
        String relation = family.getShortestRelation(family.getMember("Satvy"),family.getMember("Vila"));
        Assert.assertEquals("COUSIN", relation);
    }

    @Test
    public void testCousinMothersBrothersDaughter(){
        String relation = family.getShortestRelation(family.getMember("Savya"),family.getMember("Chika"));
        Assert.assertEquals("COUSIN", relation);
    }

    @Test
    public void testBrotherInLawSpousesBrother(){
        String relation = family.getShortestRelation(family.getMember("Jaya"),family.getMember("Vrita"));
        Assert.assertEquals("BROTHER-IN-LAW", relation);
    }

    @Test
    public void testBrotherInLawSiblingsHusband(){
        String relation = family.getShortestRelation(family.getMember("Saayan"),family.getMember("Asva"));
        Assert.assertEquals("BROTHER-IN-LAW", relation);
    }

    @Test
    public void testSisterInLawSpousesSister(){
        String relation = family.getShortestRelation(family.getMember("Jnki"),family.getMember("Chika"));
        Assert.assertEquals("SISTER-IN-LAW", relation);
    }
    @Test
    public void testSisterInLawSiblingsWife(){
        String relation = family.getShortestRelation(family.getMember("Saayan"),family.getMember("Krpi"));
        Assert.assertEquals("SISTER-IN-LAW", relation);
    }

    @Test
    public void testMaternalAuntMotherSisterInLaw(){
        String relation = family.getShortestRelation(family.getMember("Satvy"),family.getMember("Lika"));
        Assert.assertEquals("MATERNAL AUNT", relation);
    }


    @Test
    public void testMaternalAuntMotherSister(){
        String relation = family.getShortestRelation(family.getMember("Savya"),family.getMember("Anvi"));
        Assert.assertEquals("MATERNAL AUNT", relation);
    }

    @Test
    public void testPaternalAuntFatherSister(){
        String relation = family.getShortestRelation(family.getMember("Vila"),family.getMember("Satya"));
        Assert.assertEquals("PATERNAL AUNT", relation);
    }

    @Test
    public void testPaternalAuntFatherSisterInLaw(){
        String relation = family.getShortestRelation(family.getMember("Vrita"),family.getMember("Lika"));
        Assert.assertEquals("PATERNAL AUNT", relation);
    }

    @Test
    public void testMaternalUncleMotherBrother(){
        String relation = family.getShortestRelation(family.getMember("Saayan"),family.getMember("Ish"));
        Assert.assertEquals("MATERNAL UNCLE", relation);
    }

    @Test
    public void testMaternalUncleMotherBrotherInLaw(){
        String relation = family.getShortestRelation(family.getMember("Satvy"),family.getMember("Ansh"));
        Assert.assertEquals("MATERNAL UNCLE", relation);
    }

    @Test
    public void testPaternalUncleFatherBrotherInLaw(){
        String relation = family.getShortestRelation(family.getMember("Drita"),family.getMember("Vyan"));
        Assert.assertEquals("PATERNAL UNCLE", relation);
    }

    @Test
    public void testPaternalUncleFatherBrother(){
        String relation = family.getShortestRelation(family.getMember("Misa"),family.getMember("Savya"));
        Assert.assertEquals("PATERNAL UNCLE", relation);
    }


    @Test
    public void testExtendedRelationOne(){
        String relation = family.getShortestRelation(family.getMember("Jata"),family.getMember("Lika"));
        Assert.assertEquals("GRANDFATHER'S SISTER-IN-LAW", relation);
    }

    @Test
    public void testExtendedRelationTwo(){
        String relation = family.getShortestRelation(family.getMember("Driya"),family.getMember("Mina"));
        Assert.assertEquals("GRANDFATHER'S SISTER'S SON'S WIFE", relation);
    }

    @Test
    public void testExtendedRelationThree(){
        String relation = family.getShortestRelation(family.getMember("Jnki"),family.getMember("Misa"));
        Assert.assertEquals("HUSBAND'S PATERNAL AUNT'S GRANDSON", relation);
    }

    @Test
    public void testExtendedRelationFour(){
        String relation = family.getShortestRelation(family.getMember("Mnu"),family.getMember("Gru"));
        Assert.assertEquals("WIFE'S GRANDFATHER'S BROTHER'S GRANDDAUGHTER'S HUSBAND", relation);
    }

    @Test
    public void testExtendedRelationFive(){
        String relation = family.getShortestRelation(family.getMember("Ansh"),family.getMember("Kriya"));
        Assert.assertEquals("SISTER-IN-LAW'S GRANDSON", relation);
    }
}
