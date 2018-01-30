/*
 * Represents each mouse path as an ArrayList of points.
 */

import java.util.ArrayList;

public class CursorPath {
		
	private ArrayList<CursorPoint> pathCursorPoints;
	private int pathNumPoints;
	private int pathDistance;
	private int pathTimespanMilliseconds;
		
	public CursorPath(ArrayList<CursorPoint> cursorPoints)
	{
		this.pathCursorPoints = deepCopyCursorPoints(cursorPoints);
		this.pathNumPoints = cursorPoints.size();
		this.pathDistance = calculateCursorPathDistance();
		this.pathTimespanMilliseconds = calculateCursorPathTimespan();
	}
	
	private ArrayList<CursorPoint> deepCopyCursorPoints(ArrayList<CursorPoint> cursorPoints) {
		ArrayList<CursorPoint> cursorPointsCopy = new ArrayList<CursorPoint>(cursorPoints.size());
		for (CursorPoint cursorPoint : cursorPoints) {
			CursorPoint cursorPointCopy = new CursorPoint(cursorPoint.x, cursorPoint.y, cursorPoint.time);
			cursorPointsCopy.add(cursorPointCopy);
		}
		return cursorPointsCopy;
	}
	
	private int calculateCursorPathTimespan() {
		return getEndingCursorPoint().time - getStartingCursorPoint().time;
	}
	
	private int calculateCursorPathDistance() {
		return (int) calculateDistanceBetweenCursorPoints(getStartingCursorPoint(), getEndingCursorPoint());
	}
	
	private CursorPoint getStartingCursorPoint() {
		return pathCursorPoints.get(0);
	}
	
	private CursorPoint getEndingCursorPoint() {
		return pathCursorPoints.get(pathNumPoints - 1);
	}
	
	private double calculateDistanceBetweenCursorPoints(CursorPoint a, CursorPoint b) {
		return Math.hypot(a.x - b.x, a.y - b.y);
	}
	
	public boolean isCursorPathReasonable() {
		return isCursorPathTimespanReasonable() && isCursorPathDistanceReasonable() &&
				isCursorPathNumPointsReasonable();
	}
	
	private boolean isCursorPathTimespanReasonable() {
		return (this.pathTimespanMilliseconds > 100 && this.pathTimespanMilliseconds < 400);
	}
	
	private boolean isCursorPathDistanceReasonable() {
		return (this.pathDistance > 5 && this.pathDistance < 600);
	}
	
	private boolean isCursorPathNumPointsReasonable() {
		return (this.pathNumPoints > 5 && this.pathNumPoints < 50);
	}
	
	public ArrayList<CursorPoint> getCursorPathPoints() {
		return pathCursorPoints;
	}
	
	public int getCursorPathDistance() {
		return pathDistance;
	}
	
	public void displayCursorPoints() {
		for (CursorPoint p : pathCursorPoints) {
			System.out.println("(" + p.x + ", " + p.y + "), " + p.time);
		}
		System.out.println("Length:" + pathNumPoints + ", Timespan:" + pathTimespanMilliseconds);
	}
}