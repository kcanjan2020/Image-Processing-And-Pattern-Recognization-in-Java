


	import java.awt.Color;
	import java.awt.FlowLayout;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;

	import javax.imageio.ImageIO;
	import javax.swing.ImageIcon;
	import javax.swing.JFrame;
	import javax.swing.JLabel;

	public class Image_Read_Write_Display {
		
		public static void main(String[] args) 
		{
			BufferedImage image;
			try 
			{
				image=ImageIO.read(new File("D:\\Computer Engineering Note\\7th Sem\\2. Image Processing And Pattern Recognization\\IPPR_Lab_Solution\\Mbappe.jpg"));
					System.out.println("Reading Complete");
			} 
			catch (IOException e)
				{
				// TODO: handle exception
				System.out.println("Error:"+e);
				return;
				}
			for(int y=0;y<image.getHeight();y++)
				for(int x=0;x<image.getWidth();x++)
				{
					Color c=new Color( image.getRGB(x, y));
					int red=c.getRed();
					int green=(c.getGreen());
					int blue=(c.getBlue());
					int gray=(int)(red+green+blue)/3;
					Color newColor=new Color(gray,gray,gray);
					image.setRGB(x, y, newColor.getRGB());
				}
			try 
			{
				ImageIO.write(image, "jpg",new File("D:\\Computer Engineering Note\\7th Sem\\2. Image Processing And Pattern Recognization\\IPPR_Lab_Solution\\image_greyed.jpg"));
				System.out.println("Writing Complete");
			} catch (IOException e) {
		
				// TODO: handle exception
				System.out.println("Error:"+e);
				return;
			}
			ImageIcon icon=new ImageIcon(image);
			JFrame frame=new JFrame();
			frame.setLayout(new FlowLayout());
			frame.setSize(3500,2000);
			JLabel lbl=new JLabel();
			lbl.setIcon(icon);
			frame.add(lbl);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}

	}
