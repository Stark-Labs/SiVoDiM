package starklabs.sivodim.Drama.Model.Utilities;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;

/**
 * Created by Riccardo Rizzo on 31/05/2016.
 */

public class XMLParser {
    // debugging
    static void vDebug(String debugString) {
        Log.v("DomParsing", debugString+"\n");
    }
    // debugging
    static void eDebug(String debugString) {
        Log.e("DomParsing", debugString+"\n");
    }

    // data structure containing parsed data
    Screenplay parsedData;

    // return data structure with parsed screenplays
    public Screenplay getParsedData() { return parsedData; }

    public void parseXml(File file) {
        Document doc;
        try {
            // instance of document from a file
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            // get the root element
            Element root = doc.getDocumentElement();

            // new screenplay object to store parsed data
            String title = root.getAttribute("title");
            // new screenplay object to store parsed data
            parsedData = new ScreenplayImpl(title);

            // debug
            vDebug("Root element :" + root.getNodeName());
            vDebug("");

            // get the child elements from root
            NodeList nodes = root.getChildNodes();

            // cycle through every node
            for (int i = 0; i < nodes.getLength(); i++) {
                Node c = nodes.item(i);
                // check if the node is a tag
                if (c.getNodeType() == Node.ELEMENT_NODE) {
                    // from Node to Element "cast"
                    Element screenplayElem = (Element) c;

                    vDebug("_Attributo note id:" + title);
                    vDebug("");

                    // tag cases
                    if (screenplayElem.equals("characters")) {
                        NodeList charactersList = ((Element) c).getElementsByTagName("character");
                        for (int k = 0; k < charactersList.getLength(); k++) {
                            Node c1 = charactersList.item(k);
                            Element characterElem = (Element) c1; // cast
                            String name = characterElem.getAttribute("name");
                            String avatarPath = characterElem.getAttribute("avatar");
                            String voice = characterElem.getAttribute("voice");

                            Avatar avatar = new Avatar(avatarPath);

                            Character character = new CharacterImpl.CharacterBuilder()
                                    .setName(name)
                                    .setAvatar(avatar)
                                    .setVoice(voice)
                                    .build();

                            parsedData.addCharacter(character);
                        }
                    }
                    if (screenplayElem.equals("chapters")) {
                        NodeList chaptersList = ((Element) c).getElementsByTagName("chapter");
                        for (int k = 0; k < chaptersList.getLength(); k++) {
                            Node c1 = chaptersList.item(k);
                            Element chapterElem = (Element) c1; // cast
                            String chapterTitle = chapterElem.getAttribute("title");
                            String backgroundPath = chapterElem.getAttribute("background");
                            String soundtrackPath = chapterElem.getAttribute("soundtrack");
                            Background background = new Background(backgroundPath);
                            Soundtrack soundtrack = new Soundtrack(soundtrackPath);

                            Chapter chapter = new ChapterImpl.ChapterBuilder()
                                    .setTitle(chapterTitle)
                                    .setBackground(background)
                                    .setSoundtrack(soundtrack)
                                    .build();

                            NodeList speechesList = ((Element) c1).getElementsByTagName("speeches");
                            // tag cased
                            for(int z=0; k < speechesList.getLength(); z++) {
                                Node c2 = speechesList.item(z);
                                Element speechElem = (Element) c2; // cast
                                String text = speechElem.getTextContent();
                                String characterName = speechElem.getAttribute("character");
                                String emotion = speechElem.getAttribute("emotion");
                                String soundFxPath = speechElem.getAttribute("soundFx");
                                SoundFx soundFx = new SoundFx(soundFxPath);

                                Character character = parsedData.getCharacterByName(characterName);

                                Speech speech = new SpeechImpl.SpeechBuilder()
                                        .setText(text)
                                        .setEmotion(emotion)
                                        .setSoundFX(soundFx)
                                        .build();

                                if(character!=null) speech.setCharacter(character);

                                chapter.addSpeech(speech);
                            }

                            parsedData.addChapter(chapter);
                        }
                    }
                }
            }
        } // handle exceptions
        catch (SAXException e) {
            eDebug(e.toString());
        } catch (IOException e) {
            eDebug(e.toString());
        } catch (ParserConfigurationException e) {
            eDebug(e.toString());
        } catch (FactoryConfigurationError e) {
            eDebug(e.toString());
        }
    }
}
