package starklabs.sivodim.Drama.Presenter;

import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.View.HomeInterface;
import starklabs.sivodim.Drama.View.ListChapterInterface;
import starklabs.sivodim.Drama.View.NewScreenplayInterface;

/**
 * Created by io on 25/05/2016.
 */
public class ScreenplayPresenterImpl implements ScreenplayPresenter {
    NewScreenplayInterface newScreenplayInterface;
    Screenplay screenplay;
    // to share and export algorithms
    ListChapterInterface listChapterInterface;
    // to keep track of the last screenplay when on home (after back operation)
    HomeInterface homeInterface;

    @Override
    public void export() {

    }

    @Override
    public void share() {

    }

    @Override
    public void newScreenplay() {

    }

    @Override
    public void orderChapter() {

    }
}
