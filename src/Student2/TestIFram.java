package Student2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JTextField;

class TestIFrame extends JFrame {
    private JTextField time;
    SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public TestIFrame(){
        super();
        setBounds(100, 100, 200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        time = new JTextField();
        this.add(time,BorderLayout.CENTER);
        time.addActionListener(new TimeActionListener());
        setVisible(true);

    }

    //添加状态栏“时间文本框”的事件监听器，用来实现动态刷新时间
    class TimeActionListener implements ActionListener{
        public TimeActionListener(){
            javax.swing.Timer t=new javax.swing.Timer(1000,this);
            t.start();
        }

        @Override
        public void actionPerformed(ActionEvent ae){
            time.setText(myfmt.format(new java.util.Date()).toString());
        }
    }

    public static void main(String[] args){
        new TestIFrame();
    }

}
