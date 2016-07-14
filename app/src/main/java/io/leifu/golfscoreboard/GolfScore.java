package io.leifu.golfscoreboard;

public class GolfScore {
    private int holeLabel;
    private int strokeCount;

    public GolfScore(int holeLabel, int strokeCount) {
        this.holeLabel = holeLabel;
        this.strokeCount = strokeCount;
    }

    public int getHoleLabel() {
        return holeLabel;
    }

    public void setHoleLabel(int holeLabel) {
        this.holeLabel = holeLabel;
    }

    public int getStrokeCount() {
        return strokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        this.strokeCount = strokeCount;
    }
}
