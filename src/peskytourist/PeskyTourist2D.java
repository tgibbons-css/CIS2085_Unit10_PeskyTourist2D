/*
    Images, concept, and code credit:
    John Nicholson
    Austin Peay State University
    Clarksville, TN
 */
package peskytourist;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PeskyTourist2D {

    public final static String IMAGE = "Statue";
    //public final static String IMAGE = "TowerHall";

    public static int width = 0;        // The width of the image
    public static int height = 0;       // the height of the image in pixels

    public static int[][] createMedian2D(int[][] array1, int[][] array2, int[][] array3) {
        // Create new array for storing median values (for the new image).
        int[][] newArray = new int[width][height];

        // For each index, find the median value of the three images: array1, array2 and array3
        // Store the median in the new array.
        // Return the new array.
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int num1 = array1[x][y];
                int num2 = array2[x][y];
                int num3 = array3[x][y];
                if (num2 < num1 && num1 < num3) {
                    //   num2 < num1 < num3
                    newArray[x][y] = num1;
                } else if (num3 <= num1 && num1 <= num2) {
                    //   num3 < num1 < num2
                    newArray[x][y] = num1;
                } else if (num1 <= num2 && num2 <= num3) {
                    //   num1 < num2 < num3
                    newArray[x][y] = num2;
                } else if (num3 <= num2 && num2 <= num1) {
                    //   num3 < num2 < num1
                    newArray[x][y] = num2;
                } else {
                    newArray[x][y] = num3;
                }
            }
        }
        return newArray;                  // Change this to return your new array
    }

    public static int[][] createMedian3D(int[][][] imageLayers) {
        // Create new array for storing median values (for the new image).
        int[][] newArray = new int[width][height];
        // For each index, find the median value of the three images: array1, array2 and array3
        // Store the median in the new array.
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int num1 = imageLayers[0][x][y];
                int num2 = imageLayers[1][x][y];
                int num3 = imageLayers[2][x][y];
                if (num2 < num1 && num1 < num3) {
                    //   num2 < num1 < num3
                    newArray[x][y] = num1;
                } else if (num3 <= num1 && num1 <= num2) {
                    //   num3 < num1 < num2
                    newArray[x][y] = num1;
                } else if (num1 <= num2 && num2 <= num3) {
                    //   num1 < num2 < num3
                    newArray[x][y] = num2;
                } else if (num3 <= num2 && num2 <= num1) {
                    //   num3 < num2 < num1
                    newArray[x][y] = num2;
                } else {
                    newArray[x][y] = num3;
                }
            }
        }
        // Return the new array.
        return newArray;                  // Change this to return your new array

    }

    public static void main(String[] args) throws IOException {
        // Read in the original images (png files).
        File file1 = new File(IMAGE + "1.png");
        BufferedImage image1 = ImageIO.read(file1);
        displayImage(file1, "Image 1");
        File file2 = new File(IMAGE + "2.png");
        BufferedImage image2 = ImageIO.read(file2);
        //displayImage(file2, "Image 2");
        File file3 = new File(IMAGE + "3.png");
        BufferedImage image3 = ImageIO.read(file3);
        //displayImage(file3, "Image 3");

        // Convert the images into arrays of ints.
        int[][] imageArray1 = create2DArray(image1);
        int[][] imageArray2 = create2DArray(image2);
        int[][] imageArray3 = create2DArray(image3);
        
        int[][][] imageArray3D = {imageArray1, imageArray2, imageArray3};

        // Create median array with three 2D arrays
        int[][] newImageArray2 = createMedian2D(imageArray1, imageArray2, imageArray3);
        // Create median array with three 2D arrays
        int[][] newImageArray3 = createMedian3D(imageArray3D);
       
        // Create new BufferedImage for final result.  Start with first image as a starting point.
        BufferedImage newImage = ImageIO.read(new File(IMAGE + "1.png"));

        // Convert RGB array into a 2D array of Colors.
        //Color[][] colorArray = createColor2DArray(newImageArray2);
        Color[][] colorArray = createColor2DArray(newImageArray3);


        // Update new image with final pixel values.
        updatePixels(newImage, colorArray);

        // Write the new file.
        File newImageFile = new File(IMAGE + "Final.png");
        ImageIO.write(newImage, "PNG", newImageFile);

        // Output message to user.
        System.out.println("Process complete!");
        System.out.println("Updated image stored in " + newImageFile.getAbsolutePath());
        displayImage(newImageFile, "New updated image");

    }

    public static void updatePixels(BufferedImage image, Color[][] colorArray) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                image.setRGB(x, y, colorArray[y][x].getRGB());
            }
        }
    }

    public static Color[][] createColor2DArray(int[][] integerArray) {
        Color[][] colorArray = new Color[height][width];
        int index = 0;
        int integerValue;
        int red;
        int green;
        int blue;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                integerValue = integerArray[x][y];
                red = integerValue / 1000000;
                integerValue = integerValue % 1000000;
                green = integerValue / 1000;
                integerValue = integerValue % 1000;
                blue = integerValue;
                index++;
                colorArray[y][x] = new Color(red, green, blue);
            }
        }

        return colorArray;
    }

    public static Color[][] createColorArray(int[] integerArray, int height, int width) {
        Color[][] colorArray = new Color[height][width];
        int index = 0;
        int integerValue;
        int red;
        int green;
        int blue;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                integerValue = integerArray[index];
                red = integerValue / 1000000;
                integerValue = integerValue % 1000000;
                green = integerValue / 1000;
                integerValue = integerValue % 1000;
                blue = integerValue;
                index++;

                colorArray[y][x] = new Color(red, green, blue);
            }
        }

        return colorArray;
    }

    public static int[] createRgbArray(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();

        Color pixel;
        int[] rgbArray = new int[height * width * 3];
        int index = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel = new Color(image.getRGB(x, y));

                rgbArray[index] = pixel.getRed();
                index++;

                rgbArray[index] = pixel.getGreen();
                index++;

                rgbArray[index] = pixel.getBlue();
                index++;
            }
        }

        return rgbArray;
    }

    // Create a 2D version of the array
    public static int[][] create2DArray(BufferedImage image) {
        height = image.getHeight();
        width = image.getWidth();

        Color pixel;
        int[][] integerArray = new int[width][height];
        int integerValue;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel = new Color(image.getRGB(x, y));
                integerValue = pixel.getRed() * 1000000 + pixel.getGreen() * 1000 + pixel.getBlue();
                integerArray[x][y] = integerValue;
            }
        }

        return integerArray;
    }

    public static int[] createIntegerArray(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();

        Color pixel;
        int[] integerArray = new int[height * width];
        int index = 0;
        int integerValue;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel = new Color(image.getRGB(x, y));
                integerValue = pixel.getRed() * 1000000 + pixel.getGreen() * 1000 + pixel.getBlue();
                integerArray[index] = integerValue;
                index++;
            }
        }

        return integerArray;
    }

    /**
     * Display an image in a dialog popup window with a given title
     *
     * @param imgFile
     * @param title
     */
    public static void displayImage(File imgFile, String title) {
        BufferedImage image;
        try {
            image = ImageIO.read(imgFile);
            JLabel picLabel = new JLabel(new ImageIcon(image));
            JOptionPane.showMessageDialog(null, picLabel, title, JOptionPane.PLAIN_MESSAGE, null);
        } catch (IOException ex) {
            System.out.println("Image file not found at " + imgFile.getAbsolutePath());
        }

    }
}
