package com.br.breakingnewsapp.model;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FeedRss {
    private static final String ns = null;
    public ArrayList<Noticia> parse(InputStream inputStream) throws IOException, XmlPullParserException {
        try {
            XmlPullParser parser  = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(inputStream, null);
            parser.nextTag();
            return readFeed(parser);
        }finally {
            inputStream.close();
        }
    }
    private Noticia readItem(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG,ns,"item");
        String title = null;
        String link = null;
        String description = null;
        String img = null;
        while (parser.next()!=XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("title")){
                title = readTitle(parser);
            }else if(name.equals("link")){
                link = readLink(parser);
            }else if(name.equals("description")){
                description = readDescription(parser);
            }else if(name.equals("media")){
                img = readImg(parser);
            }else {
                skip(parser);
            }
        }
        return new Noticia(title,link,description);
    }
    private ArrayList readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList itens = new ArrayList();
        parser.require(XmlPullParser.START_TAG, ns, "feed");
        while (parser.next() != XmlPullParser.END_TAG){
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if(name.equals("item")){
                itens.add(readItem(parser));
            }
            else {
                skip(parser);
            }
        }
        return itens;
    }
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result ="";
        if(parser.next() == XmlPullParser.TEXT){
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    private String readDescription(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns,"description");
        String description = readText(parser);
        parser.require(XmlPullParser.END_TAG,ns,"description");
        return description;
    }
    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns,"title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG,ns,"title");
        return title;
    }
    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns,"link");
        String link = readText(parser);
        parser.require(XmlPullParser.END_TAG,ns,"link");
        return link;
    }
    private String readImg(XmlPullParser parser) throws IOException, XmlPullParserException {
        String img = "";
        parser.require(XmlPullParser.START_TAG,ns,"media");
        img = parser.getAttributeValue(null,"url");
        return img;
    }
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth =1;
        while (depth != 0){
            switch (parser.next()){
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
