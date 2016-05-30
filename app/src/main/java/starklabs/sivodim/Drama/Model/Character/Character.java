package starklabs.sivodim.Drama.Model.Character;

import starklabs.sivodim.Drama.Model.Utilities.Avatar;

/**
 * Created by io on 25/05/2016.
 */
public interface Character {
    void changeAvatar(Avatar avatar);
    void changeVoice(String voiceID);
    void changeName(String name);
    Avatar getAvatar();
    String getVoiceID();
    String getName();
}
