package Properties;

import javafx.beans.binding.ObjectBinding;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class CurrentSongProperty extends ObjectBinding<Song> {

    @Override
    protected Song computeValue() {
        Runtime rt = Runtime.getRuntime();
        URL res = getClass().getClassLoader().getResource("CurrentPlaying");
        try {
            assert res != null;
            File f = Paths.get(res.toURI()).toFile();
        String[] commands = {f.getAbsolutePath()};
        try {
            Process proc = rt.exec(commands);

            BufferedReader stdInut = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s = stdInut.readLine();
            JSONObject j = new JSONObject(s);
            return new Song(j.getString("Title"),j.getString("Album"), j.getString("Artist"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;

    }
}
