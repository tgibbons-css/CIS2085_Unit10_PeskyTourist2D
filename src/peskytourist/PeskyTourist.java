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

public class PeskyTourist {

    public final static String IMAGE = "Statue";
    //public final static String IMAGE = "TowerHall";

    public static int[] createMedianArray(int[] array1, int[] array2, int[] array3) {
        // Create new array for storing median values (for the new image).

        // For each index, find the average value of the three images: array1, array2 and array3
        // Store the average in the new array.
        
        // Return the new array.
        return array1;                  // Change this to return your new array

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
        int[] imageArray1 = createIntegerArray(image1);
        int[] imageArray2 = createIntegerArray(image2);
        int[] imageArray3 = createIntegerArray(image3);

        // Create median array.
        int[] newImageArray = createMedianArray(imageArray1, imageArray2, imageArray3);

        // Create new BufferedImage for final result.  Start with first image as a starting point.
        BufferedImage newImage = ImageIO.read(new File(IMAGE + "1.png"));

        // Convert RGB array into a 2D array of Colors.
        Color[][] colorArray = createColorArray2(newImageArray, newImage.getHeight(), newImage.getWidth());

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

    public static Color[][] createColorArray(int[] rgbArray, int height, int width) {
        Color[][] colorArray = new Color[height][width];
        int index = 0;
        int red;
        int green;
        int blue;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                red = rgbArray[index];
                index++;
                green = rgbArray[index];
                index++;
                blue = rgbArray[index];
                index++;

                colorArray[y][x] = new Color(red, green, blue);
            }
        }

        return colorArray;
    }

    public static Color[][] createColorArray2(int[] integerArray, int height, int width) {
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
