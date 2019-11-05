// Chee Yap, CS 102
// Fall 2019, hw5
import java.util.Random;

interface InterfaceCircList {
	void add(Node u);	// NOTE!!! We add a "Node" not an "int".
	void remove();
	int size();
	void show(String msg);
	void rot();
	void rot(int n);
	void rev();
}
