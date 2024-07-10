package resourcesManager;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {
    public enum ImageName {
        LOBBY_BACKGROUND("lobbyBackground.jpeg"),
        UPLOAD_BUTTON("uploadButton.png"),
        FILTER_BACKGROUND("filtersBackground.png"),
        BACK_TO_LOBBY_BUTTON("backToLobby.png"),
        SAVE_IMAGE_BUTTON("saveImage.png"),
        ADD_NOISE_BUTTON("addNoise.png"),
        VINTAGE_BUTTON("vintage.png"),
        GRAYSCALE_BUTTON("grayscale.png"),
        POSTERIZE_BUTTON("posterize.png"),
        TINT_BUTTON("tint.png"),
        MIRROR_BUTTON("mirror.png"),
        PIXELATE_BUTTON("pixelate.png"),
        REMOVE_COLOR_BUTTON("removeColor.png"),
        NEGATIVE_BUTTON("negative.png"),
        CONTRAST_BUTTON("contrast.png"),
        VIGNETTE_BUTTON("vignette.png"),
        SEPIA_BUTTON("sepia.png"),
        LIGHTER_BUTTON("lighter.png"),
        DARKER_BUTTON("darker.png"),
        RESET_FILTER_BUTTON("resetFilter.png");























        private final String fileName;

        ImageName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }

    private static final Map<ImageName, ImageIcon> imageCache = new HashMap<>();

    static {
        for (ImageName imageName : ImageName.values()) {
            loadImage(imageName);
        }
    }

    private static void loadImage(ImageName imageName) {
        String filePath = "resources/images/" + imageName.getFileName();
        ImageIcon imageIcon = new ImageIcon(filePath);
        imageCache.put(imageName, imageIcon);
    }

    public static ImageIcon getImageIcon(ImageName imageName) {
        return imageCache.get(imageName);
    }
}