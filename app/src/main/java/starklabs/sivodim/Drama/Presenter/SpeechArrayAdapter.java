package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.R;

/**
 * Created by Francesco Bizzaro on 31/05/2016.
 */
public class SpeechArrayAdapter extends ArrayAdapter {
    /**
     * The list of speeches to show
     */
    private List<Speech> speechList = new ArrayList<Speech>();

    /**
     * The TextView that will show the text of the speech
     */
    private TextView speechText;

    /**
     * The ImageView that will show the avatar of the character who says the speech
     */
    private ImageView speechAvatar;

    /**
     * The TextView that will show the name of the character who says the speech
     */
    private TextView characterName;


    /**
     * Main constructor that initialize the list and the layout
     * @param context
     * @param resource The id of layout resource utilized
     */
    public SpeechArrayAdapter(Context context, int resource) {
        super(context, resource);
    }


    /**
     * To add a new Speech in the screen
     * @param object
     */
    public void add(Speech object) {
        speechList.add(object);
        super.add(object);
    }

    /**
     * To get the current number of speeches in the list
     * @return
     */
    public int getCount() {
        return this.speechList.size();
    }

    /**
     * To obtain a reference to the Speech in a specified position
     * @param index The position inside the list
     * @return
     */
    public Speech getItem(int index) {
        return this.speechList.get(index);
    }


    /**
     * Create the view filling informations in the related fields. This method is automatically called
     * during the graphics construction
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Speech speechObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.speech_layout, parent, false);
        speechText = (TextView) row.findViewById(R.id.speechText);
        speechAvatar=(ImageView)row.findViewById(R.id.speechAvatar);
        characterName=(TextView)row.findViewById(R.id.characterName);
        Drawable drawable;
        int color=Color.BLACK;
        switch (speechObj.getEmotion()){
            case "FEAR":
                drawable=speechText.getResources().getDrawable(R.drawable.fear);
                color=Color.WHITE;
                break;
            case "HAPPINESS":
                drawable=speechText.getResources().getDrawable(R.drawable.happiness);
                break;
            case "ANGER":
                drawable=speechText.getResources().getDrawable(R.drawable.anger);
                break;
            case "SADNESS":
                drawable=speechText.getResources().getDrawable(R.drawable.sadness);
                color=Color.WHITE;
                break;
            case "SURPRISE":
                drawable=speechText.getResources().getDrawable(R.drawable.surprise);
                break;
            case "DISGUST":
                drawable=speechText.getResources().getDrawable(R.drawable.disgust);
                break;
            default:
                drawable=speechText.getResources().getDrawable(R.drawable.sadness);
                color=Color.WHITE;
                break;
        }
        speechText.setTextColor(color);
        speechText.setBackgroundDrawable(drawable);
        speechText.setText(speechObj.getText());
        Character character=speechObj.getCharacter();
        if(character!=null){
            characterName.setText(character.getName());
            Avatar avatar=character.getAvatar();
            if(avatar!=null && avatar.getImage()!=null)
                speechAvatar.setImageBitmap(avatar.getImage());
            else
                speechAvatar.setImageResource(R.mipmap.undefined_user);
        }
        return row;
    }
}
