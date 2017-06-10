package DesignPatterns;

public interface IState {
	void goNext();
	void handle();
	IContext getWrapper();
}
