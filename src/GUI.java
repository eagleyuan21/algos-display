import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GUI {
	private static int max = 15;
	
	private static JFrame frame;
	private static JPanel panel;
	
	private static int numel;
	private static boolean search_sort = false;
	private static boolean asscend_dessend = true;
	private static int searchval = -1;
	private static String type;
	private static int[] disp;
	private static String[] searchl = {"Linear", "Chunk", "Binary"};
	private static String[] sortl = {"Insertion", "Selection", "Merge"};
	
	public GUI(){
		
	  frame = new JFrame("Algorithms Visualization");
	  panel = new JPanel();
	  frame.setSize(500,500);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	  frame.add(panel);
	  panel.setLayout(null);
	  
	  JButton assc = new JButton(new AbstractAction("Asscending"){
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	            asscend_dessend = true;
	        }
	  });
	  
	  JButton dess = new JButton(new AbstractAction("Dessending"){
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	asscend_dessend = false;
	        }
	  });
	  
	  assc.setBounds(165, 10, 105, 25);
	  panel.add(assc);
	  dess.setBounds(165, 35, 105, 25);
	  panel.add(dess);
	  assc.setVisible(true);
      dess.setVisible(true);
      
      JLabel s_val = new JLabel("Search Value: ");
	  s_val.setBounds(175, 10, 120, 25);
	  panel.add(s_val);	  
	  s_val.setVisible(false);
	  
	  SpinnerNumberModel model1 = new SpinnerNumberModel(0, 0, max * 4, 1); 
	  JSpinner s_select = new JSpinner(model1);

	  s_select.setBounds(175, 35, 80, 25);
	  panel.add(s_select);	  
	  s_select.setVisible(false);
	  
	  JComboBox algos = new JComboBox(sortl);
	  algos.setBounds(270, 23, 110, 25);
	  panel.add(algos);
	  
	  JButton search = new JButton(new AbstractAction("Search"){
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	            search_sort = true;

	            algos.removeAllItems();

	            for (String item : searchl) {
	                algos.addItem(item);
	            }
	            
	            assc.setVisible(false);
	            dess.setVisible(false);
	            s_val.setVisible(true);
	            s_select.setVisible(true);

	            // adding combobox to panel
	            panel.add(algos);    
	        }
	  });
	  
	  JButton sort = new JButton(new AbstractAction("Sort"){
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	            search_sort = false;
	            algos.removeAllItems();

	            for (String item : sortl) {
	                algos.addItem(item);
	            }
	            
	            assc.setVisible(true);
	            dess.setVisible(true);
	            s_val.setVisible(false);
	            s_select.setVisible(false);
	            
	            // adding combobox to panel
	            panel.add(algos);   
	        }
	  });
	  
	  search.setBounds(5, 10, 80, 25);
	  panel.add(search);
	  sort.setBounds(80, 10, 80, 25);
	  panel.add(sort);
	  
	  JLabel sizeask = new JLabel("Size of List: ");
	  sizeask.setBounds(5, 35, 80, 25);
	  panel.add(sizeask);	  
	  JSlider sizeval = new JSlider(0, max, 8);
	  sizeval.setBounds(75, 35, 100, 25);
	  sizeval.setMinorTickSpacing(1);
	  sizeval.setMajorTickSpacing(3);
	  sizeval.setPaintTicks(true);
	  panel.add(sizeval);
	  
	  JButton setup = new JButton(new AbstractAction("Setup"){
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	numel = sizeval.getValue();
	        	if(search_sort)
	        	{
	        		searchval = (int) s_select.getValue();
	        	}
	        	disp = setarr(numel, search_sort, searchval);
	        	type = algos.getSelectedItem().toString();
	            for(int i = 0; i < numel; i++)
	      	  	{
	      		  System.out.println(disp[i]);
	      	  	}
	      	  	System.out.println();
	        }
	  });
	  
	  JButton run = new JButton(new AbstractAction("Run"){
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	            runsim(disp, search_sort, searchval, asscend_dessend, type);
	            for(int i = 0; i < numel; i++)
	      	  	{
	      		  System.out.println(disp[i]);
	      	  	}
	        }
	  });
	  
	  setup.setBounds(380, 10, 80, 25);
	  panel.add(setup);
	  run.setBounds(380, 35, 80, 25);
	  panel.add(run);
	  
	  frame.setVisible(true);
	}
	
	public static void main(String[] args) {
	  
	  new GUI();
	  
	}
  
	public static int[] setarr(int size, boolean S_s, int s_val)
	{
	  int[] arr = new int[size];
	  
	  for (int i = 0; i < size; i++)
	  {
		  arr[i] = (int)(Math.random() * size * 4);
	  }
	  
	  if(S_s)
	  {
		  arr[(int)(Math.random() * size)] = s_val;
	  }
	  
	  return arr;
	}
  
	public static void runsim(int[] arr, boolean search_or_sort, int searching, boolean assc_dess, String spec)
	{
	  if(search_or_sort)
	  {
		  
	  }
	  else {
		  if(spec.equals("Insertion"))
		  {
			  insertsort(arr, assc_dess);
		  }
	  }
	}
  
	public static void insertsort(int arr[], boolean order) 
	{ 
      int n = arr.length; 
      for (int i = 1; i < n; ++i) { 
          int key = arr[i]; 
          int j = i - 1; 
          
          if(order)
          {
        	  while (j >= 0 && arr[j] > key) { 
                  arr[j + 1] = arr[j]; 
                  j = j - 1; 
              } 
          }
          else {
        	  while (j >= 0 && arr[j] < key) { 
                  arr[j + 1] = arr[j]; 
                  j = j - 1; 
              } 
          }
          
          arr[j + 1] = key; 
      } 
	}
}