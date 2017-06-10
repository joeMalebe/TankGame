package DesignPatterns;

import UI.Screen;

public interface IContext {
	void goNext();
	void setState(Screen screen);
}
