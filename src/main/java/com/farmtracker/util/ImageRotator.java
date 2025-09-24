package com.farmtracker.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;

public class ImageRotator {

    /**
     * Rotates a BufferedImage by a given angle in degrees.
     *
     * @param originalImage The BufferedImage to rotate.
     * @param angleDegrees The angle of rotation in degrees.
     * @return A new BufferedImage containing the rotated image.
     */
    public static BufferedImage rotateImage(BufferedImage originalImage, double angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);

        // Calculate new dimensions to accommodate the rotated image
        double sin = Math.abs(Math.sin(angleRadians));
        double cos = Math.abs(Math.cos(angleRadians));
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        int newWidth = (int) Math.floor(originalWidth * cos + originalHeight * sin);
        int newHeight = (int) Math.floor(originalHeight * cos + originalWidth * sin);

        // Create a new BufferedImage for the rotated image
        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics2D g2d = rotatedImage.createGraphics();

        // Set rendering hints for smoother rotation (anti-aliasing)
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Translate to center the original image within the new canvas
        g2d.translate((newWidth - originalWidth) / 2, (newHeight - originalHeight) / 2);

        // Rotate around the center of the original image
        g2d.rotate(angleRadians, originalWidth / 2, originalHeight / 2);

        // Draw the original image onto the new Graphics2D context
        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }
}
