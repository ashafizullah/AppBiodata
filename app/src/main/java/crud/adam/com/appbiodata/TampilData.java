package crud.adam.com.appbiodata;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import crud.adam.com.appbiodata.adapter.AdapterData;
import crud.adam.com.appbiodata.api.ApiRequestBiodata;
import crud.adam.com.appbiodata.api.Retroserver;
import crud.adam.com.appbiodata.model.DataModel;
import crud.adam.com.appbiodata.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilData extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<DataModel> mItems = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);

        pd = new ProgressDialog(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerTemp);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);

        pd.setMessage("Mohon tunggu...");
        pd.setCancelable(false);
        pd.show();

        ApiRequestBiodata api = Retroserver.getClient().create(ApiRequestBiodata.class);
        Call<ResponseModel> getData = api.getBiodata();
        getData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pd.cancel();
                Log.d("retro", "Response: " + response.body().getKode());
                mItems = response.body().getResult();
                mAdapter = new AdapterData(TampilData.this, mItems);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.cancel();
                Log.d("retro", "Failed: respon gagal.");
            }
        });
    }
}