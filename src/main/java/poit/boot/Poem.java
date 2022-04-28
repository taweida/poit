package poit.boot;

public class Poem {

    protected String originalText;
    protected String poemText;

    public Poem(String text){
        this.originalText = text;
    }

    public String getOriginalText(){
        return originalText;
    }

    public String getPoemText(){
        return poemText;
    }

    public void setPoemText(String text){
        this.poemText = text;
    }

}

class Haiku extends Poem{

    public Haiku(String text){
        super(text);
    }

}

class ShakespearePoem extends Poem{

    public ShakespearePoem(String text){
        super(text);
    }

}

class Limerick extends Poem{

    public Limerick(String text){
        super(text);
    }

}
