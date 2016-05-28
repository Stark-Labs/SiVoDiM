package starklabs.sivodim.Drama.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import starklabs.sivodim.R;

public class NewChapterActivity extends AppCompatActivity implements NewChapterInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chapter);

        getSupportActionBar().setTitle("Creazione capitolo");
    }
}
