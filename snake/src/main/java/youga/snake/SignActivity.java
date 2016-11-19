package youga.snake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignActivity extends AppCompatActivity {

    @BindView(R.id.et_json)
    EditText mEtJson;
    @BindView(R.id.btn_sign)
    Button mBtnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        init();
    }

    /**
     * snake_sign=fIVFAl6xonw6WYUnyDGNSnmOUDc%3D%0A
     * &device_id=imei_35436007025306_uuid_147887674745207807
     * &target_uid=be5c225e-7fbc-4da8-bf44-01912f13f081
     * &push_id=AHYE5Bsdg1flx4rc8%2F4Nm2U0s5IKtyfr4fV1hvpGj9o%3D
     * &version_code=2038
     * &channel=official
     * &version=2.0
     * &platform=2
     * &sid=bfeedf1e703cfd7543c1c5d464de2c9d
     * &market=official
     * &uid=be5c225e-7fbc-4da8-bf44-01912f13f081
     * &push_channel=2
     */
    private void init() {

        mBtnSign.setOnClickListener(view -> {
            if (mEtJson.getText().toString().isEmpty()) {
                Toast.makeText(SignActivity.this, mEtJson.getHint().toString(), Toast.LENGTH_SHORT).show();
                return;
            }
            String json = mEtJson.getText().toString();
            String[] splits = json.split("&");
            HashMap<String, String> map = new HashMap<>();
            for (String split : splits) {
                if (split.contains("="))
                    map.put(split.split("=")[0], split.split("=")[1]);
            }
            String text = getString(R.string.lack);
            if (!map.containsKey("push_id")) {
                Toast.makeText(SignActivity.this, String.format(text, "push_id"), Toast.LENGTH_SHORT).show();
                return;
            }
            if (!map.containsKey("sid")) {
                Toast.makeText(SignActivity.this, String.format(text, "sid"), Toast.LENGTH_SHORT).show();
                return;
            }
            if (!map.containsKey("uid")) {
                Toast.makeText(SignActivity.this, String.format(text, "uid"), Toast.LENGTH_SHORT).show();
                return;
            }
            if (!map.containsKey("target_uid")) {
                Toast.makeText(SignActivity.this, String.format(text, "target_uid"), Toast.LENGTH_SHORT).show();
                return;
            }
            map.remove("snake_sign");
            Intent data = new Intent();
            data.putExtra("MAP", map);
            setResult(Activity.RESULT_OK, data);
            finish();
        });


    }
}
