package io.leifu.golfscoreboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {
    private final GolfScore[] mGolfScores;
    private Context mContext;

    public ScoreAdapter(Context context, GolfScore[] golfScores) {
        mContext = context;
        mGolfScores = golfScores;
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hole, parent, false);
        ScoreViewHolder viewHolder = new ScoreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        holder.bindScore(mGolfScores[position]);
    }

    @Override
    public int getItemCount() {
        return mGolfScores.length;
    }

    static class ScoreViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.holeLabel)
        TextView mHoleLabel;
        @BindView(R.id.strokeCount)
        TextView mStrokeCount;
        @BindView(R.id.addStrokeButton)
        Button mAddStrokeButton;
        @BindView(R.id.removeStrokeButton)
        Button mRemoveStrokeButton;

        public ScoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindScore(final GolfScore golfScore) {
            mHoleLabel.setText(String.format("Hole %d:", golfScore.getHoleLabel()));
            mStrokeCount.setText(String.valueOf(golfScore.getStrokeCount()));
            mAddStrokeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int score = golfScore.getStrokeCount() + 1;
                   golfScore.setStrokeCount(score);
                    mStrokeCount.setText(String.valueOf(golfScore.getStrokeCount()));
                }
            });
           mRemoveStrokeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int score =golfScore.getStrokeCount();
                    if (score >= 1) {
                        golfScore.setStrokeCount(score - 1);
                    } else {
                        Toast.makeText(view.getContext(), "You can't set a stroke count to a negative integer.", Toast.LENGTH_LONG).show();
                    }
                    mStrokeCount.setText(String.valueOf(golfScore.getStrokeCount()));
                }
            });
        }
    }



}
