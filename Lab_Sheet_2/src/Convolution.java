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

public class Convolution {
	 
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
					 int  [][] c=  convolution(f);
					  int [][] cl=clip(c);

					//threshold the  convolution image
					    int av = avg(cl);
					    int[][] t = threshold(cl,av);
					  //int [][] m=MedianFilter(f, 9);
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
		
		static int[][] threshold(int[][] h, int threshold1 ) {
			
			 int[][] f= new int[h.length][h[0].length];
				for(int x=0;x<h.length;x++)
				{
					for(int y=0;y<h[0].length;y++)
					{
					
						if(h[x][y]>threshold1)
						{
							f[x][y]=255;
						}
						else
						   f[x][y]=0;
							
					}
				}
				return f;
			}
		 static int avg(int[][] h ) {
			 int sum=0,average=0,i=0;
				for(int x=0;x<h.length;x++)
				{
					for(int y=0;y<h[0].length;y++)
					{
					  i++;
						sum=sum+h[x][y];
							
					}
				}
				System.out.println(i);
				System.out.println(sum);
				average=sum/i;
				return average;
			}
		 static int[][] clip(int[][] f)
		 {
			 for(int x=0;x<f.length;x++)
				{
					for(int y=0;y<f[0].length;y++)
					{
						if(f[x][y]<0)
						{
							f[x][y]=0;
						}
						if(f[x][y]>255)
						{
							f[x][y]=255;
						}
					}
				}
			 return f;
		 }
		 static int[][] convolution(int[][] f) {
				double[][] w= {{1.0/9.0,1.0/9.0,1.0/9.0},
						{1.0/9.0,1.0/9.0,1.0/9.0},
						{1.0/9.0,1.0/9.0,1.0/9.0}};
			int a=(w.length-1)/2;
			int b=(w[0].length-1)/2;
			int [][] F= new int [f.length][f[0].length];
			int[][] f_padded=new int[2*a+f.length][2*a+f[0].length];
				for(int x=0;x<f.length;x++)
				{
					for(int y=0;y<f[0].length;y++)
					{
					  f_padded[a+x][b+y]=f[x][y];
					}
				}
				
				for(int x=0;x<F.length;x++)
				{
					for(int y=0;y<F[0].length;y++)
					{
						for(int s=-a;s<=a;s++)
							for(int t=-b;t<=b;t++)
						{
					 double v=w[s+a][t+b];
					 F[x][y]= F[x][y]+(int) (v*f_padded[(a+x)-s][(b+y)-t]);
					}
				}
				}
				
				return F;
			}
		 static int[][] MedianFilter(int [][] f, int size)
		 {
			 int[][] f_hat=new int[f.length][f[0].length];
			 int a=size/2;
			 for(int x=a;x<f.length-a;x++)
				{
					for(int y=a;y<f[0].length-a;y++)
					{
						int[] A=new int [size*size];
						int count=0;
						for(int i=x-a;i<=x+a;i++) {
							for(int j=y-a;j<=y+a;j++) {
							A[count]=f[i][j];
						 Arrays.sort(A);
						 f_hat[x][y]=A[(int) A.length/2];
							}
						 }
					}
				}
			 return f_hat;
		 }
		 static int minval(int z[][] ) { 
			 
			 //return Arrays.stream(z).flatMapToInt(Arrays::stream).collect(Collectors.summarizingInt(Integer::intValue)).getMin();
			 	
			 //	for(int x[]:z) {
			 		
			 	//}
			 
				int min=255;
				for(int x=0;x<z.length;x++)
				{
					for(int y=0;y<z[0].length;y++)
					{
						if(z[x][y]<min)
					 
					 	min=z[x][y];
					}
				}
				return min;
			}
		 static int maxval(int z[][] ) { 
				int max=0;
				for(int x=0;x<z.length;x++)
				{
					for(int y=0;y<z[0].length;y++)
					{
						if(z[x][y]>max)
					 
					 	max=z[x][y];
					}
				}
				return max;
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
