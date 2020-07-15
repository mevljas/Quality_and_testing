/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seba
 */
public interface Seznam<Tip> {

    // Dodajanje elementa v podatkovno strukturo
    void add(Tip e);

    //Odstranjevanje (in vračanje) prvega elementa iz pod. struk.
    Tip removeFirst();

    // Vračanje prvega elementa iz pod. struk.
    Tip getFirst();

    // Število elementov v podatkovni strukturi
    int size();

    // Globina podatkovne strukture
    int depth();

    // Ali je podakovna struktura prazna
    boolean isEmpty();
}
