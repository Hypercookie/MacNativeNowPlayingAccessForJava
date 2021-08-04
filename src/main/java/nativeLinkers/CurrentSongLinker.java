package nativeLinkers;

//import org.json.JSONObject;

//import org.json.JSONObject;

public class CurrentSongLinker {

    static {
        System.loadLibrary("songlinker");
    }


    public native String getCurrentSongFromNativeMacPlayBack();


    public Song getCurrentSong() {
        //System.out.println(new CurrentSongLinker().getCurrentSongFromNativeMacPlayBack());
        String ok = getCurrentSongFromNativeMacPlayBack();
        System.out.println(ok);
        //JSONObject j = new JSONObject(ok);
     //   return new Song((String) j.get("Title"), (String) j.get("Album"), (String) j.get("Artist"));
        return null;
    }

    public static void main(String[] args) {

       new  CurrentSongLinker().getCurrentSong();
    }

}
