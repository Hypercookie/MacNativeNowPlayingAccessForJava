package Properties;

import javafx.application.Platform;
import lombok.Generated;
import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class PropertyWrapper {

    private ScheduledExecutorService refreshService = Executors.newSingleThreadScheduledExecutor(r -> {
        var t = Executors.defaultThreadFactory().newThread(r);
        t.setDaemon(true);
        return t;
    });

    @Getter
    private CurrentSongProperty c_song_prpty = new CurrentSongProperty();
    @Getter
    private CurrentSongTitleProperty c_song_title_prpty = new CurrentSongTitleProperty();
    @Getter
    private CurrentSongArtistProperty c_song_artist_prpty = new CurrentSongArtistProperty();
    @Getter
    private CurrentSongAlbumProperty c_song_album_prpty = new CurrentSongAlbumProperty();

    static {
        refreshService.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                c_song_prpty.invalidate();
                c_song_title_prpty.invalidate();
                c_song_album_prpty.invalidate();
                c_song_artist_prpty.invalidate();
            });
        }, 1000, 100, TimeUnit.MILLISECONDS);
    }


}
