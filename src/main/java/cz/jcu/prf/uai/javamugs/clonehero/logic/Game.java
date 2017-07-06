package cz.jcu.prf.uai.javamugs.clonehero.logic;

/**
 * Game logic class.
 *
 * @author Daniel Hryzbil
 */
public class Game {

    public static final int SCORE_BASE = 100;
    public static final double MULTIPLIER_BASE_EASY = 0.2;
    public static final double MULTIPLIER_BASE_NORMAL = 0.5;
    public static final double MULTIPLIER_BASE_HARD = 0.8;

    private double timeOffset;
    private PressChart pressChart;
    private Score score;
    private Buffer buffer;
    private double multiplierBase;

    /**
     * Creates game logic.
     *
     * @param timeOffset game speed.
     * @param difficulty game difficulty.
     * @param pressChart loaded press chart. Never null.
     */
    public Game(double timeOffset, byte difficulty, PressChart pressChart) {
        this.timeOffset = timeOffset;
        this.pressChart = pressChart;
        score = new Score();
        buffer = new Buffer(difficulty);
        switch (difficulty) {
            case 1:
                multiplierBase = MULTIPLIER_BASE_EASY;
                break;
            case 2:
                multiplierBase = MULTIPLIER_BASE_NORMAL;
                break;
            default:
                multiplierBase = MULTIPLIER_BASE_HARD;
        }
    }

    /**
     * Updates and reports current game status.
     *
     * @param currentTime actual game time.
     * @param chord       chord pressed keys by user.
     * @return game status. Never null.
     */
    public GameReport tick(double currentTime, Chord chord) {
        Chord next = pressChart.next(currentTime);
        buffer.addToBuffer(next, currentTime + timeOffset);
        BufferReport report = buffer.check(chord, currentTime);

        if (report.getMiss() > 0) {
            score.resetMultiplier();
        }

        if (report.getHit() > 0) {
            score.addScore(SCORE_BASE * report.getHit());
            score.addMultiplier(multiplierBase);
        }

        return new GameReport(score, next, report.getHitChord(), report.getMissChord());
    }

    /**
     * @return speed of game (offset between drawing and hitting chords)
     */
    public double getTimeOffset() {
        return timeOffset;
    }

    /**
     * @return actual score multiplier base
     */
    public double getScoreMultiplierBase() {
        return multiplierBase;
    }
}
