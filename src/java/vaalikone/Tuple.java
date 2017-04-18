/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

/**
 *
 * @author Jonne
 * @param <E>
 * @param <F>
 */
public class Tuple<E,F> {

    /**
     *
     */
    public E ehdokasId;

    /**
     *
     */
    public F pisteet;

    /**
     *
     * @param e
     * @param f
     */
    public Tuple(E e, F f) {
        this.ehdokasId=e;
        this.pisteet=f;
    }
    
}
