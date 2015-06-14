import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Scorejpg {
	
	public static void main(String[] args) throws IOException {
		String s;
		s="7000";
		BufferedImage image = createImage(500, 500, s);

        if (image != null) {
        	 ImageIO.write(image, "jpg",new File("C:\\outt.png"));
        }
	}

	
	
	public static BufferedImage createImage(int width, int height, String s) {
   
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D GD = (Graphics2D)bi.getGraphics(); 
        Font font = new Font(Font.DIALOG_INPUT, Font.ITALIC, 50);
        GD.setBackground(Color.BLACK);    
        GD.clearRect(0, 0, width, height);    
        GD.setPaint(Color.RED);
        GD.setFont(font);
        GD.drawString(s, width/2-50, height / 2);       
        Graphics2D AFA=(Graphics2D)bi.getGraphics();
		Font AFAfont = new Font(Font.DIALOG_INPUT, Font.BOLD, 50); 
		AFA.setPaint(Color.GREEN);
		AFA.setFont(AFAfont);
        AFA.drawString("Your score", width/2-150, height / 3);   
        
        return bi;
        
	}
	
}