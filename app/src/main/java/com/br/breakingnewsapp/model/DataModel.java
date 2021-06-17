package com.br.breakingnewsapp.model;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){}
    public static DataModel getInstance(){return instance;}
    private Context context;
    //private FeedRss feedRss;

    public void setContext(Context context){
        this.context = context;
    }
    public ArrayList<Noticia> getNoticias(){
        //final InputStream[] inputStream = new InputStream[1];
        ArrayList<Noticia> lista = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.GET, "https://g1.globo.com/rss/g1/brasil/",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        /*try {
                            inputStream[0] = org.apache.commons.io.IOUtils.toInputStream(response, "UTF-8");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                        Log.d("Data",response+"");
                        try {
                            DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                            Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(response.getBytes()));
                            for(int i=0;i<parse.getElementsByTagName("item").getLength();i++){
                                String title = parse.getElementsByTagName("title").item(i).getTextContent();
                                String link = parse.getElementsByTagName("link").item(i).getTextContent();
                                String description = parse.getElementsByTagName("description").item(i).getTextContent();
                                Noticia noticiaTemp = new Noticia(title,link,description);
                                lista.add(noticiaTemp);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("requestErro",error.getMessage());
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        /*try {
            lista = feedRss.parse(inputStream[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }*/
        return lista;
    }
}
