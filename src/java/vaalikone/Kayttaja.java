/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonne
 */
public class Kayttaja implements Serializable {

    private final List<Integer> vastaus = new ArrayList<>(20);
    List<Tuple<Integer, Integer>> pisteet = new ArrayList<>(20);
    private final static Logger logger = Logger.getLogger(Loki.class.getName());

    /**
     * Kayttaja-olioon tallennetaan vaalikoneen käyttäjän tietoja.
     */
    public Kayttaja() {

        //täytelläänhän listat valmiiksi
        for (int i = 0; i < 20; i++) {
            this.vastaus.add(0);
            this.pisteet.add(new Tuple<>(0, 0));
        }

    }

    /**
     *
     * @return Integer-lista käyttäjän vastauksista
     */
    public List<Integer> getVastausLista() {
        return this.vastaus;
    }

    /**
     * Hae pisteet-listasta yksittäiset pisteet
     *
     * @param ehdokasId ehdokkaan id-numero
     * @return pisteet ehdokkaaseen nähden
     */
    public Integer getPisteet(int ehdokasId) {
        if (this.pisteet.size() >= ehdokasId) {
            return this.pisteet.get(ehdokasId).pisteet;
        } else {
            return 0;
        }
    }

    /**
     * Aseta pisteet tiettyyn ehdokkaaseen nähden
     *
     * @param ehdokasId ehdokkaan id-numero
     * @param pisteet Arvo, mikä lisätään
     */
    public void addPisteet(Integer ehdokasId, Integer pisteet) {
        this.pisteet.set(ehdokasId, new Tuple<>(ehdokasId, pisteet));
    }

    /**
     * Hae yksittäinen käyttäjän vastaus kysymykseen
     *
     * @param index kysymyksen numero
     * @return Yksittäinen integer-muotoinen vastaus käyttäjän vastaus-listasta
     */
    public Integer getVastaus(int index) {
        return this.vastaus.get(index);
    }

    /**
     * Lisää vastaus
     *
     * @param index kysymyksen numero
     * @param vastaus vastauksen arvo
     */
    public void addVastaus(Integer index, Integer vastaus) {
        this.vastaus.set(index, vastaus);
    }

    /**
     * Hae parhaat ehdokkaat pistemäärän mukaan järjesteltynä
     *
     * @return Tuple-lista, (ehdokkaan id, pisteet)
     */
    public List<Tuple<Integer, Integer>> haeParhaatEhdokkaat() {

        /* Järjestä pisteet sisältävä Tuple.
         *  Javan Collections.sort oletuksena järjestää listat pienimmästä suurimpaan
         *  Collections.reverseOrder kääntää järjestyksen toisin päin
         */
        Collections.sort(this.pisteet, Collections.reverseOrder(comparator));
        
//        this.pisteet.stream().forEach((tpl) -> {
//            logger.log(Level.INFO, "Ehdokas ID={0} pisteet={1}", new Object[]{tpl.ehdokasId, tpl.pisteet});
//        });

        return this.pisteet;
    }

    //Tuplen järjestämiseen tarvittavan comparatorin muodostaminen
    //lähde: http://stackoverflow.com/questions/5690537/sorting-a-tuple-based-on-one-of-the-fields
    //Comparator<Tuple<Integer, Integer>> comparator = (Tuple<Integer, Integer> o1, Tuple<Integer, Integer> o2) -> o1.pisteet.compareTo(o2.pisteet);
    Comparator<Tuple<Integer, Integer>> comparator = new Comparator<Tuple<Integer, Integer>>() {
        @Override
        public int compare(Tuple<Integer, Integer> o1, Tuple<Integer, Integer> o2) {
            return o1.pisteet.compareTo(o2.pisteet);
        }
    };

}
