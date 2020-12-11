package Repository;

import com.example.vietis_fuddy.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import Ultilities.API;
import Ultilities.AppResources;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NotificationRepository {
    private static NotificationRepository instance = null;
    private final INotiRepository iNotiRepository;
    private final String TAG = "Noti Repo";
    public static final String URL_ADD_PHONE_TOKEN = API.get_URL_STRING(AppResources.getResourses().getString(R.string.ADD_TOKEN_KEY));
    private NotificationRepository(INotiRepository iNotiRepository) {
        this.iNotiRepository = iNotiRepository;
    }

    public static NotificationRepository getInstance(INotiRepository iNotiRepository) {
        if(instance==null){
            instance = new NotificationRepository(iNotiRepository);
        }
        return instance;
    }
    public void deviceRegister ( String tokenKey){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL_ADD_PHONE_TOKEN)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                iNotiRepository.getNotiMessage(null,e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String jsonString = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonString);
                    String msg = jsonObject.getString("message");
                    iNotiRepository.getNotiMessage(msg,null);
                }catch (JSONException e){
                    iNotiRepository.getNotiMessage(null,e);
                }
            }
        });
    }
}
