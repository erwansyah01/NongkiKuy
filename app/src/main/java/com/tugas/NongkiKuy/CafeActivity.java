package com.tugas.NongkiKuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.tugas.NongkiKuy.R;
import com.tugas.NongkiKuy.Adapter.CafeAdapter;
import com.tugas.NongkiKuy.Api.Api;
import com.tugas.NongkiKuy.Model.ModelCafe;
import com.tugas.NongkiKuy.Utils.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class CafeActivity extends AppCompatActivity implements CafeAdapter.onSelectData {

    RecyclerView rvCafe;
    CafeAdapter cafeAdapter;
    ProgressDialog progressDialog;
    List<ModelCafe> modelCafe = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvCafe = findViewById(R.id.rvCafe);
        rvCafe.setHasFixedSize(true);
        rvCafe.setLayoutManager(new LinearLayoutManager(this));


        getCafe();
    }

    private void getCafe() {
        progressDialog.show();
        AndroidNetworking.get("http://192.168.1.9//Cafe/index.php/auth/API")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray playerArray = response.getJSONArray("API");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                ModelCafe dataApi = new ModelCafe();
                                dataApi.setTxtNamaCafe(temp.getString("nama_cafe"));
                                dataApi.setTxtAlamatCafe(temp.getString("alamat"));
                                dataApi.setGambarCafe(temp.getString("gambar"));
                                dataApi.setDescCafe(temp.getString("keterangan"));
                                dataApi.setTxtNoTelp(temp.getString("no_telp"));
                                modelCafe.add(dataApi);
                                showCafe();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CafeActivity.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(CafeActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showCafe() {
        cafeAdapter = new CafeAdapter(CafeActivity.this, modelCafe, this);
        rvCafe.setAdapter(cafeAdapter);
    }

    @Override
    public void onSelected(ModelCafe modelCafe) {
        Intent intent = new Intent(CafeActivity.this, DetailCafeActivity.class);
        intent.putExtra("detailCafe", modelCafe);
        startActivity(intent);
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