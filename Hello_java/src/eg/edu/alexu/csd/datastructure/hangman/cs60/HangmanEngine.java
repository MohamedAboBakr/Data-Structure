package eg.edu.alexu.csd.datastructure.hangman.cs60;

import eg.edu.alexu.csd.datastructure.hangman.IHangman;

import java.util.Random;
public class HangmanEngine implements IHangman{

    
    static String[] words ;
    static  int MaxWrongGuesses = 0;
    static String random_string ;
    int len ;
   static char[] letters , discover;
    @Override
    public void setDictionary(String[] word) {
    	   
    	   words = new String[word.length];
           words = word ;
    }

    @Override
    public String selectRandomSecretWord() {
    	if(words == null || words.length == 0) {
    		random_string = null ;
    		return random_string ;
    	}
        Random random = new Random();
        int index = random.nextInt(words.length);
        random_string = words[index];
        if(random_string.equals(" ") || random_string == null ){
        	random_string = null ;
        	return null;
        }
        len = random_string.length() ;
        letters = random_string.toCharArray();
        discover = new char[len];
        for(int i=0 ; i< len ; i++){
            discover[i] = '-';
        }
        return random_string;
    }

    @Override
    public String guess(Character c) {
    	if(random_string == null ) return null ;
    	if(random_string.equals("")) return null;
        if(c == null){
        	MaxWrongGuesses -= 1;
        	return null;
        }
        String return_string ;
        if(MaxWrongGuesses == 0) return null ;
        int flag = 0 ;
        for(int i=0 ;  i < len ; i++){
            if(Character.toLowerCase(letters[i]) == Character.toLowerCase(c.charValue())){
                flag = 1;
                letters[i] = '-';
                discover[i] = Character.toUpperCase(c);
            }
        }
        
        if(flag == 0) MaxWrongGuesses -= 1;
        return_string = new String(discover);
        return  return_string;
    }

    @Override
    public void setMaxWrongGuesses(Integer max) {
           MaxWrongGuesses = max ;
    }
    
}