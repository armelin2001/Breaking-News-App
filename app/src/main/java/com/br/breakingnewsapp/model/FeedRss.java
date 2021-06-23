package com.br.breakingnewsapp.model;

import android.content.Context;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FeedRss {

    public List<Noticia> loadData(String url, Context context, List<Noticia> list){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String reg = response.replaceAll("<img [\\s\\S]*? /><br />","");
                            //String lastReg = reg.replaceAll("<description>Últimas notícias [\\s\\S]*? país.</description>","");
                            DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                            Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(reg.getBytes()));
                            NodeList listaNode = parse.getElementsByTagName("title");
                            for(int j=0;j<listaNode.getLength();j++) {
                                String titleErro = listaNode.item(j).getTextContent();
                                Node nNode = listaNode.item(j);
                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement = (Element) nNode;
                                    if (titleErro.equals(("G1 > Brasil")) || titleErro.equals("G1 > Tecnologia e Games") || titleErro.equals("G1 > Economia")) {
                                        nNode.getParentNode().removeChild(nNode);
                                    }
                                }
                            }

                            for(int i=0;i<parse.getElementsByTagName("item").getLength();i++){
                                String title = parse.getElementsByTagName("title").item(i).getTextContent();
                                String description = parse.getElementsByTagName("description").item(i).getTextContent();
                                String img = parse.getElementsByTagName("media:content").item(i).getAttributes().item(0).getTextContent();
                                list.add(new Noticia(title, img,description));
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("erroResponse",error.getMessage());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return list;
    }
}
