package com.tugas.NongkiKuy;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.tugas.NongkiKuy.R;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.tugas.NongkiKuy.Api.Api;
import com.tugas.NongkiKuy.Model.ModelCafe;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailCafeActivity extends AppCompatActivity {

    Toolbar tbDetailCafe;
    TextView tvNamaCafe, tvDescCafe, tvAddressCafe, tvPhoneCafe;
    ImageView imgCafe;
    String idCafe, NamaCafe, DescCafe, AddressCafe, PhoneCafe;
    ModelCafe modelCafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cafe);

        modelCafe = (ModelCafe) getIntent().getSerializableExtra("detailCafe");
        if (modelCafe != null) {
            idCafe = modelCafe.getIdCafe();
            NamaCafe = modelCafe.getTxtNamaCafe();
            DescCafe = modelCafe.getDescCafe();
            AddressCafe = modelCafe.getTxtAlamatCafe();
            PhoneCafe = modelCafe.getTxtNoTelp();

            //set Id
            imgCafe = findViewById(R.id.imgCafe);
            tvNamaCafe = findViewById(R.id.tvNamaCafe);
            tvDescCafe = findViewById(R.id.tvDescCafe);
            tvAddressCafe = findViewById(R.id.tvAddressCafe);
            tvPhoneCafe = findViewById(R.id.tvPhoneCafe);

            //get Image
            Glide.with(this)
                    .load(modelCafe.getGambarCafe())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgCafe);

            getDetailCafe();
        }
    }

    private void getDetailCafe() {
        AndroidNetworking.get("http://192.168.1.9//Cafe/index.php/auth/ApiDetail")
                .addPathParameter("id_cafe", idCafe)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                NamaCafe = response.getString("nama_cafe");
                                AddressCafe = response.getString("alamat");
                                DescCafe = response.getString("keterangan");
                                PhoneCafe = response.getString("no_telp");



                                //set Text
                                tvNamaCafe.setText(NamaCafe);
                                tvDescCafe.setText(DescCafe);
                                tvAddressCafe.setText(AddressCafe);
                                tvPhoneCafe.setText(PhoneCafe);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(DetailCafeActivity.this,
                                        "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(DetailCafeActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}