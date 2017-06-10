package Tanks;


public class Qlearning {
	public int[] states = {1,2,3,4,5};
	public Action[] actions = {Action.up,Action.down,Action.left,Action.right};
	public static double gamma;
	public static double alpha;
	
	public int q[][];
	public int currentState;
	public Action currentAction;
	
	public enum Action {
		up,down,left,right;
	}
}



