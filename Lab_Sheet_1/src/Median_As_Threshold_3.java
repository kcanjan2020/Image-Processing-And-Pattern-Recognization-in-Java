
 import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Median_As_Threshold_3 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		//Display Original Image
				displayGrayImage(image);
				
				//Display Gray Image
				int[][] f=getGrayImage(image);
				BufferedImage image2=arrayToImage(f);
				displayGrayImage(image2);
				
				//Display Threshold Image
				int median=getmedian(f);
				int[][] t=threshHold(f,median);
				BufferedImage image3=arrayToImage(t);
				displayGrayImage(image3);
		
		try 
		{
			ImageIO.write(image, "jpg",new File("D:\\Computer Engineering Note\\7th Sem\\2. Image Processing And Pattern Recognization\\IPPR_Lab_Solution\\image_greyed.jpg"));
			System.out.println("Writing Complete");
		} catch (IOException e) {
	
			// TODO: handle exception
			System.out.println("Error:"+e);
			return;
		}
		
	}
	static int[][] getGrayImage(BufferedImage image)
	 {
		 int m=image.getWidth();
		 int n=image.getHeight();
		 int [][]f=new int[m][n];
		 for(int y=0;y<n;y++)
		 {
			 for(int x=0;x<m;x++)
			 {
				 Color c=new Color( image.getRGB(x, y));
					int red=c.getRed();
					int green=(c.getGreen());
					int blue=(c.getBlue());
					int gray=(int)(red+green+blue)/3;
					f[x][y]=gray; 
			 }
		 }
		 		return f;
		}
	
	static BufferedImage arrayToImage(int[][]f){
		BufferedImage image=new BufferedImage(f.length, f[0].length,BufferedImage.TYPE_BYTE_GRAY);
		for(int x=0;x<image.getWidth();x++)
		{
			for(int y=0;y<image.getHeight();y++)
			{
				Color newColor= new Color(f[x][y],f[x][y],f[x][y]);
				image.setRGB(x, y, newColor.getRGB());
			}
		}
		return image;

	}
	
	static int[][] threshHold(int[][]f,int m)
	 {
		 for(int x=0;x<f.length;x++)
		 {
			 for(int y=0;y<f[0].length;y++)
			 {
				 if(f[x][y]>m)
				 {
					 f[x][y]=255;
				 }
				 else {
					f[x][y]=0;
				}
			 }
		 }
		 		return f;
		}
	
	static int getmedian(int[][] f)
	{
		
		int count=f.length*f[0].length;
		int[] arr=new int[count];
		int i=0;
		for(int x=0;x<f.length;x++)
		{
			for(int y=0;y<f[0].length;y++)
			{	
				
				if(i<count)
				{
					arr[i]=f[x][y];
				}
				i++;
			
			}
		}
		Arrays.sort(arr);
		
		int md=(count+1)/2;
		int median=arr[md-1];
		return median;
	}
	
	static void displayGrayImage(BufferedImage image)
	{
		ImageIcon icon=new ImageIcon(image);
		JFrame frame=new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(3500,2000);
		JLabel lbl=new JLabel("ffg");
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	 
}


				
			
