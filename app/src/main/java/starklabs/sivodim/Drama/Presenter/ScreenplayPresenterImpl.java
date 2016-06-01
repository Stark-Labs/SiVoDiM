package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.Iterator;
import java.util.Vector;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.View.HomeInterface;
import starklabs.sivodim.Drama.View.ListChapterActivity;
import starklabs.sivodim.Drama.View.ListChapterInterface;
import starklabs.sivodim.Drama.View.NewScreenplayInterface;
import starklabs.sivodim.R;

/**
 * Created by io on 25/05/2016.
 */
public class ScreenplayPresenterImpl implements ScreenplayPresenter {
    NewScreenplayInterface newScreenplayInterface;
    Screenplay screenplay;
    // to share and export algorithms
    ListChapterInterface listChapterInterface;
    // to keep track of the last screenplay when on home (after back operation)
    //HomeInterface homeInterface;
    ArrayAdapter<String> titlesAdapter;


    public ScreenplayPresenterImpl(NewScreenplayInterface newScreenplayInterface){
        this.newScreenplayInterface=newScreenplayInterface;
    }

    public ScreenplayPresenterImpl(ListChapterInterface listChapterActivity){
        this.listChapterInterface=listChapterActivity;
    }

    @Override
    public void export() {

    }

    @Override
    public void share() {

    }

    @Override
    public void newScreenplay(String title) {
        this.screenplay=new ScreenplayImpl(title);
        save();
    }

    @Override
    public void addCharacter(Character character){
        screenplay.addCharacter(character);
    }

    @Override
    public void importCharacter(String screenplay,Context context){
        this.screenplay.importCharacters(ScreenplayImpl.loadScreenplay(screenplay,context));
    }

    @Override
    public void orderChapter() {

    }

    private String[] loadChapterTitles(String screenplay){
        /*if(this.screenplay==null){
            this.screenplay=ScreenplayImpl.loadScreenplay(screenplay);}
        Iterator<Chapter>chapterIterator=this.screenplay.getChapterIterator();
        Vector<String> result=new Vector<>();
        while (chapterIterator.hasNext()){
            Chapter chapter=chapterIterator.next();
            result.add(chapter.getTitle());
        }
        return (String[]) result.toArray();*/
        return new String[]{"capitolo1","capitolo2","capitolo3"};
    }

    @Override
    public ArrayAdapter<String> getTitlesAdapter(Context context,String screenplay){
        if(titlesAdapter==null)
            titlesAdapter=new ArrayAdapter<String>(context, R.layout.screenplay_item,loadChapterTitles(screenplay));
        return titlesAdapter;
    }

    @Override
    public boolean save(){

        return false;
    }
}
