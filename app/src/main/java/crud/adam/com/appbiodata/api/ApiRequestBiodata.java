package crud.adam.com.appbiodata.api;

import crud.adam.com.appbiodata.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by adams on 03/11/2018.
 */

public interface ApiRequestBiodata {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel> saveBiodata(@Field("nama") String nama,
                                    @Field("usia") String usia,
                                    @Field("domisili") String domisili);

    @GET("read.php")
    Call<ResponseModel> getBiodata();
}
