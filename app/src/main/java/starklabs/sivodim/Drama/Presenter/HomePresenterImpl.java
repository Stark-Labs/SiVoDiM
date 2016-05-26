package starklabs.sivodim.Drama.Presenter;

import java.util.Vector;

import starklabs.sivodim.Drama.View.HomeInterface;

/**
 * Created by io on 25/05/2016.
 */
public class HomePresenterImpl implements HomePresenter {
    HomeInterface homeInterface;
    ScreenplayPresenter screenplayPresenter;

    @Override
    public Vector<String> getScreenplayNames() {
        return null;
    }

    @Override
    public void createScreenplayList() {

    }
}
