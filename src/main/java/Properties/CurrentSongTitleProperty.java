package Properties;

import javafx.beans.binding.StringBinding;

public class CurrentSongTitleProperty extends StringBinding {
    @Override
    protected String computeValue() {
        return PropertyWrapper.getC_song_prpty().get().name();
    }
}
