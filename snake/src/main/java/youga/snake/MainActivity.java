package youga.snake;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;
import youga.snake.api.DataForm;
import youga.snake.api.Snake;

public class MainActivity extends AppCompatActivity {

    private static final String DATA = "DATA";
    private static final int REQ_SIGN = 222;
    private static final String SP_NAME = "SNAKE";

    @BindView(R.id.et_length)
    EditText mEtLength;
    @BindView(R.id.et_kill)
    EditText mEtKill;
    @BindView(R.id.btn_snake)
    Button mBtnSnake;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @BindView(R.id.rb_forever)
    RadioButton mRbForever;
    @BindView(R.id.rb_time)
    RadioButton mRbTime;
    @BindView(R.id.iv_user)
    ImageView mIvUser;
    @BindView(R.id.tv_nick)
    TextView mTvNick;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.tv_forever_length)
    TextView mTvForeverLength;
    @BindView(R.id.tv_forever_kill)
    TextView mTvForeverKill;
    @BindView(R.id.tv_time_length)
    TextView mTvTimeLength;
    @BindView(R.id.tv_time_kill)
    TextView mTvTimeKill;
    @BindView(R.id.ll_info)
    LinearLayout mLlInfo;
    Gson mGson = new Gson();
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.btn_sign)
    Button mBtnSign;
    private DataForm.Data mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        String json = getSharedPreferences(SP_NAME, MODE_PRIVATE).getString(DATA, null);
        mLlInfo.setVisibility(View.GONE);
        mBtnSign.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.INVISIBLE);
        if (json != null) {
            mUser = mGson.fromJson(json, DataForm.Data.class);
            HashMap<String, String> map = new HashMap<>();
            map.put("push_id", mUser.getPush_id());
            map.put("sid", mUser.getSid());
            map.put("uid", mUser.getUid());
            map.put("target_uid", mUser.getTarget_uid());
            getScoreInfo(map);
        } else {
            mBtnSign.setVisibility(View.VISIBLE);
        }

        mBtnSnake.setOnClickListener(view -> {
            String length = mEtLength.getText().toString();
            String kill = mEtKill.getText().toString();
            if (length.isEmpty()) {
                Toast.makeText(MainActivity.this, mEtLength.getHint().toString(), Toast.LENGTH_SHORT).show();
                return;
            }
            if (kill.isEmpty()) {
                Toast.makeText(MainActivity.this, mEtKill.getHint().toString(), Toast.LENGTH_SHORT).show();
                return;
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("push_id", mUser.getPush_id());
            map.put("sid", mUser.getSid());
            map.put("uid", mUser.getUid());
            map.put("target_uid", mUser.getTarget_uid());
            map.put("game_mode", mRbForever.isChecked() ? "1" : "2");
            map.put("kill", kill);
            map.put("length", length);
            pushScore(map);
        });

        mBtnSign.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignActivity.class);
            startActivityForResult(intent, REQ_SIGN);
        });

        registerNetWorkBroadcast();
    }

    private void pushScore(HashMap<String, String> map) {
        mProgressBar.setVisibility(View.VISIBLE);
        Snake.pushScore(MainActivity.this, map, response -> runOnUiThread(() -> {
            mProgressBar.setVisibility(View.GONE);
            try {
                DataForm data1 = mGson.fromJson(response.body().string(), DataForm.class);
                mTvResult.setText(data1.getMessage());
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("push_id", mUser.getPush_id());
                map1.put("sid", mUser.getSid());
                map1.put("uid", mUser.getUid());
                map1.put("target_uid", mUser.getTarget_uid());
                getScoreInfo(map1);
            } catch (Exception e) {
                e.printStackTrace();
                mTvResult.setText(e.getMessage());
            }
        }));
    }


    private void registerNetWorkBroadcast() {
        NetWorkBroadcast broadcast = new NetWorkBroadcast();
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("wx_auth_success");
        registerReceiver(broadcast, localIntentFilter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_SIGN && resultCode == Activity.RESULT_OK) {
            HashMap<String, String> map = (HashMap<String, String>) data.getSerializableExtra("MAP");
            mBtnSign.setVisibility(View.GONE);
            getScoreInfo(map);
        }
    }

    private void getScoreInfo(HashMap<String, String> map) {
        mProgressBar.setVisibility(View.VISIBLE);
        Snake.getScoreInfo(this, map, response -> runOnUiThread(() -> {
            mLlInfo.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
            try {
                if (response.code() == 200) {
                    DataForm data1 = mGson.fromJson(response.body().string(), DataForm.class);
                    mUser = data1.getData();
                    mUser.setPush_id(map.get("push_id"));
                    mUser.setSid(map.get("sid"));
                    mUser.setUid(map.get("uid"));
                    mUser.setTarget_uid(map.get("target_uid"));
                    display(mUser);
                    SharedPreferences.Editor editor = getSharedPreferences(SP_NAME, MODE_PRIVATE).edit();
                    editor.putString(DATA, mGson.toJson(mUser));
                    editor.apply();
                } else {
                    mTvResult.setText(response.body().string());
                }
            } catch (Exception e) {
                e.printStackTrace();
                mTvResult.setText(e.getMessage());
            }
        }));
    }

    private void display(DataForm.Data user) {
        mTvNick.setText(user.getNickname() + "\t" + user.getAge() + getString(R.string.age));
        mTvCount.setText(String.format(getString(R.string.count), user.getLen(), user.getKill()));
        mTvForeverLength.setText(String.format(getString(R.string.longest_length), user.getEnd_len(), user.getEnd_len_rank()));
        mTvForeverKill.setText(String.format(getString(R.string.most_kill), user.getEnd_kill(), user.getEnd_kill_rank()));
        mTvTimeLength.setText(String.format(getString(R.string.longest_length), user.getLimit_len(), user.getLimit_len_rank()));
        mTvTimeKill.setText(String.format(getString(R.string.most_kill), user.getLimit_kill(), user.getLimit_kill_rank()));
        Glide.with(this)
                .load(user.getAvatar())
                .into(mIvUser);
    }

    static class NetWorkBroadcast extends BroadcastReceiver {

        private static final String TAG = "NetWorkBroadcast";

        public void onReceive(Context paramContext, Intent paramIntent) {
            Log.i("666", "onReceive: " + paramIntent);
            String action = paramIntent.getAction();
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {

            } else if ("wx_auth_success".equals(action)) {
                String access_token = paramIntent.getStringExtra("access_token");
                Log.i(TAG, "access_token:" + access_token);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("SIGN IN").setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, SignActivity.class);
        startActivityForResult(intent, REQ_SIGN);
        return true;
    }
}
