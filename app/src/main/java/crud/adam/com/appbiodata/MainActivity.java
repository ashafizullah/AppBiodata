package crud.adam.com.appbiodata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import crud.adam.com.appbiodata.api.ApiRequestBiodata;
import crud.adam.com.appbiodata.api.Retroserver;
import crud.adam.com.appbiodata.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    EditText nama, usia, domisili;
    Button btnSave, btnTampilData;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = (EditText) findViewById(R.id.etNama);
        usia = (EditText) findViewById(R.id.etUsia);
        domisili = (EditText) findViewById(R.id.etDomisili);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnTampilData = (Button) findViewById(R.id.btnTampilData);
        pd = new ProgressDialog(this);

        btnTampilData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TampilData.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Sedang menyimpan data...");
                pd.setCancelable(false);
                pd.show();

                String snama = nama.getText().toString();
                String susia = usia.getText().toString();
                String sdomisili = domisili.getText().toString();

                ApiRequestBiodata api = Retroserver.getClient().create(ApiRequestBiodata.class);

                Call<ResponseModel> saveBio = api.saveBiodata(snama, susia, sdomisili);
                saveBio.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        pd.cancel();
                        Log.d("retro", "Response: " + response.body().toString());
                        String kode = response.body().getKode();

                        if(kode.equals("1")){
                            Toast.makeText(MainActivity.this, "Data berhasil disimpan.", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Data gagal disimpan.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.cancel();
                        Log.d("retro", "Gagal mengirim request.");
                    }
                });
            }
        });
    }
}