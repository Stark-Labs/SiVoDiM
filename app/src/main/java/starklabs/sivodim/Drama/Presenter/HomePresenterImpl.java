package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.io.File;
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
    public String[] loadScreenplayTitles(Context context){
        File dir = context.getFilesDir();
        File[] directoryListing = dir.listFiles();
        String[] screenplayTitles = null;
        if (directoryListing != null) {
            for (int i=0; i < directoryListing.length; ++i) {
                screenplayTitles[i] = directoryListing[i].getName();
            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
        return screenplayTitles;
    }

    @Override
    public ArrayAdapter<String> getTitlesAdapter(Context context){
        if(titlesAdapter==null){
            titlesAdapter=new ArrayAdapter<String>(context, R.layout.screenplay_item,loadScreenplayTitles(context));
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
