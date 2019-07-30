package PracticeCodes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Toufiq Rich
 */

class WindowSensor extends WindowAdapter{
	public void windowClosing(WindowEvent we){
        System.out.println("Window is closing");
		System.exit(0);
	}
}
class MyFrame extends Frame{
	public String msg;
	public TextField deptNo,dName,loc;
	public Label message;
	public MyFrame(){
		super("Read Update and Delete Demo");
		msg="Window Created";		
		
		message=new Label();
		Label dNoLabel=new Label("Department No.");
		Label dNameLabel=new Label("Department Name");
		Label dLocLabel=new Label("Department Location");
		Button readButton=new Button("Read");Button updateButton=new Button("Update");Button deleteButton=new Button("Delete");
		
		deptNo=new TextField(30);dName=new TextField(30);loc=new TextField(30);
	
		add(dNoLabel);add(deptNo);
		add(dNameLabel);add(dName);
		add(dLocLabel);add(loc);
		
		add(readButton);
		add(updateButton);
		add(deleteButton);add(message);
		
		ButtonSensor bs=new ButtonSensor(this);
		readButton.addActionListener(bs);
		updateButton.addActionListener(bs);
		deleteButton.addActionListener(bs);
		
		setSize(280,300);
		int screenWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		setLocation((screenWidth-280)/2,120);
		setLayout(new FlowLayout());
	}
}
class Utility{
	public void readData(MyFrame frame){
		String sql="select * from emp where deptno='"+frame.deptNo.getText()+"'";
		frame.deptNo.setText(frame.deptNo.getText());
		frame.dName.setText("Dummy Dept Name from DB");
		frame.loc.setText("Dummy Dept Location from DB");
		System.out.println(sql);
	}
	public void updateData(MyFrame frame){
		String sql="update emp set dname='"+frame.dName.getText()+"', loc='"+frame.loc.getText()+"' where deptNo='"+frame.deptNo.getText()+"'";
		System.out.println(sql);
	}
	public void deleteData(MyFrame frame){
		String sql="delete from emp where deptNo='"+frame.deptNo.getText()+"'";
		System.out.println(sql);
	}
}
class ButtonSensor implements ActionListener{
	MyFrame mf;
	Utility u;
	public ButtonSensor(MyFrame f){
		mf=f;
		u=new Utility();
	}
	public void actionPerformed(ActionEvent ae){
		boolean flag=true;
		String dn=mf.deptNo.getText();
		if(dn.length()==0){
			JOptionPane.showMessageDialog(mf,"You must provide a department no.");
			System.out.println("You must provide a department no.");
			flag=false;
		}
		String s=ae.getActionCommand();
		if(flag && s.equals("Read")){
			u.readData(mf);
		}
		else if(flag && s.equals("Update")){
			u.updateData(mf);
		}
		else if(flag && s.equals("Delete")){
			u.deleteData(mf);
		}
		System.out.println("button pressed");
	}
}

public class RUD{
    public static void main(String str[]){
        MyFrame mf=new MyFrame();
        mf.addWindowListener(new WindowSensor());
        mf.setVisible(true);
    }
}