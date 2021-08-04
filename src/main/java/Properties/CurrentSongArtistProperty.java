package Properties;

import javafx.beans.binding.StringBinding;

public class CurrentSongArtistProperty extends StringBinding {
    @Override
    protected String computeValue() {
        return PropertyWrapper.getC_song_prpty().get().artist();
    }
}
