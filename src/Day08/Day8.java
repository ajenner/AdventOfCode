package Day08;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ajenner on 09/12/2019.
 */
public class Day8 {
    String input;
    public Day8 (String input) {
        this.input = input;
    }

    public long question1(int width, int height) {
        Image img = new Image(input, width, height);
        Layer validationLayer = img.validationLayer();
        return validationLayer.getNumberOfPixelN('1') * validationLayer.getNumberOfPixelN('2');
    }

    public String question2(int width, int height) {
        Image img = new Image(input, width, height);
        Layer visibleLayer = img.visibleLayer();
        img.createImageFileFromLayer(visibleLayer);
        return visibleLayer.pixels;
    }

    class Image {
        ArrayList<Layer> layers = new ArrayList<>();
        int width;
        int height;

        public Image(String input, int width, int height) {
            this.width = width;
            this.height = height;
            buildLayers(input);
        }

        private void buildLayers(String input) {
            int pixelsPerLayer = this.height * this.width;
            for (int i = 0; i < input.length(); i += pixelsPerLayer) {
                layers.add(new Layer(input.substring(i, i + pixelsPerLayer)));
            }
        }

        public Layer validationLayer() {
            Collections.sort(this.layers, (Layer layer, Layer layer2) -> (int)(layer.getNumberOfPixelN('0') - layer2.getNumberOfPixelN('0')));
            return this.layers.get(0);
        }

        public Layer visibleLayer() {
            StringBuilder builder = new StringBuilder();
            int numPixels = this.height * this.width;
            for (int i = 0; i < numPixels; i++) {
                builder.append(visiblePixel(i));
            }
            return new Layer(builder.toString());
        }

        private char visiblePixel(int index) {
            for (Layer layer : this.layers) {
                char current = layer.pixels.charAt(index);
                if (current == '0') {
                    return current;
                } else if (current == '1') {
                    return current;
                }
            }
            return '2';
        }

        public void createImageFileFromLayer(Layer layer) {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

            for(int row = 0; row < this.height; row++) {
                for (int column = 0; column < this.width; column++) {
                    image.setRGB(column, row, this.getPixelRGBValue(layer.pixels.charAt(row * this.width + column)));
                }
            }
            try {
                ImageIO.write(image, "jpg", new File("C:\\AdventOfCode\\src\\Data\\day8OutputImage.jpg"));
                System.out.println("Image Created");
            }
            catch (Exception e) {
                System.out.println("Why have you done this?");
            }
        }

        private int getPixelRGBValue(char value) {
            if (value == '0') {
                return Color.BLACK.getRGB();
            }
            else if (value == '1') {
                return Color.WHITE.getRGB();
            }
            else {
                return Color.TRANSLUCENT;
            }
        }
    }


    class Layer {
        String pixels;
        public Layer (String input) {
            this.pixels = input;
        }

        public long getNumberOfPixelN(char N) {
            return pixels.chars().filter(ch -> ch == N).count();
        }
    }
}
