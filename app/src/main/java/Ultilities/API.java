package Ultilities;

import com.example.vietis_fuddy.R;

public class API {
    /**
     * Return URL String for path with host and port
     *
     * @param path
     * @return
     */
    public static String get_URL_STRING(String path) {
        return "http://" + AppResources.getResourses().getString(R.string.HOST_NAME) + ":" + AppResources.getResourses().getString(R.string.POST) +"/"+ path;
    }


}