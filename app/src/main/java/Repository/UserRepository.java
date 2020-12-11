package Repository;

import com.example.vietis_fuddy.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import Entity.User;
import Ultilities.API;
import Ultilities.AppResources;
import Ultilities.UserApp;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserRepository {
    private static UserRepository instance = null;
    private IUserRepository iUserRepository;
    private List<User> users;
    private static final String URL_LOGIN = API.get_URL_STRING(AppResources.getResourses().getString(R.string.USER_LOGIN));
    private static final String URL_REGISTER = API.get_URL_STRING(AppResources.getResourses().getString(R.string.USER_REGISTER));
    private final String TAG = "User Repository";

    private UserRepository(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public static UserRepository getInstance(IUserRepository iUserRepository) {
        if (instance == null) {
            instance = new UserRepository(iUserRepository);
        }
        return instance;
    }
    public void login(String email, String password){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email",email)
                .addFormDataPart("password",password)
                .build();
        Request request = new Request.Builder()
                .url(URL_LOGIN)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                iUserRepository.afterLogin(null,e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try{
                    String jsonString = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonString);
                    String result = jsonObject.getString("result");
                    if(result.equals("SC")){
                        JSONObject JSONuser = jsonObject.getJSONObject("data");
                        User user = User.createUserFromJSONObject(JSONuser);
                        user.setImageURL(jsonObject.getJSONObject("Image_model").getString("imageURL"));
                        iUserRepository.afterLogin(user,null);
                    }else{
                        iUserRepository.afterLogin(null, new JSONException(result));
                    }

                }catch (JSONException e){
                    iUserRepository.afterLogin(null,e);
                }
            }
        });
    }

    public void register(String email, String password){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email",email)
                .addFormDataPart("password",password)
                .addFormDataPart("userType","1")
                .build();
        Request request = new Request.Builder()
                .url(URL_REGISTER)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                iUserRepository.afterRegister(null,e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try{
                    String jsonString = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonString);
                    String result = jsonObject.getString("result");
                    if(result.equals("SC")){
                        JSONObject jsonUser = jsonObject.getJSONObject("data");
                        User user = User.createUserFromJSONObject(jsonUser);
                        iUserRepository.afterRegister(user,null);
                    }else{
                        iUserRepository.afterRegister(null,new JSONException(result));
                    }

                }catch (JSONException e){
                    iUserRepository.afterRegister(null,e);
                }
            }
        });
    }
}
