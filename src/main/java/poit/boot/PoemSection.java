package poit.boot;

import java.util.ArrayList;

/*
    This is kind of a weird implementation of Composite, but I think it's about what we need?
 */

public abstract class PoemSection {

    protected ArrayList<PoemSection> components;
    protected String text;
    protected int numSyllables;

    /*
        Since we do the calculations at construction time, this is explicitly not reactive to changes in
        components after construction. This is to save time; we're assuming poems will be static (and don't
        plan to make any changes to this fact)
     */
    public PoemSection(ArrayList<PoemSection> components){
        this.components = components;
    }

    public PoemSection(){
        this.components = null;
        this.text = "";
        this.numSyllables = 0;
    }

    public int getNumSyllables(){
        int syllableCount = 0;
        this.text = "";
        for(PoemSection comp : components){
            syllableCount += comp.getNumSyllables();
        }
        return syllableCount;
    }

    public String getText(){
        String poemText = "";
        for(PoemSection comp : components){
            poemText = poemText + comp.getText();
        }
        return poemText;
    }

}

class PoemTree extends PoemSection {

    public PoemTree(ArrayList<PoemSection> components){
        super(components);
    }

    @Override
    public String getText(){

        String poemText = components.isEmpty() ? "" : components.get(0).getText();
        for(int i = 1; i < components.size(); i++){
            poemText = poemText + "\n\n" + components.get(i).getText();
        }
        return poemText;

    }

}

class Stanza extends PoemSection {

    public Stanza(ArrayList<PoemSection> lines){
        super(lines);
    }

    @Override
    public String getText(){

        String poemText = components.isEmpty() ? "" : components.get(0).getText();
        for(int i = 1; i < components.size(); i++){
            poemText = poemText + "\n" + components.get(i).getText();
        }
        return poemText;

    }

}

class Line extends PoemSection {
    
    public Line(ArrayList<PoemSection> words){
        super(words);
    }

    @Override
    public String getText(){

        String poemText = components.isEmpty() ? "" : components.get(0).getText();
        for(int i = 1; i < components.size(); i++){
            poemText = poemText + " " + components.get(i).getText();
        }
        return poemText;

    }

}

/*
    Syllable counting courtesy of user Tihamer at
    https://stackoverflow.com/questions/405161/detecting-syllables-in-a-word
*/
class Word extends PoemSection {

    public Word(String word){
        super();
        this.numSyllables = countSyllables(word);
        this.text = word;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public int getNumSyllables(){
        return this.numSyllables;
    }

    // TODO: exclude newline characters
    private int countSyllables(String word) {
        char[] vowels = { 'a', 'e', 'i', 'o', 'u', 'y' };
        char[] currentWord = word.toCharArray();
        int numVowels = 0;
        boolean lastWasVowel = false;
        for (char wc : currentWord) {
            boolean foundVowel = false;
            for (char v : vowels)
            {
                //don't count diphthongs
                if ((v == wc) && lastWasVowel)
                {
                    foundVowel = true;
                    lastWasVowel = true;
                    break;
                }
                else if (v == wc && !lastWasVowel)
                {
                    numVowels++;
                    foundVowel = true;
                    lastWasVowel = true;
                    break;
                }
            }
            // If full cycle and no vowel found, set lastWasVowel to false;
            if (!foundVowel)
                lastWasVowel = false;
        }
        // Remove es, it's _usually? silent
        if (word.length() > 2 &&
                word.substring(word.length() - 2) == "es")
            numVowels--;
            // remove silent e
        else if (word.length() > 1 &&
                word.substring(word.length() - 1) == "e")
            numVowels--;
        return numVowels;
    }

}