package io.leifu.golfscoreboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreAdapter extends BaseAdapter {
    private final GolfScore[] mGolfScores;
    private Context mContext;

    public ScoreAdapter(Context context, GolfScore[] golfScores) {
        mContext = context;
        mGolfScores = golfScores;
    }

    @Override
    public int getCount() {
        return mGolfScores.length;
    }

    @Override
    public Object getItem(int i) {
        return mGolfScores[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_hole, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mHoleLabel.setText(String.format("Hole %d:", mGolfScores[i].getHoleLabel()));
        holder.mStrokeCount.setText(String.valueOf(mGolfScores[i].getStrokeCount()));
        holder.mAddStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int score = mGolfScores[i].getStrokeCount() + 1;
                mGolfScores[i].setStrokeCount(score);
                holder.mStrokeCount.setText(String.valueOf(mGolfScores[i].getStrokeCount()));
            }
        });
        holder.mRemoveStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int score = mGolfScores[i].getStrokeCount();
                if (score >= 1) {
                    mGolfScores[i].setStrokeCount(score - 1);
                } else {
                    Toast.makeText(mContext, "You can't set a stroke count to a negative integer.", Toast.LENGTH_LONG).show();
                }
                holder.mStrokeCount.setText(String.valueOf(mGolfScores[i].getStrokeCount()));
            }
        });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.holeLabel)
        TextView mHoleLabel;
        @BindView(R.id.strokeCount)
        TextView mStrokeCount;
        @BindView(R.id.addStrokeButton)
        Button mAddStrokeButton;
        @BindView(R.id.removeStrokeButton)
        Button mRemoveStrokeButton;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
