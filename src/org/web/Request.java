package org.web;

import org.filemanagment.FileManagment;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Sphiinx on 3/16/17.
 */
public class Request {

    /**
     * Sends a post request to the specified url with the hash map.
     * Converts the data from the hash map to a byte[] before sending it.
     *
     * @param url_name The url to send the post request to.
     * @param hash_map The hash map containing the parameters.
     * @return True if the data was sent successfully; false otherwise.
     */
    public static boolean sendPostRequest(String url_name, Map<String, Object> hash_map) {
        try {
            final StringBuilder POST_DATA = new StringBuilder();
            for (Map.Entry<String, Object> param : hash_map.entrySet()) {
                if (POST_DATA.length() != 0)
                    POST_DATA.append('&');

                POST_DATA.append('&');
                POST_DATA.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                POST_DATA.append('=');
                POST_DATA.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }

            final byte[] POST_DATA_BYTES = POST_DATA.toString().getBytes("UTF-8");

            final URL URL = new URL(url_name);
            final HttpURLConnection CONNECTION = (HttpURLConnection) URL.openConnection();
            CONNECTION.setRequestMethod("POST");
            CONNECTION.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            CONNECTION.setRequestProperty("Content-Length", String.valueOf(POST_DATA_BYTES.length));
            CONNECTION.setDoOutput(true);
            CONNECTION.getOutputStream().write(POST_DATA_BYTES);
            final Reader READER = new BufferedReader(new InputStreamReader(CONNECTION.getInputStream(), "UTF-8"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Requests the file from the specified url and saves it to the specified path.
     * Compares the downloaded file size to the url file size to ensure it's fully downloaded.
     *
     * @param url  The url in which to download the file.
     * @param path The path in which to save the file.
     * @param save_name The name of the file. (Make sure to include a seperator between the path and the same name.)
     * @return True if the file was successfully downloaded, false otherwise.
     */
    public static boolean requestFile(String url, String path, String save_name) {
        try {
            final URL URL = new URL(url);
            final HttpURLConnection CONNECTION = (HttpURLConnection) URL.openConnection();
            final int RESPONSE = CONNECTION.getResponseCode();
            if (RESPONSE != HttpURLConnection.HTTP_OK)
                return false;

            final InputStream INPUT_STREAM = CONNECTION.getInputStream();
            final FileOutputStream OUTPUT_STREAM = new FileOutputStream(path + save_name);

            int bytes_read;
            final byte[] BUFFER = new byte[4096];
            while ((bytes_read = INPUT_STREAM.read(BUFFER)) != -1)
                OUTPUT_STREAM.write(BUFFER, 0, bytes_read);

            OUTPUT_STREAM.close();
            INPUT_STREAM.close();
            CONNECTION.disconnect();

            final int FILE_SIZE = requestFileSize(url);
            final File DOWNLOADED_FILE = FileManagment.getFileInDirectory(path, save_name);
            if (DOWNLOADED_FILE == null)
                return false;

            return FILE_SIZE == DOWNLOADED_FILE.length();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Requests the size of the file from the specified url.
     *
     * @param url The url to request file size.
     * @return The size of the file in bytes.
     * */
    public static int requestFileSize(String url) {
        try {
            final URL URL = new URL(url);
            final HttpURLConnection CONNECTION = (HttpURLConnection) URL.openConnection();
            final int RESPONSE = CONNECTION.getResponseCode();
            if (RESPONSE != HttpURLConnection.HTTP_OK)
                return -1;

            return CONNECTION.getContentLength();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

}

