package com.hayrihabip.cryptonotifier;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class API {
    private static String APIUrl = "https://ifo2s91vcf.execute-api.us-east-1.amazonaws.com/dev/";

    public static ArrayList<Cryptos> GetBalance(String body) throws Exception {
        String response = "";

        URL url = new URL(APIUrl + "balance");

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(body);

        writer.flush();
        writer.close();
        os.close();

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK)
            response = getStringFromStream(conn.getInputStream());
        else
            throw new Exception(getStringFromStream(conn.getErrorStream()));

        ArrayList<Cryptos> responseArray = new ArrayList<>();
        if (response != "")
            responseArray = new Gson().fromJson(response, new TypeToken<ArrayList<Cryptos>>(){}.getType());

        return responseArray;
    }

    private static String getStringFromStream(InputStream in) {
        String line;
        String response = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while ((line = br.readLine()) != null)
                response += line;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
