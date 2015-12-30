package com.mobileanwendungen.cityhunt.ServerConnection;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
/**
 * Created by benni9998 on 11.11.2015.
 */
public class phpAccess extends AsyncTask<String,Void,Void> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... y) {
        String x = y[0].toString();
        try {
            GetText(x);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    public  void  GetText(String string)  throws UnsupportedEncodingException
    {

        Log.d("1", string);
        // Create data variable for sent values to server

        String data = URLEncoder.encode("name", "UTF-8")
                + "=" + URLEncoder.encode(string, "UTF-8");

        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL("urltophpfile");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }

            text = sb.toString();
            Log.d("1", "gesendet");
        }
        catch(Exception ex)
        {
            Log.d("First Exception", ex.toString());
        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {
                Log.d("Second Exception", ex.toString());}
        }

        // Show response on activity
        if (text != "") {
            Log.d("3", text);
        }
    }
}
