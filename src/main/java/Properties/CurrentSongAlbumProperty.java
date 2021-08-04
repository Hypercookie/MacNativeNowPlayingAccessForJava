package Properties;

import javafx.beans.binding.StringBinding;

public class CurrentSongAlbumProperty extends StringBinding {
    @Override
    protected String computeValue() {
        return PropertyWrapper.getC_song_prpty().get().album();
    }
}
