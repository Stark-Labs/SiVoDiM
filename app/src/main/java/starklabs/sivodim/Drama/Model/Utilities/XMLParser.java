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
    private Screenplay parsedData;

    // return data structure with parsed screenplays
    public Screenplay getParsedData() { return parsedData; }

    private void parseCharacter(Node node){
        //NodeList charactersList = ((Element) c).getElementsByTagName("character");
        NodeList charactersList = node.getChildNodes();
        for (int k = 0; k < charactersList.getLength(); k++) {
            Node c1 = charactersList.item(k);
            if (c1.getNodeType() == Node.ELEMENT_NODE) {
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
    }

    private void parseChapter(Node node){
        //NodeList chaptersList = ((Element) c).getElementsByTagName("chapter");
        NodeList chaptersList = node.getChildNodes();
        for (int k = 0; k < chaptersList.getLength(); k++) {
            Node c1 = chaptersList.item(k);

            vDebug("Verifica lettura nodi interni a capitolo :" + c1.getNodeName());
            vDebug("");

            if (c1.getNodeType() == Node.ELEMENT_NODE) {
                Element chapterElem = (Element) c1; // cast
                String chapterTitle = chapterElem.getAttribute("title");
                vDebug("Titolo capitolo: "+chapterTitle);
                String backgroundPath = chapterElem.getAttribute("background");
                String soundtrackPath = chapterElem.getAttribute("soundtrack");
                Background background = new Background(backgroundPath);
                Soundtrack soundtrack = new Soundtrack(soundtrackPath);

                Chapter chapter = new ChapterImpl.ChapterBuilder()
                        .setTitle(chapterTitle)
                        .setBackground(background)
                        .setSoundtrack(soundtrack)
                        .build();

                NodeList speechesList = ((Element) c1).getElementsByTagName("speech");
                // tag cased
                for (int z = 0; z < speechesList.getLength(); z++) {
                    Node c2 = speechesList.item(z);
                    Element speechElem = (Element) c2; // cast
                    if(speechElem!=null) {
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

                        if (character != null) speech.setCharacter(character);

                        vDebug("SPEECH: "+speech.getText());
                        chapter.addSpeech(speech);
                    }
                }

                parsedData.addChapter(chapter);
            }
        }
    }

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
                vDebug("Verifica lettura nodi PRIMA if :" + c.getNodeName());
                vDebug("");
                if (c.getNodeType() == Node.ELEMENT_NODE) {
                    vDebug("Verifica lettura nodi DOPO if :" + c.getNodeName());
                    vDebug("");
                    // from Node to Element "cast"
                    Element screenplayElem = (Element) c;
                    String nodeName = screenplayElem.getNodeName();

                    // tag cases
                    if (nodeName.equals("characters")) {
                        parseCharacter(c);
                    }
                    if (nodeName.equals("chapters")) {
                        parseChapter(c);
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