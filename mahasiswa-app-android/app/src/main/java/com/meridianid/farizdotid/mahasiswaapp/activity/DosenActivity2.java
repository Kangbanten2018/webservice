package com.meridianid.farizdotid.mahasiswaapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.meridianid.farizdotid.mahasiswaapp.R;
import com.meridianid.farizdotid.mahasiswaapp.adapter.DosenAdapter;
import com.meridianid.farizdotid.mahasiswaapp.adapter.DosenAdapter2;
import com.meridianid.farizdotid.mahasiswaapp.model.ResponseDosen;
import com.meridianid.farizdotid.mahasiswaapp.model.ResponseDosen2;
import com.meridianid.farizdotid.mahasiswaapp.model.SemuadosenItem;
import com.meridianid.farizdotid.mahasiswaapp.model.SemuadosenItem2;
import com.meridianid.farizdotid.mahasiswaapp.util.api.BaseApiService;
import com.meridianid.farizdotid.mahasiswaapp.util.api.BaseApiService2;
import com.meridianid.farizdotid.mahasiswaapp.util.api.UtilsApi;
import com.meridianid.farizdotid.mahasiswaapp.util.api.UtilsApi2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DosenActivity2 extends AppCompatActivity {

    @BindView(R.id.rvDosen)
    RecyclerView rvDosen;
    ProgressDialog loading;

    Context mContext;
    List<SemuadosenItem2> semuadosenItemList = new ArrayList<>();
    DosenAdapter2 dosenAdapter;
    BaseApiService2 mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);

        getSupportActionBar().setTitle("Mahasiswa");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi2.getAPIService2();

        dosenAdapter = new DosenAdapter2(this, semuadosenItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvDosen.setLayoutManager(mLayoutManager);
        rvDosen.setItemAnimator(new DefaultItemAnimator());

        getResultListDosen();
    }

    private void getResultListDosen(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        mApiService.getSemuaDosen2().enqueue(new Callback<ResponseDosen2>() {
            @Override
            public void onResponse(Call<ResponseDosen2> call, Response<ResponseDosen2> response) {
                if (response.isSuccessful()){
                    loading.dismiss();

                    final List<SemuadosenItem2> semuaDosenItems = response.body().getSemuadosen();

                    rvDosen.setAdapter(new DosenAdapter2(mContext, semuaDosenItems));
                    Log.e("Log:",semuaDosenItems.toString());
                    dosenAdapter.notifyDataSetChanged();
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDosen2> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
