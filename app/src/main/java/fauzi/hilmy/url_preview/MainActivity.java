package fauzi.hilmy.url_preview;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fauzi.hilmy.url_preview.api.ApiClient;
import fauzi.hilmy.url_preview.api.ApiInterface;
import fauzi.hilmy.url_preview.model.ResponsePreview;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edtUrl)
    TextInputEditText edtUrl;
    @BindView(R.id.btnPreview)
    Button btnPreview;
    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtDesc)
    TextView txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnPreview)
    public void onViewClicked() {
        String url = edtUrl.getText().toString();
        if (!url.isEmpty()){
            loadPrev(url);
        }
    }

    private void loadPrev(String url) {
        ApiInterface interfacee = ApiClient.getInstance();
        Call<ResponsePreview> call = interfacee.getTech(BuildConfig.TSDB_API_KEY, url);
        call.enqueue(new Callback<ResponsePreview>() {
            @Override
            public void onResponse(Call<ResponsePreview> call, Response<ResponsePreview> response) {
                if (!response.body().getDescription().equals("Invalid response status code (0)")) {
                    Picasso.get()
                            .load(response.body().getImage())
                            .into(imgLogo);
                    txtTitle.setText(response.body().getTitle());
                    txtDesc.setText(response.body().getDescription());
                } else {
                    Toast.makeText(MainActivity.this, response.body().getDescription(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePreview> call, Throwable t) {

            }
        });
    }
}
