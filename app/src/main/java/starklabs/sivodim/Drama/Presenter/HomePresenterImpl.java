package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.Vector;

import starklabs.sivodim.Drama.View.HomeInterface;
import starklabs.sivodim.R;

/**
 * Created by io on 25/05/2016.
 */
public class HomePresenterImpl implements HomePresenter {
    HomeInterface homeInterface;
    ScreenplayPresenter screenplayPresenter;
    // content of the view
    ArrayAdapter<String> titlesAdapter;

    public HomePresenterImpl(HomeInterface homeInterface){
        this.homeInterface=homeInterface;
    }

    @Override
    public String[] loadScreenplayTitles(){
        return new String[]{"Star Wars","Lord of The Rings","Inside Out","Toy Story","District 9"};
    }

    @Override
    public ArrayAdapter<String> getTitlesAdapter(Context context){
        if(titlesAdapter==null){
            titlesAdapter=new ArrayAdapter<String>(context, R.layout.screenplay_item,loadScreenplayTitles());
        }
        return titlesAdapter;
    }

    @Override
    public Vector<String> getScreenplayNames() {
        return null;
    }

    @Override
    public void createScreenplayList() {

    }
}
