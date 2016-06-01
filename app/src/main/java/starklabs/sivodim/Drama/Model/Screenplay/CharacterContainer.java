package starklabs.sivodim.Drama.Model.Screenplay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Character.Character;

/**
 * Created by io on 25/05/2016.
 */
public class CharacterContainer {
    ArrayList<Character> characters=new ArrayList<>();

    public void loadCharacters(){

    }

    public ListIterator<Character> iterator(){
        return characters.listIterator();
    }

    public void addCharacter(Character character){
        characters.add(character);
    }

    public void removeCharacter(Character character){
        characters.remove(character);
    }
}
