package starklabs.sivodim.Drama.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.Presenter.CharacterPresenter;
import starklabs.sivodim.R;

public class EditCharacterActivity extends AppCompatActivity implements EditCharacterInterface{
    private static CharacterPresenter characterPresenter;
    private ImageView editAvatar;
    private Spinner editVoice;
    private EditText editName;
    private Button apply;
    private Character character;

    public static void setPresenter(CharacterPresenter characterPresenter){
        EditCharacterActivity.characterPresenter=characterPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_character);

        characterPresenter.setActivity(this);

        getSupportActionBar().setTitle("Gestione personaggio");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editAvatar=(ImageView)findViewById(R.id.editAvatar);
        editVoice=(Spinner)findViewById(R.id.editVoice);
        editName=(EditText)findViewById(R.id.editName);
        apply=(Button)findViewById(R.id.applyChanges);
        character=characterPresenter.getCharacter();

        Avatar avatar=character.getAvatar();
        if(avatar!=null && avatar.getImage()!=null)
           editAvatar.setImageBitmap(avatar.getImage());

        editName.setText(character.getName());
        //add voices....

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editName.getText().toString();
                character.setName(name);
                //set voice and avatar..
                Intent intent=new Intent(v.getContext(),ListCharacterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(this,ListCharacterActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
