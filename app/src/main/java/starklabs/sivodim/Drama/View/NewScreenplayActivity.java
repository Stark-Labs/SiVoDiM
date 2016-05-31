package starklabs.sivodim.Drama.View;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.R;

public class NewScreenplayActivity extends AppCompatActivity implements NewScreenplayInterface, View.OnClickListener {
    private Button buttonCreateProject;
    private EditText editTextProjectName;
    private Spinner spinnerImportCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_screenplay);

        getSupportActionBar().setTitle("Creazione progetto");

        // GUI items initialization
        this.buttonCreateProject = (Button) findViewById(R.id.buttonCreateProject);
        this.editTextProjectName = (EditText) findViewById(R.id.editTextProjectName);
        this.spinnerImportCharacters = (Spinner) findViewById(R.id.spinnerImportCharacters);

        // onClick for Button
        this.buttonCreateProject.setOnClickListener(NewScreenplayActivity.this);

    }

    public void onClick(View v) {
        String title = editTextProjectName.getText().toString();
        Screenplay screenplay = new ScreenplayImpl(title);
    }
}
