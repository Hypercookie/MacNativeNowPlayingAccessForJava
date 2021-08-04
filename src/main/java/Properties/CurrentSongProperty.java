package Properties;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleObjectProperty;
import nativeLinkers.Song;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CurrentSongProperty extends ObjectBinding<Song> {

    @Override
    protected Song computeValue() {
        Runtime rt = Runtime.getRuntime();
        String[] commands = {"/Users/jannes/IdeaProjects/SkipMe/src/main/java/CurrentPlaying"};
        try {
            Process proc = rt.exec(commands);

            BufferedReader stdInut = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s = stdInut.readLine();
            JSONObject j = new JSONObject(s);
            return new Song(j.getString("Title"),j.getString("Album"), j.getString("Artist"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
