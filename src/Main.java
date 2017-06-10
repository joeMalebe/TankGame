import java.awt.EventQueue;

import javax.swing.JFrame;

import List.IList;
import List.IPosition;
import List.List;
import List.ListNode;
import UI.GameFrame;

import Graphics.Frame;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		    EventQueue.invokeLater(new Runnable() {
		        public void run() {
		            //.. Create UI
		        	new GameFrame();
		        }
		    });
		
		
		/*IList<String> s = new List<String>();
		s.addLast("hello");
		s.addLast("My name");
		s.addLast("is not");
		s.addLast("japody");
		int count = 0;
		for(String a:s)
		{
			System.out.println(a.toString());
			count++;
		}
		System.out.println(count +" loops " +s.size()+" Size");*/
//		Frame j = new Frame();
//		j.setSize(Frame.FRAME_WIDTH,Frame.FRAME_HEIGHT);	
//		j.setLocationRelativeTo(null);
//		j.setVisible(true);
//		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		j.setResizable(false);
		//System.out.println("hello");
	}

}
