package io.leifu.golfscoreboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private final static int TOTAL_HOLES = 18;
    private static final String PREF_FILE = "io.leifu.golfscoreboard.preference";
    private GolfScore[] mGolfScores = new GolfScore[TOTAL_HOLES];
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private ScoreAdapter mAdapter;
    @BindView(android.R.id.list)
    ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSharedPreferences = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        for (int i = 0; i < TOTAL_HOLES; i++) {
            int score = mSharedPreferences.getInt("" + i, 0);
            mGolfScores[i] = new GolfScore(i + 1, score);
        }
        mAdapter= new ScoreAdapter(this, mGolfScores);
        mList.setAdapter(mAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (int i = 0; i < TOTAL_HOLES; i ++) {
            mEditor.putInt("" + i, mGolfScores[i].getStrokeCount());
        }
        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_strokes) {
            mEditor.clear();
            mEditor.apply();
            for (int i = 0; i < TOTAL_HOLES; i++) {
                mGolfScores[i] = new GolfScore(i + 1, 0);
            }
            mAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
