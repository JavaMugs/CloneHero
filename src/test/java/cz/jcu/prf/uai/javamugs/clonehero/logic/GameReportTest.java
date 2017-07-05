package cz.jcu.prf.uai.javamugs.clonehero.logic;

import junit.framework.TestCase;

/**
 * GameReport class test.
 * @author Daniel Hryzbil
 */
public class GameReportTest extends TestCase {
	public void testGameReportContainer() {
		cz.jcu.prf.uai.javamugs.clonehero.logic.Score score = new cz.jcu.prf.uai.javamugs.clonehero.logic.Score();
		score.addMultiplier(1.0);
		score.addScore(200);
		cz.jcu.prf.uai.javamugs.clonehero.logic.Chord chordToDraw = new cz.jcu.prf.uai.javamugs.clonehero.logic.Chord(true, false, true, false, true);
		cz.jcu.prf.uai.javamugs.clonehero.logic.Chord chordHit = new cz.jcu.prf.uai.javamugs.clonehero.logic.Chord(false, true, false, true, false);
		cz.jcu.prf.uai.javamugs.clonehero.logic.Chord chordMiss = new cz.jcu.prf.uai.javamugs.clonehero.logic.Chord(true, false, false, true, false);
		cz.jcu.prf.uai.javamugs.clonehero.logic.GameReport report = new cz.jcu.prf.uai.javamugs.clonehero.logic.GameReport(score, chordToDraw, chordHit, chordMiss);
		assertNotNull(report);
		assertEquals(chordToDraw.getChords(), report.getChordToDraw().getChords());
		assertEquals(chordHit.getChords(), report.getHitChord().getChords());
		assertEquals(chordMiss.getChords(), report.getMissChord().getChords());
		assertEquals(score.getScore(), report.getScore());
		assertEquals(score.getMultiplier(), report.getMultiplier());
	}
}
