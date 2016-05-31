package starklabs.sivodim.Drama.Model.Utilities;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.CharacterContainer;
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
    ArrayList<Screenplay> parsedData=new ArrayList<Screenplay>();

    // return data structure with parsed screenplays
    public ArrayList<Screenplay> getParsedData() { return parsedData; }

    public void parseXml(File file) {
        Document doc;
        try {
            // instance of document from a file
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            // get the root element
            Element root = doc.getDocumentElement();

            vDebug("Root element :" + root.getNodeName());
            vDebug("");

            // get the child elements from root
            NodeList nodes = root.getChildNodes();

            // cycle through every node
            for(int i=0; i<nodes.getLength(); i++) {
                Node c = nodes.item(i);
                // check if the node is a tag
                if (c.getNodeType() == Node.ELEMENT_NODE) {
                    // from Node to Element "cast"
                    Element screenplayElem = (Element) c;

                    // new screenplay object to store parsed data
                    String title = screenplayElem.getAttribute("title");

                    vDebug("_Attributo note id:" + title);
                    vDebug("");

                    // new screenplay object to store parsed data
                    Screenplay screenplay = new ScreenplayImpl(title);

                    // param for screenplay
                    CharacterContainer characterContainer = null;

                    // get deeper in the hierarchy
                    NodeList screenplayDetails = c.getChildNodes();
                    for (int j = 0; j < screenplayDetails.getLength(); j++) {
                        Node c1 = screenplayDetails.item(j);
                        // check if the node is a tag
                        if (c1.getNodeType() == Node.ELEMENT_NODE) {
                            Element detail = (Element) c1; // cast
                            String nodeName = detail.getNodeName(); // read tag name
                            String nodeValue = detail.getFirstChild().getNodeValue(); // read tag content

                            vDebug("______Dettaglio:" + nodeName);
                            vDebug("______Contenuto Dettaglio:" + nodeValue);
                            vDebug("");

                            // tag cases
                            if (nodeName.equals("characters")) {
                                NodeList charactersList = ((Element) c1).getElementsByTagName("character");
                                for (int k = 0; k < charactersList.getLength(); k++) {
                                    Node c2 = charactersList.item(k);
                                    Element characterElem = (Element) c2; // cast
                                    String name = characterElem.getAttribute("name");
                                    String avatarPath = characterElem.getAttribute("avatar");
                                    String voice = characterElem.getAttribute("voice");

                                    Avatar avatar = new Avatar(avatarPath);

                                    Character character = new CharacterImpl.CharacterBuilder()
                                            .setName(name)
                                            .setAvatar(avatar)
                                            .setVoice(voice)
                                            .build();

                                    screenplay.addCharacter(character);
                                }
                            }
                            if (nodeName.equals("chapters")) {
                                NodeList chaptersList = c1.getChildNodes();
                                for (int k = 0; k < chaptersList.getLength(); k++) {
                                    Node c2 = chaptersList.item(k);
                                    if (c2.getNodeType() == Node.ELEMENT_NODE) {
                                        Element chapterElem = (Element) c2; // cast
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

                                        screenplay.addChapter(chapter);
                                    }
                                }
                            }
                            vDebug("");
                            parsedData.add(screenplay); // add object to array list
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
