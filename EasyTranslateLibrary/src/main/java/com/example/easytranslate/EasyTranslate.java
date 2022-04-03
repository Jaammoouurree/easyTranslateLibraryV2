package com.example.easytranslate;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.io.IOException;
import java.io.InputStream;

public class EasyTranslate {


    String toBeTranslated;
    String fromLanguange;
    String toLanguage;
    String translatedText;
    boolean connected;
    Context mcontext;
    Translate translate;

    public EasyTranslate(Context context){
        this.mcontext = context;
    }




    public void getTranslateService() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try (InputStream is = mcontext.getResources().openRawResource(R.raw.easy_translate_credentials)) {

            //Get credentials:
            final GoogleCredentials myCredentials = GoogleCredentials.fromStream(is);

            //Set credentials and get translate service:
            TranslateOptions translateOptions = TranslateOptions.newBuilder().setCredentials(myCredentials).build();
            translate = translateOptions.getService();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }

    public  String translate(String toBeTranslated, String fromLanguange, String toLanguage) {
        this.fromLanguange = fromLanguange;
        this.toBeTranslated = toBeTranslated;
        this.toLanguage = toLanguage;

        //Get input text to be translated:


        if (checkInternetConnection()) {

            //If there is internet connection, get translate service and start translation:
            getTranslateService();
            Translation translation = translate.translate(toBeTranslated, Translate.TranslateOption.sourceLanguage(fromLanguange), Translate.TranslateOption.targetLanguage(toLanguage), Translate.TranslateOption.model("base"));
            translatedText = translation.getTranslatedText();

            //Translated text and original text are set to TextViews:
            return translatedText;


        } else {

            //If not, display "no connection" warning:
            return "No Internet";
        }

    }





    public boolean checkInternetConnection() {

        //Check internet connection:
        ConnectivityManager connectivityManager = (ConnectivityManager)mcontext.getSystemService(Context.CONNECTIVITY_SERVICE);

        //Means that we are connected to a network (mobile or wi-fi)
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        return connected;
    }
}