package starklabs.sivodim.Drama.Model.Screenplay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Character.Character;

/**
 * Created by io on 25/05/2016.
 */
public class ScreenplayImpl implements Screenplay {
    private String title;
    private CharacterContainer characterContainer;
    private ArrayList<Chapter> chapters=new ArrayList<>();
    private ExportAlgorithm exportAlgorithm;
    private ShareAlgorithm shareAlgorithm;

    public ScreenplayImpl(String title){
        this.title = title;
        characterContainer=new CharacterContainer();
    }

    public static Screenplay loadScreenplay(String name){

        return null;
    }

    @Override
    public Iterator<Chapter> getChapterIterator(){
        return chapters.iterator();
    }

    @Override
    public void export() {

    }

    @Override
    public void share() {

    }

    @Override
    public CharacterContainer getCharacters(){
        return characterContainer;
    }

    @Override
    public void importCharacters(Screenplay screenplay){
        CharacterContainer characters=screenplay.getCharacters();
        this.characterContainer=characters.clone();
    }

    @Override
    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
    }

    @Override
    public void addCharacter(Character character) {
        characterContainer.addCharacter(character);
    }

    @Override
    public void removeCharacter(Character character){
        characterContainer.removeCharacter(character);
    }
}
