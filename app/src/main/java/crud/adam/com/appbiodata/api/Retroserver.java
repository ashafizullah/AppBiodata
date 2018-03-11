package crud.adam.com.appbiodata.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adams on 03/11/2018.
 */

public class Retroserver {
    private static final String base_url = "http://10.0.3.2:8082/crud_retrofit/";

    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
